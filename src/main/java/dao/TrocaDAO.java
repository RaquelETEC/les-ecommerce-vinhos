package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.PedidoItens;
import model.entity.PedidoVenda;
import model.entity.Produtos;
import model.entity.TiposStatusItensPedido;
import model.entity.Troca;

public class TrocaDAO {

	
	public ArrayList<PedidoVenda> ListarItensTroca() {
	    ArrayList<PedidoVenda> listaDePedidos = new ArrayList<>();
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    // Consulta SQL corrigida
	    String sql = "SELECT "
	            + "pv.ven_id, "
	            + "pv.vend_id_cliente, "
	            + "pv.ven_status, "
	            + "pv.ven_data, "
	            + "pv.ven_total_pedido, "
	            + "pi.ped_item_id, "
	            + "pi.ped_item_prod_id, "
	            + "pi.ped_item_prod_desc, "
	            + "pi.ped_item_prod_quantidade, "
	            + "pi.ped_item_prod_valor, "
	            + "pi.ped_item_prod_valor_total, "
	            + "pi.ped_item_status_troca, "
	            + "pi.ped_item_quant_troca, "
	            + "p.pro_img, "
	            + "p.pro_codigo_barra, "
	            + "t.troca_id, "
	            + "t.quantidade_solicitada, "
	            + "t.status "
	            + "FROM pedido_venda pv "
	            + "LEFT JOIN pedido_itens pi ON pv.ven_id = pi.ped_item_ven_id "
	            + "LEFT JOIN produto p ON pi.ped_item_prod_id = p.pro_id "
	            + "LEFT JOIN trocas t ON t.item_id = pi.ped_item_id "
	            + "WHERE t.status is not null";

	    try {
	        con = Conexao.conectar();
	        pstmt = con.prepareStatement(sql);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
				  int idPedido = rs.getInt("ven_id");
				  PedidoVenda pedido = new PedidoVenda();
				  pedido.setId(idPedido);
				  pedido.setCliente(new DaoCliente().buscarClientePorId(rs.getInt("vend_id_cliente")));
				  pedido.setStatus(rs.getString("ven_status"));
				  pedido.setData(rs.getDate("ven_data"));
				  pedido.setTotalPedido(rs.getDouble("ven_total_pedido"));
				
				  // Verificar se o pedido já está na lista
				  PedidoVenda pedidoExistente = listaDePedidos.stream()
				          .filter(p -> p.getId() == idPedido)
				          .findFirst()
				          .orElse(null);
				
				  if (pedidoExistente == null) {
				      listaDePedidos.add(pedido);
				      pedidoExistente = pedido;
				  }
				
				  // Adicionar o item do pedido, se houver, à lista de itens do pedido
				  if (rs.getInt("ped_item_prod_id") != 0) {
				  Produtos produto = new Produtos();
				  Troca troca = new Troca();
				
				  PedidoItens item = new PedidoItens();
				  item.setId(rs.getInt("ped_item_id"));
				  item.setDescricao(rs.getString("ped_item_prod_desc"));
				  item.setQuantidade(rs.getInt("ped_item_prod_quantidade"));
				  item.setPreco(rs.getDouble("ped_item_prod_valor"));
				  item.setTotalProduto(rs.getDouble("ped_item_prod_valor_total"));
				  item.setTipos(TiposStatusItensPedido.values()[rs.getInt("ped_item_status_troca")]);
				  item.setQuantidadeTrocada(rs.getInt("ped_item_quant_troca"));
				  item.setQuantidadeSolicitadaTroca(rs.getInt("quantidade_solicitada"));
				
				  produto.setId(rs.getInt("ped_item_prod_id"));
				  produto.setImg(rs.getString("pro_img"));
				  produto.setCodigo_barra(rs.getString("pro_codigo_barra"));
				
				  troca.setId(rs.getInt("troca_id"));
				  troca.setItemId(rs.getInt("ped_item_id"));
				  troca.setQuantidadeSolicitada(rs.getInt("quantidade_solicitada"));
				  troca.setStatus(TiposStatusItensPedido.values()[rs.getInt("status")]);
		
		          item.setProduto(produto);
		          item.setTroca(troca);
		
		          pedidoExistente.getPedidoItens().add(item);
		      }
				  
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro ao listar pedidos: " + e);
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (pstmt != null) {
	                pstmt.close();
	            }
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException e) {
	            System.out.println("Erro ao fechar recursos: " + e);
	        }
	    }

