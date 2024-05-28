package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.entity.CarrinhoItens;
import model.entity.Cliente;
import model.entity.Produtos;
import model.entity.StatusCarrinhoItens;
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
	
	public ArrayList<CarrinhoItens> listarCarrinho(CarrinhoDeCompras carrinho) {
	    System.out.println("Acesso ao Dao ListarCarrinho");

	    ArrayList<CarrinhoItens> listaCarrinhoItens = new ArrayList<>();
	    String read = "SELECT car_itens_id, " + 
	                  "car_itens_car_id, " + 
	                  "car_itens_prod_id, " + 
	                  "car_itens_prod_quant, " +
	                  "car_itens_status, " + 
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
	            CarrinhoItens item = new CarrinhoItens();

	            produto.setId(rs.getInt(3));
	            carrinho.setId(rs.getInt(2));
	            
	            // Usando ordinal() para mapear o valor do banco de dados para o enum
	            int statusDBIndex = rs.getInt(5);
	            StatusCarrinhoItens status = StatusCarrinhoItens.values()[statusDBIndex];
	            
	            item.setCarrinho(carrinho);
	            item.setId(rs.getInt(1));
	            item.setProduto(produto);
	            item.setQuantProd(rs.getInt(4));
	            item.setStatus(status); // Atribuindo o status ao objeto CarrinhoItens
	            item.setMotivoRemocao(rs.getString(6));

	            listaCarrinhoItens.add(item);
	        }
	        con.close();
	    } catch (SQLException e) {
	        System.out.println("ERRO AO LISTAR" + e);
	        // Lidar com exceções de banco de dados aqui
	    }
	    return listaCarrinhoItens;
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
	    String selectQuery = "SELECT car_itens_prod_quant, car_itens_status FROM carrinho_itens WHERE car_itens_car_id = ? AND car_itens_prod_id = ? and car_itens_status <> 2";
	    String updateQuery = "UPDATE carrinho_itens SET car_itens_prod_quant = ?, car_itens_status = 0 WHERE car_itens_car_id = ? AND car_itens_prod_id = ?";
	    String insertQuery = "INSERT INTO carrinho_itens (car_itens_car_id, car_itens_prod_id, car_itens_prod_quant, car_itens_status) VALUES (?, ?, ?, 0)";

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
	            int statusDBIndex = rs.getInt("car_itens_status");
	            StatusCarrinhoItens status = StatusCarrinhoItens.values()[statusDBIndex];
	            int novaQuantidade = quantidadeAtual + quantidade;
	            
	            // Atualizar a quantidade e o status do produto
	            updatePst.setInt(1, novaQuantidade);
	            updatePst.setInt(2, carrinho.getId());
	            updatePst.setInt(3, produto.getId());
	            updatePst.executeUpdate();
	            
	            if (status.ordinal() != 0) { // Se o status não for 0 (não adicionado)
	                // Se não foi adicionado anteriormente, atualizar o status para 0 (adicionado)
	                String updateStatusQuery = "UPDATE carrinho_itens SET car_itens_status = 0 WHERE car_itens_car_id = ? AND car_itens_prod_id = ?";
	                try (PreparedStatement updateStatusPst = con.prepareStatement(updateStatusQuery)) {
	                    updateStatusPst.setInt(1, carrinho.getId());
	                    updateStatusPst.setInt(2, produto.getId());
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

	public String alterarStatusCarrinho(CarrinhoItens carrinhoItens) {
	    String read = "UPDATE carrinho_itens SET " 
	                + "car_itens_status = ?, "
	                + "car_itens_prod_quant = ?, "      
	                + "car_itens_motivo = ? " 
	                + "WHERE car_itens_car_id = ? "
	                + "and car_itens_prod_id = ? "
	                +"and car_itens_id = ? ";
	    try {
	        Connection con = Conexao.conectar();
	        PreparedStatement pst = con.prepareStatement(read);
	        
	        pst.setInt(1, carrinhoItens.getStatus().ordinal());
	        pst.setInt(2, carrinhoItens.getQuantProd());
	        pst.setString(3, carrinhoItens.getMotivoRemocao());
	        pst.setInt(4, carrinhoItens.getCarrinho().getId());
	        pst.setInt(5, carrinhoItens.getProduto().getId());
	        pst.setInt(6, carrinhoItens.getId());


	        pst.executeUpdate();
	        con.close();
	        
	        return (carrinhoItens.getStatus() == StatusCarrinhoItens.REMOVIDO) ? "Item removido do carrinho com sucesso!" 
	        	    : (carrinhoItens.getStatus() == StatusCarrinhoItens.ADICIONADO) ? "Item adicionado do carrinho com sucesso!"
	        	    : "Status do item atualizado com sucesso!";	        
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "Erro ao alterar status item do carrinho: " + e;
	    }           
	}
	
	public ArrayList<CarrinhoItens> listarItensAtivos(CarrinhoDeCompras carrinho) {
	    System.out.println("Acesso ao Dao ListarItensAtivos");

	    ArrayList<CarrinhoItens> listaCarrinhoItens = new ArrayList<>();
	    String read = "SELECT car_itens_id, " + 
	                  "car_itens_car_id, " + 
	                  "car_itens_prod_id, " + 
	                  "car_itens_prod_quant, " +
	                  "car_itens_status, " + 
	                  "car_itens_motivo " + 
	                  "FROM carrinho_itens " + 
	                  "WHERE car_itens_car_id = ? AND car_itens_status = 0";
	    try {
	        Connection con = Conexao.conectar();
	        PreparedStatement pst = con.prepareStatement(read);
	        
	        pst.setInt(1, carrinho.getId());
	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            Produtos produto = new Produtos();
	            CarrinhoItens item = new CarrinhoItens();

	            produto.setId(rs.getInt(3));
	            carrinho.setId(rs.getInt(2));
	            
	            // Usando ordinal() para mapear o valor do banco de dados para o enum
	            int statusDBIndex = rs.getInt(5);
	            StatusCarrinhoItens status = StatusCarrinhoItens.values()[statusDBIndex];
	            
	            item.setCarrinho(carrinho);
	            item.setId(rs.getInt(1));
	            item.setProduto(produto);
	            item.setQuantProd(rs.getInt(4));
	            item.setStatus(status); // Atribuindo o status ao objeto CarrinhoItens
	            item.setMotivoRemocao(rs.getString(6));

	            listaCarrinhoItens.add(item);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Lidar com exceções de banco de dados aqui
	    } finally {
	        // Fechar recursos como conexão, instrução e conjunto de resultados
	    }
	    return listaCarrinhoItens;
	}
}
