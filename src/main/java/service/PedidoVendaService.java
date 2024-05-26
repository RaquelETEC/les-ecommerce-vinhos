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

import java.util.Date;
import java.text.SimpleDateFormat;

public class PedidoVendaService {
	
	private DAOPedidoVenda daoPedidoVenda;
	Cliente cliente = new Cliente();
	CupomService cupomService = new CupomService();
	
	public PedidoVendaService() {
		this.daoPedidoVenda = new DAOPedidoVenda();
	}
	
	public ArrayList<PedidoVenda> listarPedidoVenda(Cliente cliente, String status, int statusItem) {
	    return daoPedidoVenda.ListarPedidos(cliente,status,statusItem);
	}
	
	public PedidoVenda selecionarPedido(PedidoVenda pedidovenda) {
		return daoPedidoVenda.selecionarPedidos(pedidovenda);
	}
	
	public String editarPedido(PedidoVenda pedidovenda){
		return daoPedidoVenda.EditarPedido(pedidovenda);
	}

	public String cadastrarPedido(PedidoVenda pedido, ArrayList<CarrinhoItens> itens, ArrayList<Cupons> listaCupons, ArrayList<CartaoDeCredito> listaCartoes) {
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

        // Calcular o total dos valores dos cartões
        double totalCartoes = listaCartoes.stream().mapToDouble(CartaoDeCredito::getValor).sum();

        // Verificação: A soma dos valores dos cartões e dos descontos deve ser igual ao total do pedido
        double totalPagamento = totalCartoes + totalDesconto;
        double saldo =  pedido.getTotalPedido() - totalPagamento;

        if (saldo > 0) {
            return "Erro: A soma dos valores dos cartões e dos descontos não é igual ao total do pedido.";
        }
       
		String resposta = daoPedidoVenda.CadastrarPedidoDao(pedido,itens, listaCupons, listaCartoes); 
		
		// Verifica se a resposta contém o idPedido
		if (resposta != null && resposta.contains("idPedido=")) {
		    // Encontra a posição do início do idPedido na resposta
		    int indexIdPedido = resposta.indexOf("idPedido=");

		    // Extrai o idPedido da resposta
		    int idPedido = Integer.parseInt(resposta.substring(indexIdPedido + 9)); // O 9 representa o comprimento de "idPedido="

		    // Chama a função GerarCupom passando apenas o idPedido
		    if (saldo < 0) {
		        //cupomService.GerarCupom("SALDO", saldo, idPedido, 0);
		    }
		}
		
		return resposta;
	}

	public String trocaService(PedidoVenda pedido, List<PedidoItens> itensSelecionados,TiposStatusItensPedido novoStatus) {
		System.out.println("service trocas");
		String resposta = ""; 
	    for (PedidoItens item : itensSelecionados) {
	        try {
	            resposta += daoPedidoVenda.editarStatusItem(item, novoStatus);
	        } catch (NumberFormatException e) {
	            resposta = "" + e;
	        }
	    }
	    
	    if (!resposta.contains("error")){
		    resposta += this.editarPedido(pedido); 
	    }
	    
		return resposta;
	}




}
