package Service;

import java.util.ArrayList;

import Dao.DAOPedidoVenda;
import model.entity.PedidoVenda;

public class PedidoVendaService {
	
	private DAOPedidoVenda daoPedidoVenda;
	
	public PedidoVendaService() {
		this.daoPedidoVenda = new DAOPedidoVenda();
	}
	
	public ArrayList<PedidoVenda> listarPedidoVenda() {
		System.out.println("cheguei no pedido service ");
		return daoPedidoVenda.ListarPedidos();
	}
	
	public PedidoVenda selecionarPedido(PedidoVenda pedidovenda) {
		return daoPedidoVenda.selecionarPedidos(pedidovenda);
	}
	
	public PedidoVenda editarPedido(PedidoVenda pedidovenda){
		return daoPedidoVenda.EditarPedido(pedidovenda);
	}
}
