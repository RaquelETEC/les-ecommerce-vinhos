package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import dao.Conexao;
import model.entity.BandeiraCartao;
import model.entity.CartaoDeCredito;
import model.entity.Cliente;
import model.entity.Cupons;
import model.entity.Notificacoes;

public class DaoCupons {

	public ArrayList<Cupons> ListarCupons(Cliente cliente) {
		ArrayList<Cupons> listaDeCupons = new ArrayList<>();
		String read = "SELECT " + "cup_id, " + "cup_codigo, " + "cup_desc, " + "cup_img, " + "cup_tipo, "
				+ "cup_valor, " + "cup_validade " + "FROM cupons c "
				+ "INNER JOIN cliente_cupom_relacionamento cr ON c.cup_id = cr.cupom_id " + "WHERE cr.cliente_id = ?";
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

	public String gerarCupom(String codigo, String descricao, String img, String tipoCupom, Double valorCupom,
			Date validade, int usado) {
		String insert = "INSERT INTO cupons (cup_codigo, cup_desc, cup_img, cup_tipo, cup_valor, cup_validade, cup_usado) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		int idCupomGerado = -1; // Valor padrão para indicar que nenhum cupom foi gerado

		try (Connection con = Conexao.conectar();
				PreparedStatement pst = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS)) {

			pst.setString(1, codigo);
			pst.setString(2, descricao);
			pst.setString(3, img);
			pst.setString(4, tipoCupom);
			pst.setDouble(5, valorCupom);
			pst.setDate(6, new java.sql.Date(validade.getTime())); // Convertendo a data para o tipo SQL
			pst.setInt(7, usado);

			int linhasAfetadas = pst.executeUpdate();

			if (linhasAfetadas > 0) {
				ResultSet rs = pst.getGeneratedKeys();
				if (rs.next()) {
					idCupomGerado = rs.getInt(1); // Obtém o ID gerado
				}
			}
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return "error: " + e;
		}
		System.out.println("cupom gerado:" + idCupomGerado);
		return "" + idCupomGerado;
	}

	public String vincularCupomAoCliente(Cupons cupom, Cliente cliente) {
		String insert = "INSERT INTO cliente_cupom_relacionamento (cupom_id, cliente_id) " + "VALUES (?, ?)";
		String resposta = "";

		try (Connection con = Conexao.conectar(); PreparedStatement pst = con.prepareStatement(insert)) {

			pst.setInt(1, cupom.getId());
			pst.setInt(2, cliente.getId());

			int linhasAfetadas = pst.executeUpdate();
			con.close();
			resposta = (linhasAfetadas > 0) ? "success" : "error: Nenhuma linha afetada";

		} catch (SQLException e) {
			e.printStackTrace();
			resposta = "error: " + e;
		}

		return resposta;
	}

	public String gerarNotificacao(Notificacoes notificacao) {
		String insert = "INSERT INTO notificacoes (titulo, descricao, data, cliente_id) " + "VALUES (?, ?, ?)";
		String resposta = "";

		try (Connection con = Conexao.conectar(); PreparedStatement pst = con.prepareStatement(insert)) {

			pst.setString(1, notificacao.getTitulo());
			pst.setString(2, notificacao.getDescricao());
			pst.setDate(3, notificacao.getData());

			if (notificacao.getCliente().getId() != 0)
				pst.setInt(4, notificacao.getCliente().getId());

			int linhasAfetadas = pst.executeUpdate();
			con.close();

			resposta = (linhasAfetadas > 0) ? "success" : "error: Nenhuma linha afetada";

		} catch (SQLException e) {
			e.printStackTrace();
			resposta = "error: " + e;
		}

		return resposta;

	}

}