package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.entity.Cliente;
import model.entity.Cupons;

public class DaoCupons {

	
	public ArrayList<Cupons> ListarCuponsCliente(Cliente cliente) {
		System.out.println("acesso ao listar cupons");
		
		ArrayList<Cupons> listaDeCupons = new ArrayList<>();
		String read = "SELECT " 
	            + "c.cup_id, " 
	            + "c.cup_codigo, " 
	            + "c.cup_desc, " 
	            + "c.cup_img, " 
	            + "c.cup_tipo, "
	            + "c.cup_valor, " 
	            + "c.cup_validade "  
	            + "FROM cupons c "
	            + "INNER JOIN cliente_cupom_relacionamento cr ON c.cup_id = cr.cupom_id "
	            + "WHERE cr.cliente_id = ?";
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

}
