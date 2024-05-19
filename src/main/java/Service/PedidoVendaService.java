package Service;

import java.util.ArrayList;
import java.util.List;

import Dao.DAOPedidoVenda;
import model.entity.CarrinhoItens;
import model.entity.CartaoDeCredito;
import model.entity.Cliente;
import model.entity.Cupons;
import model.entity.PedidoItens;
import model.entity.PedidoVenda;
import model.entity.TiposStatusItensPedido;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PedidoVendaService {
	
	private DAOPedidoVenda daoPedidoVenda;
	
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

	public String CadastrarPedido(PedidoVenda pedido, ArrayList<CarrinhoItens> itens, ArrayList<Cupons> listaCupons, ArrayList<CartaoDeCredito> listaCartoes) {
        if (pedido.getEndereco() == null || pedido.getEndereco().getId() == null) {
            return "Erro: Endereço não inserido.";
        }
        for (CarrinhoItens item : itens) {
            if (item.getQuantProd() <= 0 || item.getProduto().getPro_preco_venda() <= 0) {
                return "Erro: Existem itens de produto com quantidade ou valor inválido.";
            }
        }

        // Calcular o total de descontos
        double totalDesconto = 0;
        for (Cupons cupom : listaCupons) {
            if ("P".equals(cupom.getTipo())) {
                // Calcula o desconto como uma porcentagem do total do pedido
                totalDesconto += (cupom.getValor() * pedido.getValor()) / 100;
            } else if ("T".equals(cupom.getTipo())) {
                // Adiciona o valor total do desconto ao totalDesconto
                totalDesconto += cupom.getValor();
            }
        }

        // Calcular o total dos valores dos cartões
        double totalCartoes = listaCartoes.stream().mapToDouble(CartaoDeCredito::getValor).sum();

        // Verificação: A soma dos valores dos cartões e dos descontos deve ser igual ao total do pedido
        double totalPagamento = totalCartoes + totalDesconto;
        double saldo =  pedido.getValor() - totalPagamento;

        if (saldo > 0) {
            return "Erro: A soma dos valores dos cartões e dos descontos não é igual ao total do pedido.";
        }
		return daoPedidoVenda.CadastrarPedidoDao(pedido,itens, listaCupons, listaCartoes);	
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
