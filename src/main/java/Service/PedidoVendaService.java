package Service;

import java.util.ArrayList;

import Dao.DAOPedidoVenda;
import model.entity.BandeiraCartao;
import model.entity.CartaoDeCredito;
import model.entity.Cliente;
import model.entity.PedidoVenda;

public class PedidoVendaService {
	
	private DAOPedidoVenda daoPedidoVenda;
	
	public PedidoVendaService() {
		this.daoPedidoVenda = new DAOPedidoVenda();
	}
	
	public ArrayList<PedidoVenda> listarPedidoVenda() {
		return daoPedidoVenda.ListarPedidos();
	}
	
	public PedidoVenda selecionarPedido(PedidoVenda pedidovenda) {
		return daoPedidoVenda.selecionarPedidos(pedidovenda);
	}
	
	public PedidoVenda editarPedido(PedidoVenda pedidovenda){
		return daoPedidoVenda.EditarPedido(pedidovenda);
	}
}
