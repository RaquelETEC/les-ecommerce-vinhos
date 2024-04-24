package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.entity.Cliente;
import model.entity.Cupons;

public class DAOCupons {


		public ArrayList<Cupons> ListarCupons(Cliente cliente) {
			ArrayList<Cupons> listaDeCupons = new ArrayList<>();
			String read = "SELECT " +
				    "cup_id, " +
				    "cup_codigo, " +
				    "cup_desc, " +
				    "cup_img, " +
				    "cup_tipo, " +
				    "cup_valor, " +
				    "cup_validade " +
		            "FROM cupons c "+ 
		            "INNER JOIN rel_cup_cli cr ON c.cup_id = cr.rcc_cup_id "+
		            "WHERE cr.rcc_cli_id = ?";
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