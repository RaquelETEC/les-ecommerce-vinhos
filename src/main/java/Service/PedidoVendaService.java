package Service;

import java.util.ArrayList;

import Dao.DAOPedidoVenda;
import model.entity.CarrinhoItens;
import model.entity.Cliente;
import model.entity.PedidoVenda;

public class PedidoVendaService {
	
	private DAOPedidoVenda daoPedidoVenda;
	
	public PedidoVendaService() {
		this.daoPedidoVenda = new DAOPedidoVenda();
	}
	
	public ArrayList<PedidoVenda> listarPedidoVenda() {
	    System.out.println("cheguei no pedido service (sem cliente)");
	    return daoPedidoVenda.listarPedidosTodosClientes();
	}

	public ArrayList<PedidoVenda> listarPedidoVenda(Cliente cliente) {
	    System.out.println("cheguei no pedido service (com cliente)");
	    return daoPedidoVenda.ListarPedidos(cliente);
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


}
