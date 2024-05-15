
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Dao.Conexao;
import model.entity.BandeiraCartao;
import model.entity.CartaoDeCredito;
import model.entity.Cliente;
import model.entity.Cupons;

public class DaoCupons {

	public ArrayList<Cupons> ListarCupons(Cliente cliente) {
		ArrayList<Cupons> listaDeCupons = new ArrayList<>();
		String read = "SELECT " + "cup_id, " + "cup_codigo, " + "cup_desc, " + "cup_img, " + "cup_tipo, "
				+ "cup_valor, " + "cup_validade " + "FROM cupons c "
				+ "INNER JOIN rel_cup_cli cr ON c.cup_id = cr.rcc_cup_id " + "WHERE cr.rcc_cli_id = ?";
		try {
			Connection con = Conexao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setInt(1, cliente.getId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				Cupons cupom = new Cupons();

				cupom.setId(rs.getInt("cup_id"));
				cupom.setCodigo(rs.getString("cup_codigo"));
				cupom.setDesc(rs.getString("cup_desc"));
				cupom.setImg(rs.getString("cup_img"));
				cupom.setTipo(rs.getString("cup_tipo"));
				cupom.setValor(rs.getDouble("cup_valor"));
				cupom.setValidade(rs.getDate("cup_validade"));

				listaDeCupons.add(cupom);
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Erro ao listar cupons: " + e);
		}
		return listaDeCupons;
	}

	public int inserirCuponsTroca(Cliente cliente, Cupons cupom) {

	    String createCupom = "INSERT INTO cupons (cup_codigo, cup_desc, cup_img, cup_tipo, cup_valor, cup_validade, cup_usado) "
	                         + "VALUES (?, ?, ?, ?, ?, ?, ?)";
	    
	    String createRelacionamento = "INSERT INTO rel_cup_cli (rcc_cup_id, rcc_cli_id) "
	                                 + "VALUES (?, ?)";

	    try {
	        Connection conn = Conexao.conectar();
	        
	        // Inserir o cupom na tabela 'cupons'
	        PreparedStatement stmt1 = conn.prepareStatement(createCupom, Statement.RETURN_GENERATED_KEYS);
	        stmt1.setString(1, cupom.getCodigo());
	        stmt1.setString(2, cupom.getDesc());
	        stmt1.setString(3, cupom.getImg());
	        stmt1.setString(4, cupom.getTipo());
	        stmt1.setDouble(5, cupom.getValor());
	        stmt1.setDate(6, new java.sql.Date(cupom.getValidade().getTime()));
	        stmt1.setBoolean(7, false); // Definir como n�o utilizado por padr�o
	        int rowsAffected = stmt1.executeUpdate();
	        
	        if (rowsAffected == 1) { // Se o cupom foi inserido com sucesso
	            ResultSet generatedKeys = stmt1.getGeneratedKeys();
	            int cupomId = -1;
	            if (generatedKeys.next()) {
	                cupomId = generatedKeys.getInt(1); // Obtendo o ID gerado do cupom
	            }
	            
	            // Inserir o relacionamento na tabela 'rel_cup_cli'
	            PreparedStatement stmt2 = conn.prepareStatement(createRelacionamento);
	            stmt2.setInt(1, cupomId);
	            stmt2.setInt(2, cliente.getId());
	            rowsAffected = stmt2.executeUpdate();
	            
	            if (rowsAffected == 1) { // Se o relacionamento foi inserido com sucesso
	                conn.close();
	                return cupomId;
	            } else {
	                conn.rollback();
	                conn.close();
	                return -1; // Retornar um valor de erro
	            }
	        } else {
	            conn.rollback();
	            conn.close();
	            return -1; // Retornar um valor de erro
	        }
	    } catch (Exception e) {
	        System.out.println("Erro ao inserir cupom: " + e);
	        return -1; // Retornar um valor de erro
	    }
	}
}