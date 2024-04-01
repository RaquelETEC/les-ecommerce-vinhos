package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.entity.BandeiraCartao;
import model.entity.CartaoDeCredito;
import model.entity.Cliente;

public class DaoCartoes {


    public String inserirCartao(Cliente cliente, CartaoDeCredito cartao, BandeiraCartao bandeira) {
    	
        System.out.println("DAO : id do cliente para o cartão:"+ cliente.getId());
 
    	String create = "INSERT INTO cartao_de_credito (cart_id_cli, cart_numero, cart_nome, cart_padrao, cart_id_bandeira, cart_cod_seguranca) "
                + "VALUES (?, ?, ?, ?, ?,?)";
                      
		try {
		    Connection con = Conexao.conectar();
            System.out.println("chegou no try no inserirCartao" +cliente.getId() );
		    PreparedStatement pst = con.prepareStatement(create, Statement.RETURN_GENERATED_KEYS); 
		    
		    int idBandeira = bandeira.getId();
		 
		    
			pst.setInt(1, cliente.getId());
			pst.setString(2, cartao.getNumero());
			pst.setString(3, cartao.getNome());
			pst.setString(4, cartao.getPadrao());
			pst.setInt(5, idBandeira);
			pst.setInt(6, cartao.getCodigoSeguranca());


			pst.executeUpdate(); 
			System.err.println("inserido cartao no dao!!");
			con.close();
			return "Sucesso";

		} catch (Exception e) {
			System.out.println("erro ao inserir cartao: "+e);
			return "Erro";
		}
	}

    
    public ArrayList<CartaoDeCredito> ListarCartoes(Cliente cliente) {
        ArrayList<CartaoDeCredito> listaDeCartoes = new ArrayList<>();
        String read = "SELECT "
                    + "cart_id, "
                    + "cart_numero, "
                    + "cart_nome, "
                    + "cart_padrao, "
                    + "band_id, " 
                    + "band_desc, "
                    + "band_img, "
                    + "cart_cod_seguranca "
                    + "FROM cartao_de_credito "
                    + "join bandeiras_cartao on cart_id_bandeira = band_id "
                    + "WHERE cart_id_cli = ?";
        try {
            Connection con = Conexao.conectar();
            PreparedStatement pst = con.prepareStatement(read);
            pst.setInt(1, cliente.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            	 BandeiraCartao bandeira = new BandeiraCartao();
                 bandeira.setId(rs.getInt("band_id"));
                 bandeira.setDesc(rs.getString("band_desc"));
                 bandeira.setImg(rs.getString("band_img"));

                 CartaoDeCredito cartao = new CartaoDeCredito();
                 cartao.setId(rs.getInt("cart_id"));
                 cartao.setNumero(rs.getString("cart_numero"));
                 cartao.setNome(rs.getString("cart_nome"));
                 cartao.setPadrao(rs.getString("cart_padrao"));
                 cartao.setCodigoSeguranca(rs.getInt("cart_cod_seguranca"));
                 cartao.setBandeira(bandeira);
                 
                listaDeCartoes.add(cartao);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar cartões: " + e);
        }
        return listaDeCartoes;
    }
    
	
    public void selecionarCartao(Cliente cliente, CartaoDeCredito cartao, BandeiraCartao bandeira) {
		String read2 = "select *  from cartao_de_credito where cart_id = ?";
		try {
			Connection con = Conexao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setInt(1, cartao.getId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
			    int idBandeira = bandeira.getId();
			    int idCliente = cliente.getId();
			    
				cartao.setId(rs.getInt(1));
				cliente.setId(rs.getInt(idCliente));
				cartao.setNumero(rs.getString(3));
				cartao.setNome(rs.getString(4));
				cartao.setPadrao(rs.getString(5));
				bandeira.setId(rs.getInt(idBandeira));
				cartao.setCodigoSeguranca(7);
			}
			con.close();
		} catch (Exception e) {
			System.out.println("ERRO AO SELECIONAR" + e);
		}
		
	}
    
	public void deletarCartao(CartaoDeCredito cartao) {
		String delete = "delete from cartao_de_credito where cart_id=?";
		
		System.out.println("eu cheguei aqui "+ cartao.getId());
		try {
	        Connection con = Conexao.conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setInt(1, cartao.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
}