	    return listaDePedidos;
	}


	
	public String solicitarTroca(PedidoItens item, int quantidade, TiposStatusItensPedido novoStatus) {
	    System.out.println("solicitar troca dao");
		String insert = "INSERT INTO trocas (item_id, quantidade_solicitada, status) VALUES (?, ?, ?)";
	    String updatePedidoItens = "UPDATE pedido_itens SET ped_item_status_troca = ?, ped_item_quant_troca = 0 WHERE ped_item_id = ?";

	    String retorno = "";
	    Connection con = Conexao.conectar();
	    
	    try ( PreparedStatement pst = con.prepareStatement(insert);
	         PreparedStatement pstPedidoItens  = con.prepareStatement(updatePedidoItens)) {
	        // Inserir a troca na tabela de trocas
	        pst.setInt(1, item.getId());
	        pst.setInt(2, quantidade);
	        pst.setInt(3, novoStatus.ordinal());
	        pst.executeUpdate();

	       
	        pstPedidoItens.setInt(1, novoStatus.ordinal());
	        pstPedidoItens.setInt(2, item.getId());
	        pstPedidoItens.executeUpdate();

	        retorno = "success";
	    } catch (Exception e) {
	        retorno = "error" + e;
	    }finally{
	    	try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	    
	    return retorno;
	}

	public String alterarStatusTroca(PedidoItens item, TiposStatusItensPedido statusItem) {
	    String updatePedidoItens = "UPDATE pedido_itens SET ped_item_status_troca = ?, ped_item_quant_troca =? WHERE ped_item_id = ?";
	    String updateTrocas = "UPDATE trocas SET status = ? WHERE troca_id = ? ";
	    String retorno = "";
	    
	    Connection con = null;
	    PreparedStatement pstPedidoItens = null;
	    PreparedStatement pstTrocas = null;

	    try {
	        con = Conexao.conectar();
	        pstPedidoItens = con.prepareStatement(updatePedidoItens);
	        pstTrocas = con.prepareStatement(updateTrocas);

	        int quantidadeAtual = item.getQuantidadeTrocada();
	        int novaQuantidadeTrocada = quantidadeAtual + item.getQuantidadeSolicitadaTroca();

	        pstPedidoItens.setInt(1, statusItem.ordinal());
	        pstPedidoItens.setInt(2, novaQuantidadeTrocada);
	        pstPedidoItens.setInt(3, item.getId());

	        // Atualizar o status e a quantidade trocada na tabela de pedido_itens
	        pstPedidoItens.executeUpdate();
	        
	        pstTrocas.setInt(1, statusItem.ordinal());
	        pstTrocas.setInt(2, item.getTroca().getId());

	        pstTrocas.executeUpdate();

	        retorno = "sucesso";
	    } catch (Exception e) {
	        retorno = "erro: " + e.getMessage();
	    } finally {
	        try {
	            if (pstPedidoItens != null) {
	                pstPedidoItens.close();
	            }
	            if (pstTrocas != null) {
	                pstTrocas.close();
	            }
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException e) {
	            retorno = "erro ao fechar conexões: " + e.getMessage();
	        }
	    }
	    return retorno;
	}
	
	public int checkOtherTrocas(int itemId) {
	    String checkOtherTrocas = "SELECT COUNT(*) AS count FROM trocas WHERE item_id = ? AND status <>  ?";
	    int quantidadeEmTroca = 0;

	    try (Connection con = Conexao.conectar();
	         PreparedStatement pstCheckOtherTrocas = con.prepareStatement(checkOtherTrocas)) {
	        pstCheckOtherTrocas.setInt(1, itemId);
	        pstCheckOtherTrocas.setInt(2, TiposStatusItensPedido.TROCADO.ordinal());

	        try (ResultSet rsCheck = pstCheckOtherTrocas.executeQuery()) {
	            if (rsCheck.next()) {
	                quantidadeEmTroca = rsCheck.getInt("count");
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro ao verificar trocas: " + e.getMessage());
	    }

	    return quantidadeEmTroca;
	}
}
