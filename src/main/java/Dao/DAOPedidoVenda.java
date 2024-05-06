package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

import model.entity.CarrinhoItens;
import model.entity.Cliente;

import model.entity.PedidoVenda;
import model.entity.TiposStatusItensPedido;


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


	public String CadastrarPedidoDao(PedidoVenda pedido, ArrayList<CarrinhoItens> itens) {
	    System.out.println("Dao cadastrar pedido");
	    String sqlPedido = "INSERT INTO pedido_venda (vend_id_cliente, ven_status, ven_data, ven_valor) VALUES (?, ?, ?, ?)";
	    String sqlItem = "INSERT INTO pedido_itens (ped_item_ven_id, ped_item_prod_id, ped_item_prod_desc, ped_item_prod_quantidade, ped_item_prod_valor, ped_item_prod_valor_total, ped_item_status_troca) VALUES (?, ?, ?, ?, ?, ?, ?)";

	    try (Connection con = Conexao.conectar();
	         PreparedStatement pstmtPedido = con.prepareStatement(sqlPedido, PreparedStatement.RETURN_GENERATED_KEYS);
	         PreparedStatement pstmtItem = con.prepareStatement(sqlItem)) {

	        con.setAutoCommit(false);

	        // Inserir o pedido
	        pstmtPedido.setInt(1, pedido.getCliente().getId());
	        pstmtPedido.setString(2, pedido.getStatus());
	        pstmtPedido.setDate(3, new java.sql.Date(pedido.getData().getTime()));
	        pstmtPedido.setDouble(4, pedido.getValor());
	        pstmtPedido.executeUpdate();

	        // Obter o ID do pedido gerado
	        ResultSet generatedKeys = pstmtPedido.getGeneratedKeys();
	        int idPedidoGerado = -1;
	        if (generatedKeys.next()) {
	            idPedidoGerado = generatedKeys.getInt(1);
	            pedido.setId(idPedidoGerado);
	        }

	        // Inserir os itens do pedido
	        if (idPedidoGerado != -1) {
	            for (CarrinhoItens item : itens) {
	                setParametrosItemPedido(pstmtItem, idPedidoGerado, item);
	                pstmtItem.addBatch(); // Adicionar a instrução ao batch
	            }

	            // Executar o batch de inserção
	            pstmtItem.executeBatch();
	        }

	        con.commit();

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "Erro ao cadastrar pedido: " + e.getMessage();
	    }

	    return "?id=" + pedido.getCliente().getId() + "&idPedido=" + pedido.getId();
	}

	private void setParametrosItemPedido(PreparedStatement pstmtItem, int idPedido, CarrinhoItens item) throws SQLException {
	    System.out.println("Configurando parâmetros do item do pedido");

	    double valorTotal = item.getProduto().getPro_preco_venda() * item.getQuantProd();

	    pstmtItem.setInt(1, idPedido);
	    pstmtItem.setInt(2, item.getProduto().getId());
	    pstmtItem.setString(3, item.getProduto().getDesc());
	    pstmtItem.setDouble(4, item.getQuantProd());
	    pstmtItem.setDouble(5, item.getProduto().getPro_preco_venda());
	    pstmtItem.setDouble(6, valorTotal);
	    pstmtItem.setInt(7, TiposStatusItensPedido.TROCA_NAO_SOLICITADA.ordinal());
	}
}
