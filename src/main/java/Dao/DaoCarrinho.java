package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.entity.CarrinhoItens;
import model.entity.Cliente;
import model.entity.Produtos;
import model.entity.CarrinhoDeCompras;

public class DaoCarrinho {
	Connection con = Conexao.conectar();

	public CarrinhoDeCompras SelecionarCarrinho(Cliente cliente) {
	    System.out.println("cheguei no SelecionarCarrinho" + cliente.getId());
	    CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

	    String read = "select * from carrinho_de_compras where car_cli_id = ?";
	    try {
	        Connection con = Conexao.conectar(); // Criação da conexão local
	        PreparedStatement pst = con.prepareStatement(read);
	        pst.setInt(1, cliente.getId());
	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            carrinho.setId(rs.getInt(1));
	            carrinho.setCliente(cliente);
	            carrinho.setQuantItems(rs.getInt(3));
	        }
	        con.close(); 

	    } catch (Exception e) {
	        System.out.println("ERRO AO SELECIONAR carrinho_de_compras" + e);
	    }
	    return carrinho;
	}
	public ArrayList<CarrinhoItens> ListarCarrinho(CarrinhoDeCompras carrinho) {

		System.out.println("Acesso ao Dao ListarCarrinho");

		ArrayList<CarrinhoItens> ArrayListcarrinhoItems = new ArrayList<>();
		String read = "SELECT car_itens_id, " + 
	              "car_itens_car_id, " + 
	              "car_itens_prod_id, " + 
	              "car_itens_prod_quant, " +
	              "car_itens_removido, " + 
	              "car_itens_motivo " + 
	              "FROM carrinho_itens " + 
	              "WHERE car_itens_car_id = ?";
		try {
			Connection con = Conexao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setInt(1, carrinho.getId());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Produtos produto = new Produtos();
				CarrinhoItens itens = new CarrinhoItens();

				produto.setId(rs.getInt(3));

				carrinho.setId(rs.getInt(2));

				itens.setCarrinho(carrinho);
				itens.setId(rs.getInt(1));
				itens.setProduto(produto);
				itens.setQuantProd(rs.getInt(4));
				itens.setRemovido(rs.getBoolean(5));
				itens.setMotivoRemocao(rs.getString(6));

				ArrayListcarrinhoItems.add(itens);
			}
			con.close();
			return ArrayListcarrinhoItems;
		} catch (Exception e) {
			System.out.println("ERRO AO LISTAR" + e);
			return null;
		}

	}
	public String AlterarQuantidade(CarrinhoItens carrinhoItem, int quantidade) {
		String read = "UPDATE  carrinho_itens SET " + 
					"car_itens_prod_quant = ? " +
					 "WHERE car_itens_id = ?";
		try {
			Connection con = Conexao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			
			pst.setInt(1, quantidade);
			pst.setInt(2, carrinhoItem.getId());

			pst.executeUpdate();
			con.close();
            return "Quantidade atualizada no carrinho com sucesso!";
			} catch (SQLException e) {
				 e.printStackTrace();
			     return "Erro ao atualizar quantidade: " + e;
			}
		}
	
	public String adicionarAoCarrinho(CarrinhoDeCompras carrinho, Produtos produto, int quantidade) {
	    String selectQuery = "SELECT car_itens_prod_quant, car_itens_removido FROM carrinho_itens WHERE car_itens_car_id = ? AND car_itens_prod_id = ?";
	    String updateQuery = "UPDATE carrinho_itens SET car_itens_prod_quant = ?, car_itens_removido = 0 WHERE car_itens_car_id = ? AND car_itens_prod_id = ?";
	    String insertQuery = "INSERT INTO carrinho_itens (car_itens_car_id, car_itens_prod_id, car_itens_prod_quant, car_itens_removido) VALUES (?, ?, ?, 0)";

	    try (Connection con = Conexao.conectar();
	         PreparedStatement selectPst = con.prepareStatement(selectQuery);
	         PreparedStatement updatePst = con.prepareStatement(updateQuery);
	         PreparedStatement insertPst = con.prepareStatement(insertQuery)) {
	        
	        selectPst.setInt(1, carrinho.getId());
	        selectPst.setInt(2, produto.getId());
	        ResultSet rs = selectPst.executeQuery();
	        
	        if (rs.next()) {
	            // Se o produto já existe no carrinho, verificar se está removido
	            int quantidadeAtual = rs.getInt("car_itens_prod_quant");
	            boolean removido = rs.getBoolean("car_itens_removido");
	            int novaQuantidade = quantidadeAtual + quantidade;
	            
	            // Atualizar a quantidade e o status do produto
	            updatePst.setInt(1, novaQuantidade);
	            updatePst.setInt(2, carrinho.getId());
	            updatePst.setInt(3, produto.getId());
	            updatePst.executeUpdate();
	            
	            if (removido) {
	                // Se estava removido, atualizar o status
	                String updateStatusQuery = "UPDATE carrinho_itens SET car_itens_removido = 0, car_itens_prod_quant = ? WHERE car_itens_car_id = ? AND car_itens_prod_id = ?";
	                try (PreparedStatement updateStatusPst = con.prepareStatement(updateStatusQuery)) {
	                    updateStatusPst.setInt(1, novaQuantidade);
	                    updateStatusPst.setInt(2, carrinho.getId());
	                    updateStatusPst.setInt(3, produto.getId());
	                    updateStatusPst.executeUpdate();
	                }
	            }
	            
	            return "Quantidade atualizada no carrinho com sucesso!";
	        } else {
	            // Se o produto não existe no carrinho, insira um novo registro
	            insertPst.setInt(1, carrinho.getId());
	            insertPst.setInt(2, produto.getId());
	            insertPst.setInt(3, quantidade);
	            insertPst.executeUpdate();
	            return "Produto inserido no carrinho com sucesso!";
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "Erro ao adicionar ao carrinho: " + e;
	    }
	}

	public String alterarStatusCarrinho(CarrinhoDeCompras carrinho, Produtos produto, String motivo, int produtoRemovido) {
		String read = 
				"UPDATE  carrinho_itens SET " 
		        +"car_itens_removido = ? ,"
				+"car_itens_prod_quant = ?, "		
		        +"car_itens_motivo = ? " 
		        + "WHERE car_itens_car_id = ? "
		        + "and car_itens_prod_id = ? ";
		try {
			Connection con = Conexao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			
			pst.setInt(1, produtoRemovido);
			pst.setInt(2, carrinho.getQuantItems());
			pst.setString(3, motivo);
			pst.setInt(4, carrinho.getId());
			pst.setInt(5, produto.getId());
	
			pst.executeUpdate();
			con.close();
			
			
			return (produtoRemovido == 1) ? "Item removido do carrinho com sucesso!" : "Item adicionado do carrinho novamento com sucesso!";
			
			} catch (SQLException e) {
				e.printStackTrace();
			    return "Erro ao remover item do carrinho: " + e;
			}			
	}
	
	public ArrayList<CarrinhoItens> ListarItensAtivos(CarrinhoDeCompras carrinho) {
		System.out.println("Acesso ao Dao ListarItensAtivos");

		ArrayList<CarrinhoItens> ArrayListcarrinhoItems = new ArrayList<>();
		String read = "SELECT car_itens_id, " + 
	              "car_itens_car_id, " + 
	              "car_itens_prod_id, " + 
	              "car_itens_prod_quant, " +
	              "car_itens_removido, " + 
	              "car_itens_motivo " + 
	              "FROM carrinho_itens " + 
	              "WHERE car_itens_car_id = ? AND car_itens_removido = 0";
		try {
			Connection con = Conexao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setInt(1, carrinho.getId());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Produtos produto = new Produtos();
				CarrinhoItens itens = new CarrinhoItens();

				produto.setId(rs.getInt(3));

				carrinho.setId(rs.getInt(2));

				itens.setCarrinho(carrinho);
				itens.setId(rs.getInt(1));
				itens.setProduto(produto);
				itens.setQuantProd(rs.getInt(4));
				itens.setRemovido(rs.getBoolean(5));
				itens.setMotivoRemocao(rs.getString(6));

				ArrayListcarrinhoItems.add(itens);
			}
			con.close();
			return ArrayListcarrinhoItems;
		} catch (Exception e) {
			System.out.println("ERRO AO LISTAR" + e);
			return null;
		}
	}

}
