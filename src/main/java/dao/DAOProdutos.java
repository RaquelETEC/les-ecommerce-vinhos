package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.Categoria;
import model.entity.Harmonizacao;
import model.entity.Precificacao;
import model.entity.Produtos;

public class DAOProdutos {

	public ArrayList<Produtos> ListarProdutos() {

		ArrayList<Produtos> listaDeProdutos = new ArrayList<>();
		String read = "SELECT pro_id, pro_desc, pro_img,pro_preco_compra, pro_preco_venda, pro_codigo_barra FROM produto";
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
				produto.setCodigo_barra(rs.getString("pro_codigo_barra"));

				listaDeProdutos.add(produto);
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Erro ao listar produtos: " + e);
		}
		return listaDeProdutos;
	}

	public ArrayList<Produtos> ListarProdutosAreaADM() {

		ArrayList<Produtos> listaDeProdutosADM = new ArrayList<>();
		String read = "SELECT " + "p.pro_id, " + "p.pro_desc, " + "p.pro_img, " + "p.pro_preco_compra, "
				+ "p.pro_preco_venda, " + "p.pro_codigo_barra, " + "p.pro_tipo, " + "p.pro_safra, " + "c.CAT_ID, "
				+ "c.CAT_DES, " + "c.CAT_STATUS, " + "pr.GRUP_ID, " + "pr.GRUP_DESC, " + "pr.GRUP_MARGEM "
				+ "FROM produto p " + "JOIN categoria c ON p.pro_categoria_id = c.CAT_ID "
				+ "JOIN precificacao pr ON p.pro_precificacao_id = pr.GRUP_ID";

		try {
			Connection con = Conexao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(rs.getInt("CAT_ID"));
				categoria.setDesc(rs.getString("CAT_DES"));
				categoria.setStatus(rs.getString("CAT_STATUS"));

				Precificacao precificacao = new Precificacao();
				precificacao.setId(rs.getInt("GRUP_ID"));
				precificacao.setDesc(rs.getString("GRUP_DESC"));
				precificacao.setMargem(rs.getDouble("GRUP_MARGEM"));

				Produtos produto = new Produtos();
				produto.setId(rs.getInt("pro_id"));
				produto.setPro_preco_venda(rs.getDouble("pro_preco_venda"));
				produto.setPro_preco_compra(rs.getDouble("pro_preco_compra"));
				produto.setDesc(rs.getString("pro_desc"));
				produto.setImg(rs.getString("pro_img"));
				produto.setCodigo_barra(rs.getString("pro_codigo_barra"));
				produto.setTipo(rs.getString("pro_tipo"));
				produto.setSafra(rs.getString("pro_safra"));
				produto.setCategoria(categoria);
				produto.setPrecificacao(precificacao);

				listaDeProdutosADM.add(produto);
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Erro ao listar produtos: " + e);
		}
		return listaDeProdutosADM;
	}

	public Produtos selecionarProduto(Produtos produto, Precificacao precificacao, Categoria categoria) {
		String read2 = "select *  from produto where pro_id = ?";

		try {
			Connection con = Conexao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setInt(1, produto.getId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				produto.setId(rs.getInt(1));
				categoria.setStatus(rs.getString(2));
				precificacao.setDesc(rs.getString(3));
				produto.setPro_preco_venda(rs.getDouble(4));
				produto.setPro_preco_compra(rs.getDouble(5));
				produto.setJustificativa(rs.getString(6));
				produto.setCodigo_barra(rs.getString(7));
				produto.setVinicola(rs.getString(8));
				produto.setPais(rs.getString(9));
				produto.setRegiao(rs.getString(10));
				produto.setSafra(rs.getString(11));
				produto.setDesc(rs.getString(12));
				produto.setTipo(rs.getString(13));
				produto.setUva(rs.getString(14));
				produto.setAlcool(rs.getString(15));
				produto.setAltura(rs.getString(16));
				produto.setLargura(rs.getString(17));
				produto.setPeso(rs.getString(18));
				produto.setProfundidade(rs.getString(19));
			}

		} catch (Exception e) {
			System.out.println("ERRO AO SELECIONAR PRODUTOS" + e);

		}
		return produto;
	}

	public Produtos buscarProdutoPorIdCompleto(int id) {
		Produtos produto = null;
		String query = "SELECT `pro_id`, " + "`pro_categoria_id`, " + "`pro_precificacao_id`, " + "`pro_preco_venda`, "
				+ "`pro_preco_compra`, " + "`pro_justificativa`, " + "`pro_codigo_barra`, " + "`pro_vinicola`, "
				+ "`pro_pais`, " + "`pro_regiao`, " + "`pro_safra`, " + "`pro_desc`, " + "`pro_tipo`, " + "`pro_uva`, "
				+ "`pro_alcool`, " + "`pro_altura`, " + "`pro_largura`, " + "`pro_peso`, " + "`pro_profundidade`, "
				+ "`pro_inf`, " + "`pro_img` " + "FROM `ecommerce`.`produto` " + "WHERE `pro_id` = ?";

		try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(query)) {

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

	public Produtos buscarProdutoPorIdSimples(int id) {
		Produtos produto = null;
		String query = "SELECT `pro_id`, `pro_preco_venda`, `pro_codigo_barra`, `pro_desc`, `pro_img` "
				+ "FROM `ecommerce`.`produto` " + "WHERE `pro_id` = ?";

		try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					produto = new Produtos();
					produto.setId(rs.getInt("pro_id"));
					produto.setPro_preco_venda(rs.getDouble("pro_preco_venda"));
					produto.setCodigo_barra(rs.getString("pro_codigo_barra"));
					produto.setDesc(rs.getString("pro_desc"));
					produto.setImg(rs.getString("pro_img"));
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao buscar produto por ID: " + e.getMessage());
		}

		return produto;
	}

	public List<Produtos> fetchAllProducts() {
		List<Produtos> listProdutos = new ArrayList<>();
		String query = "SELECT pro_id, pro_desc FROM produto";

		try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(query)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Produtos produto = new Produtos();
				produto.setId(rs.getInt("pro_id"));
				produto.setDesc(rs.getString("pro_desc"));
				listProdutos.add(produto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listProdutos;
	}

	public List<Produtos> ProdutosDisponiveis() {
		List<Produtos> listProdutos = new ArrayList<>();
		String query = "SELECT pro_id, pro_desc , pro_preco_venda FROM produto";

		try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(query)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Produtos produto = new Produtos();
				produto.setId(rs.getInt("pro_id"));
				produto.setDesc(rs.getString("pro_desc"));
				produto.setPro_preco_venda(rs.getDouble("pro_preco_venda"));
				listProdutos.add(produto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listProdutos;
	}

	public boolean atualizarProdutoEAssociados(Produtos produto, Precificacao precificacao, Categoria categoria) {
	    String updateProdutoQuery = "UPDATE produto SET pro_preco_compra = ?, "
	            + "pro_vinicola = ?, pro_pais = ?, pro_regiao = ?, pro_safra = ?, pro_desc = ?, pro_tipo = ?, "
	            + "pro_uva = ?, pro_alcool = ?, pro_altura = ?, pro_largura = ?, pro_peso = ?, pro_profundidade = ? WHERE pro_id = ?";

	    String updatePrecificacaoQuery = "UPDATE precificacao SET GRUP_DESC = ? WHERE GRUP_ID = ?";
	    String updateCategoriaQuery = "UPDATE categoria SET CAT_STATUS = ? WHERE CAT_ID = ?";

	    Connection conn = null;
	    PreparedStatement produtoStmt = null;
	    PreparedStatement precificacaoStmt = null;
	    PreparedStatement categoriaStmt = null;

	    try {
	        conn = Conexao.conectar(); // Assume que Conexao.conectar() retorna uma conexão válida
	        conn.setAutoCommit(false); // Configura para não auto commit, para controlar manualmente a transação

	        // Atualiza os dados do produto
	        produtoStmt = conn.prepareStatement(updateProdutoQuery);
	        produtoStmt.setDouble(1, produto.getPro_preco_compra());
	        produtoStmt.setString(2, produto.getVinicola());
	        produtoStmt.setString(3, produto.getPais());
	        produtoStmt.setString(4, produto.getRegiao());
	        produtoStmt.setString(5, produto.getSafra());
	        produtoStmt.setString(6, produto.getDesc());
	        produtoStmt.setString(7, produto.getTipo());
	        produtoStmt.setString(8, produto.getUva());
	        produtoStmt.setString(9, produto.getAlcool());
	        produtoStmt.setString(10, produto.getAltura());
	        produtoStmt.setString(11, produto.getLargura());
	        produtoStmt.setString(12, produto.getPeso());
	        produtoStmt.setString(13, produto.getProfundidade());
	        produtoStmt.setInt(14, produto.getId());
	        produtoStmt.executeUpdate();

	        // Atualiza os dados de precificação
	        precificacaoStmt = conn.prepareStatement(updatePrecificacaoQuery);
	        precificacaoStmt.setString(1, precificacao.getDesc()); // Ajustado para setString, pois estamos atualizando o desc
	        precificacaoStmt.setInt(2, precificacao.getId());
	        precificacaoStmt.executeUpdate();

	        // Atualiza os dados de categoria
	        categoriaStmt = conn.prepareStatement(updateCategoriaQuery);
	        categoriaStmt.setString(1, categoria.getStatus()); // Ajustado para setString, pois estamos atualizando o status
	        categoriaStmt.setInt(2, categoria.getId());
	        categoriaStmt.executeUpdate();

	        conn.commit(); // Confirma a transação
	        System.out.println("Produto e associações atualizados com sucesso: " + produto.getId());
	        return true;

	    } catch (SQLException e) {
	        if (conn != null) {
	            try {
	                conn.rollback(); // Reverte a transação em caso de erro
	            } catch (SQLException ex) {
	                System.err.println("Erro ao reverter a transação: " + ex.getMessage());
	            }
	        }
	        System.err.println("Erro ao atualizar produto e associados: " + e.getMessage());
	        return false;

	    } finally {
	        // Fechamento dos recursos
	        if (produtoStmt != null) {
	            try {
	                produtoStmt.close();
	            } catch (SQLException e) {
	                System.err.println("Erro ao fechar statement de produto: " + e.getMessage());
	            }
	        }
	        if (precificacaoStmt != null) {
	            try {
	                precificacaoStmt.close();
	            } catch (SQLException e) {
	                System.err.println("Erro ao fechar statement de precificação: " + e.getMessage());
	            }
	        }
	        if (categoriaStmt != null) {
	            try {
	                categoriaStmt.close();
	            } catch (SQLException e) {
	                System.err.println("Erro ao fechar statement de categoria: " + e.getMessage());
	            }
	        }
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                System.err.println("Erro ao fechar conexão: " + e.getMessage());
	            }
	        }
	    }
	}
	
	public ArrayList<Harmonizacao> getHarmonizacoesByProduto(Produtos produto) {
        String query = "SELECT harmonizacao.* FROM harmonizacao " +
                       "INNER JOIN rel_harm_produto ON harmonizacao.HAR_ID = rel_harm_produto.REL_HAR_ID " +
                       "WHERE rel_harm_produto.REL_HAR_PROD = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Harmonizacao> harmonizacoes = new ArrayList<>();

        try {
            conn = Conexao.conectar();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, produto.getId());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Harmonizacao harmonizacao = new Harmonizacao();
                harmonizacao.setId(rs.getInt("HAR_ID"));
                harmonizacao.setDesc(rs.getString("HAR_DESC"));
                harmonizacao.setTipo(rs.getString("HAR_TIPO_VINHO"));
                harmonizacoes.add(harmonizacao);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter harmonizações para o produto: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return harmonizacoes;
    }

}
