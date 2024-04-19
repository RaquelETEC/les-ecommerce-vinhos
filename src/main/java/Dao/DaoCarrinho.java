package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.entity.CarrinhoItens;
import model.entity.Cliente;
import model.entity.Produtos;
import model.entity.CarrinhoDeCompras;

public class DaoCarrinho {
	Connection con = Conexao.conectar();

	public CarrinhoDeCompras SelecionarCarrinho(Cliente cliente) {
		System.out.println("cheguei no SSelecionarCarrinho" + cliente.getId());
		CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

		String read = "select *  from carrinho_de_compras where car_cli_id = ?";
		try {
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
		String read = "SELECT car_itens_id," + "car_itens_car_id," + "car_itens_prod_id," + "car_itens_prod_quant,"
				+ "car_itens_removido," + "car_itens_motivo" + "FROM carrinho_itens" + "WHERE car_itens_car_id = ?";
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
