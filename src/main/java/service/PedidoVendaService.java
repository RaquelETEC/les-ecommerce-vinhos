package service;

import java.util.ArrayList;
import java.util.List;

import dao.DAOPedidoVenda;
import model.entity.CarrinhoDeCompras;
import model.entity.CarrinhoItens;
import model.entity.CartaoDeCredito;
import model.entity.Cliente;
import model.entity.Cupons;
import model.entity.PedidoItens;
import model.entity.PedidoVenda;
import model.entity.StatusCarrinhoItens;
import model.entity.TiposStatusItensPedido;
import service.CarrinhoService;
import service.CupomService;
import dao.TrocaDAO;
import java.util.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class PedidoVendaService {

	private DAOPedidoVenda daoPedidoVenda;
	private TrocaDAO trocaDAO;

	Cliente cliente = new Cliente();
	CupomService cupomService = new CupomService();

	public PedidoVendaService() {

		this.daoPedidoVenda = new DAOPedidoVenda();
		this.trocaDAO = new TrocaDAO();
	}

	public ArrayList<PedidoVenda> listarPedidoVenda(Cliente cliente, String status, int statusItem) {
		return daoPedidoVenda.ListarPedidos(cliente, status, statusItem);
	}

	public PedidoVenda selecionarPedido(PedidoVenda pedidovenda) {
		return daoPedidoVenda.selecionarPedidos(pedidovenda);
	}

	public String editarPedido(PedidoVenda pedidovenda) {
		return daoPedidoVenda.EditarPedido(pedidovenda);
	}

	public String cadastrarPedido(PedidoVenda pedido, ArrayList<CarrinhoItens> itens, ArrayList<Cupons> listaCupons,
			ArrayList<CartaoDeCredito> listaCartoes) {
		cliente.setId(pedido.getCliente().getId());
		CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
		CarrinhoItens carrinhoItens = new CarrinhoItens();

		if (pedido.getEndereco() == null || pedido.getEndereco().getId() == null) {
			return "Erro: Endereço não inserido.";
		}
		if (itens.size() == 0) {
			return "Erro: Não há itens no pedido!.";
		}

		CarrinhoService carrinhoService = new CarrinhoService(); // Criando uma instância de CarrinhoService

		for (CarrinhoItens item : itens) {
			if (item.getQuantProd() <= 0 || item.getProduto().getPro_preco_venda() <= 0) {
				return "Erro: Existem itens de produto com quantidade ou valor inválido.";
			} else {
				carrinho = carrinhoService.SelecionarCarrinho(cliente);

				carrinhoItens.setId(item.getId());
				carrinhoItens.setCarrinho(carrinho);
				carrinhoItens.setMotivoRemocao("Comprado");
				carrinhoItens.setProduto(item.getProduto());
				carrinhoItens.setQuantProd(item.getQuantProd());
				carrinhoItens.setStatus(StatusCarrinhoItens.COMPRADO);

				String resposta = carrinhoService.alterarStatusCarrinhoItem(carrinhoItens);
			}
		}

		// Calcular o total de descontos
		double totalDesconto = 0;
		for (Cupons cupom : listaCupons) {
			if ("P".equals(cupom.getTipo())) {
				// Calcula o desconto como uma porcentagem do total do pedido
				totalDesconto += (cupom.getValor() * pedido.getTotalPedido()) / 100;
			} else if ("T".equals(cupom.getTipo())) {
				// Adiciona o valor total do desconto ao totalDesconto
				totalDesconto += cupom.getValor();
			}
		}
		// Arredonde o totalDesconto para duas casas decimais
		totalDesconto = Math.round(totalDesconto * 100.0) / 100.0;

		// Calcular o total dos valores dos cartões
		double totalCartoes = listaCartoes.stream().mapToDouble(CartaoDeCredito::getValor).sum();

		// Verificação: A soma dos valores dos cartões e dos descontos deve ser igual ao
		// total do pedido
		double totalPagamento = totalCartoes + totalDesconto;
		double saldo = Math.round((pedido.getTotalPedido() - totalPagamento) * 100.0) / 100.0;

		if (saldo > 0) {
			return "Erro: A soma dos valores dos cartões e dos descontos não é igual ao total do pedido.";
		}

		String resposta = daoPedidoVenda.CadastrarPedidoDao(pedido, itens, listaCupons, listaCartoes);

		if (resposta != null && resposta.contains("idPedido=")) {
			int indexIdPedido = resposta.indexOf("idPedido=");

			int idPedido = Integer.parseInt(resposta.substring(indexIdPedido + 9)); // O 9 representa o comprimento de
																					// "idPedido="

			if (saldo < 0) {
				String respostaCupom = cupomService.GerarCupom("SALDO", saldo * -1, idPedido, 0);

				Cupons cupomGerado = new Cupons();
				cupomGerado.setId(Integer.parseInt(respostaCupom));
				// respostaCupom = cupomService.vincularCupomAoCliente(cupomGerado,cliente);

				resposta = respostaCupom.contains("ERRO") ? respostaCupom : resposta;
			}
		}

		return resposta;
	}

	public String trocaService(PedidoVenda pedido, List<PedidoItens> itensSelecionados,
			TiposStatusItensPedido statusItem) {
		System.out.println("Service trocas");
		String resposta = "";

		for (PedidoItens item : itensSelecionados) {

			int quantEmTroca = trocaDAO.checkOtherTrocas(item.getId());

			if (quantEmTroca > 1 && statusItem == TiposStatusItensPedido.TROCADO)
				pedido.setStatus("EM TROCA");

			try {
				if (statusItem == TiposStatusItensPedido.TROCA_SOLICITADA) {
					resposta += trocaDAO.solicitarTroca(item, item.getQuantidadeSolicitadaTroca(), statusItem);
				} else {
					resposta += trocaDAO.alterarStatusTroca(item, statusItem);
				}
				resposta += this.editarPedido(pedido);
			} catch (Exception e) {
				resposta += "Error: " + e.getMessage();
			}
		}

		return resposta;
	}

	public ArrayList<PedidoVenda> listarItensTroca() {
		return trocaDAO.ListarItensTroca();

	}

}
