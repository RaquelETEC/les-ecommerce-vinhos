package Dao;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.BandeiraCartao;
import model.entity.CartaoDeCredito;
import model.entity.Categoria;
import model.entity.Cliente;
import model.entity.Precificacao;
import model.entity.Produtos;


public class DAOProdutos {

	public ArrayList<Produtos> ListarProdutos() {
		
		ArrayList<Produtos> listaDeProdutos = new ArrayList<>();
		String read = "SELECT pro_id,pro_preco_venda,pro_preco_compra,pro_justificativa,pro_codigo_barra,pro_vinicola,"
				+ "pro_pais,pro_regiao,pro_safra,pro_desc,"
				+ "pro_tipo,pro_uva, pro_alcool,pro_altura,pro_largura,pro_peso,pro_profundidade,pro_inf,pro_img "
				+ "FROM produto";
		try {
			Connection con = Conexao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Produtos produto = new Produtos();
				produto.setId(rs.getInt("pro_id"));	
				produto.setPro_preco_venda(rs.getDouble("pro_preco_venda"));
				produto.setPro_preco_compra(rs.getDouble("pro_preco_compra"));
				produto.setJustificativa(rs.getString("pro_justificativa"));
				produto.setCodigo_barra(rs.getString("pro_codigo_barra"));
				produto.setVinicola(rs.getString("pro_vinicola"));
				produto.setPais(rs.getString("pro_pais"));
				produto.setRegiao(rs.getString("pro_regiao"));
				produto.setSafra(rs.getString("pro_safra"));
				produto.setDesc(rs.getString("pro_desc"));
				produto.setTipo(rs.getString("pro_tipo"));
				produto.setUva(rs.getString("pro_uva"));
				produto.setAlcool(rs.getString("pro_alcool"));
				produto.setAltura(rs.getString("pro_altura"));
				produto.setLargura(rs.getString("pro_largura"));
				produto.setPeso(rs.getString("pro_peso"));
				produto.setProfundidade(rs.getString("pro_profundidade"));
				produto.setInf(rs.getBlob("pro_inf"));
				produto.setImg(rs.getString("pro_img"));
				
				listaDeProdutos.add(produto);
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Erro ao listar produtos: " + e);
		}
		return listaDeProdutos;
	}
	
}
