package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.PedidoItens;
import model.entity.TiposStatusItensPedido;

public class TrocaDAO {

	public String solicitarTroca(PedidoItens item, int quantidade, TiposStatusItensPedido novoStatus) {
	    System.out.println("solicitar troca dao");
		String insert = "INSERT INTO trocas (item_id, quantidade_solicitada, status) VALUES (?, ?, ?)";
	    String updatePedidoItens = "UPDATE pedido_itens SET ped_item_status_troca = ?, ped_item_quant_troca = ? WHERE ped_item_id = ?";

	    String retorno = "";
	    Connection con = Conexao.conectar();
	    
	    try ( PreparedStatement pst = con.prepareStatement(insert);
	         PreparedStatement pstPedidoItens  = con.prepareStatement(updatePedidoItens)) {
	        // Inserir a troca na tabela de trocas
	        pst.setInt(1, item.getId());
	        pst.setInt(2, quantidade);
	        pst.setInt(3, novoStatus.ordinal());
	        pst.executeUpdate();

	        // Atualizar o status e a quantidade trocada na tabela de pedido_itens
	        int quantidadeAtual = item.getQuantidadeTrocada();
	        int novaQuantidadeTrocada = quantidadeAtual + quantidade;
	        pstPedidoItens.setInt(1, novoStatus.ordinal());
	        pstPedidoItens.setInt(2, novaQuantidadeTrocada);
	        pstPedidoItens.setInt(3, item.getId());
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
        String updatePedidoItens = "UPDATE pedido_itens SET ped_item_status_troca = ? WHERE ped_item_id = ?";
        String updateTrocas = "UPDATE trocas SET status = ? WHERE item_id = ?";
        String retorno = "";
        try (Connection con = Conexao.conectar();
             PreparedStatement pstPedidoItens = con.prepareStatement(updatePedidoItens);
             PreparedStatement pstTrocas = con.prepareStatement(updateTrocas)) {
            pstPedidoItens.setInt(1, statusItem.ordinal());
            pstPedidoItens.setInt(2, item.getId());
            pstTrocas.setInt(1, statusItem.ordinal());
            pstTrocas.setInt(2, item.getId());
            pstPedidoItens.executeUpdate();
            pstTrocas.executeUpdate();
            retorno = "sucesso";
        } catch (Exception e) {
            retorno = "erro: " + e.getMessage();
        }
        return retorno;
    }
}
