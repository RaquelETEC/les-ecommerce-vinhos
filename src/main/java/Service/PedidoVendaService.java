package Service;

import java.util.ArrayList;
import java.util.List;

import Dao.DAOPedidoVenda;
import model.entity.CarrinhoItens;
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
	
	public PedidoVenda editarPedido(PedidoVenda pedidovenda){
		return daoPedidoVenda.EditarPedido(pedidovenda);
	}

	public String CadastrarPedido(PedidoVenda pedido, ArrayList<CarrinhoItens> itens) {
		return daoPedidoVenda.CadastrarPedidoDao(pedido,itens);	
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

	public String GerarCupom(String tipoCupom, Double valorCupom, int idPedido, int idProduto) {
        if (tipoCupom == null || tipoCupom.isEmpty()) {
            return "TIPO CUPOM É OBRIGATÓRIO!";
        } else if (valorCupom == null || valorCupom <= 0) {
            return "VALOR DO CUPOM É OBRIGATÓRIO!";
        } else if (tipoCupom.equals("T") && (idPedido == 0 || idProduto == 0)) {
            return "PARA CUPONS DE TROCA É OBRIGATÓRIO O ID DO PEDIDO E ID PRODUTO";
        } else {
            String codigo;
            String descricao;
            String img;
            Date validade;

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataAtual = new Date();

            if (tipoCupom.equals("T")) { 
                codigo = "TROCA#" + idPedido;
                descricao = "Desconto de R$" + valorCupom + " pela troca do item " + idProduto + " no pedido " + idPedido;
                img = "descontoCupom.png";
                validade = adicionarMeses(dataAtual, 12);
            } else { 
                codigo = "PROMO#"; 
                descricao = "Cupom promocional de desconto de R$" + valorCupom;
                img = "imagens/assets/descontoCupom.png";
                validade = adicionarMeses(dataAtual, 6); //validade de 6 meses
            }

            // Retornando o resultado
            return ""+daoPedidoVenda.gerarCupom(codigo, descricao, img, tipoCupom, valorCupom, validade, 0);
        }
    }

    // Função para adicionar meses a uma data
    private Date adicionarMeses(Date data, int meses) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(data);
        cal.add(java.util.Calendar.MONTH, meses);
        return cal.getTime();
    }

	public String vincularCupomAoCliente(Cupons cupom, Cliente cliente) {
	   if (cupom == null || cliente == null) {
	        return "Cupom ou cliente não fornecido";
	    }
	   else {
		return daoPedidoVenda.vincularCupomAoCliente(cupom, cliente);
	   }
	}



}
