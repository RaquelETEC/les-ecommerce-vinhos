package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.entity.Produtos;


public class DAOProdutos {

	public ArrayList<Produtos> ListarProdutos() {
		
		
		ArrayList<Produtos> listaDeProdutos = new ArrayList<>();
		String read = "SELECT pro_id, pro_desc, pro_img,pro_preco_compra, pro_preco_venda FROM produto";
		try {
			Connection con = Conexao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Produtos produto = new Produtos();
				produto.setId(rs.getInt("pro_id"));	
				produto.setPro_preco_venda(rs.getDouble("pro_preco_venda"));
				produto.setPro_preco_compra(rs.getDouble("pro_preco_compra"));
				produto.setDesc(rs.getString("pro_desc"));
				produto.setImg(rs.getString("pro_img"));
				
				listaDeProdutos.add(produto);
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Erro ao listar produtos: " + e);
		}
		return listaDeProdutos;
	}

	public Produtos buscarProdutoPorId(int id) {
	    Produtos produto = null;
	    String query = "SELECT `pro_id`, " +
	                   "`pro_categoria_id`, " +
	                   "`pro_precificacao_id`, " +
	                   "`pro_preco_venda`, " +
	                   "`pro_preco_compra`, " +
	                   "`pro_justificativa`, " +
	                   "`pro_codigo_barra`, " +
	                   "`pro_vinicola`, " +
	                   "`pro_pais`, " +
	                   "`pro_regiao`, " +
	                   "`pro_safra`, " +
	                   "`pro_desc`, " +
	                   "`pro_tipo`, " +
	                   "`pro_uva`, " +
	                   "`pro_alcool`, " +
	                   "`pro_altura`, " +
	                   "`pro_largura`, " +
	                   "`pro_peso`, " +
	                   "`pro_profundidade`, " +
	                   "`pro_inf`, " +
	                   "`pro_img` " +
	                   "FROM `ecommerce`.`produto` " +
	                   "WHERE `pro_id` = ?";
	    
	    try (Connection conn = Conexao.conectar();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        
	        stmt.setInt(1, id);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                produto = new Produtos();
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
	            }
	        }
	        conn.close(); 

	    } catch (SQLException e) {
	        System.err.println("Erro ao buscar produto por ID: " + e.getMessage());
	    }
	    
	    return produto;
	}
	
	
}
