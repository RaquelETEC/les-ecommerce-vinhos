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


    public int inserirCartao(CartaoDeCredito cartao) {
		String create =   "INSERT INTO cliente" + //
                        "(cli_nome,cli_email,cli_senha,cli_cpf,cli_telefone_tipo,cli_telefone,cli_dt_nascimento,cli_genero, cli_status)" + //
                        "VALUES (?,?,?,?,?,?,?,?,?)";
                      
		try {
		    Connection con = Conexao.conectar();
		    PreparedStatement pst = con.prepareStatement(create, Statement.RETURN_GENERATED_KEYS); // Adicione Statement.RETURN_GENERATED_KEYS aqui
		   
		    pst.executeUpdate();

		    ResultSet rs = pst.getGeneratedKeys();
		    int idCliente = -1; // Valor padr�o se n�o houver chaves geradas
		    if (rs.next()) {
		        idCliente = rs.getInt(1); // Obtendo o ID gerado
		    }

		    con.close();
		    return idCliente;
		} catch (Exception e) {
		    System.out.println("Erro ao inserir cliente: " + e);
		    return -1; // Retornar um valor de erro
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
    
	
	
	
	
}
