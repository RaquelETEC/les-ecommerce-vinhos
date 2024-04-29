package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.entity.Cliente;

import model.entity.PedidoVenda;


public class DAOPedidoVenda {

	public ArrayList<PedidoVenda> ListarPedidos() {
		System.out.println("cheguei no dao ");
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
	            
	            cliente.setId(clienteId);
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
				pedidovenda.setStatus(rs.getString(1)); 
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


	public String CadastrarPedidoDao(PedidoVenda pedido) {
        String mensagem = "";
        String sql = "INSERT INTO pedido_venda (vend_id_cliente, ven_status, ven_data, ven_valor) " +
                     "VALUES (?, ?, ?, ?)";

        try {
            Connection con = Conexao.conectar();

            PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, pedido.getCliente().getId());
            pstmt.setString(2, pedido.getStatus());
            pstmt.setDate(3, new java.sql.Date(pedido.getData().getTime()));
            pstmt.setDouble(4, pedido.getValor());

            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idPedidoGerado = generatedKeys.getInt(1);
                pedido.setId(idPedidoGerado); 
            } 

            con.close(); 

        } catch (SQLException e) {
            mensagem = "Erro ao cadastrar pedido: " + e.getMessage();
            e.printStackTrace();
        }

        return  "?id=" + pedido.getCliente().getId() + "&idPedido=" + pedido.getId();
    }
}
