package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.entity.BandeiraCartao;
import model.entity.CartaoDeCredito;
import model.entity.Cliente;

import model.entity.PedidoVenda;


public class DAOPedidoVenda {

	public ArrayList<PedidoVenda> ListarPedidos() {
	    ArrayList<PedidoVenda> listaDePedidos = new ArrayList<>();
	    String read = "SELECT " +
	        "ven_id, " +
	        "vend_id_cliente, " +
	        "ven_status, " +
	        "ven_data, " +
	        "ven_valor " +
	        "FROM pedido_venda";
	    try {
	        Connection con = Conexao.conectar();
	        PreparedStatement pst = con.prepareStatement(read);
	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            PedidoVenda pedido = new PedidoVenda();
	            pedido.setId(rs.getInt("ven_id"));

	            int clienteId = rs.getInt("vend_id_cliente");
	            Cliente cliente = new DaoCliente().buscarClientePorId(clienteId); // Chamada para buscar o cliente pelo ID
	            pedido.setCliente(cliente);

	            pedido.setStatus(rs.getString("ven_status"));
	            pedido.setData(rs.getDate("ven_data"));
	            pedido.setValor(rs.getDouble("ven_valor"));

	            listaDePedidos.add(pedido);
	        }
	        con.close();
	    } catch (Exception e) {
	        System.out.println("Erro ao listar pedidos: " + e);
	    }
	    return listaDePedidos;
	}
	
	
	public PedidoVenda selecionarPedidos(PedidoVenda pedidovenda) {
		String read2 = "select ven_valor,ven_status from pedido_venda where ven_id = ?";
		try {
			Connection con = Conexao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setInt(1, pedidovenda.getId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				pedidovenda.setValor(rs.getDouble(1)); 
				pedidovenda.setStatus(rs.getString(2)); 
			}
			con.close();
		} catch (Exception e) {
			System.out.println("ERRO AO SELECIONAR" + e);
		}

		return pedidovenda;
	}
	
	
	public PedidoVenda EditarPedido(PedidoVenda pedidovenda) {
		String update = "update pedido_venda set " + "ven_status = ? " 
				+ "WHERE ven_id = ?;";
		try {
			Connection con = Conexao.conectar();

			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, pedidovenda.getStatus());
			pst.setInt(2, pedidovenda.getId());
			pst.executeUpdate();
			con.close();
			
			System.err.println("Atualizado o pedido no dao!!" + pedidovenda.getId());
			return pedidovenda;

		} catch (Exception e) {
			System.out.println("erro ao atualizar o pedido: " + e);
			return pedidovenda;
		}
	}
}
