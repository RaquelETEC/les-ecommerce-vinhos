package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.TiposEndereco;

public class DaoEndereco {

	public String inserirEndereco(Cliente cliente, Endereco endereco) {
		System.out.println("DAO : id do cliente para o endereço:" + cliente.getId());

		String create = "INSERT INTO endereco (" + "end_cli_id," + "end_tipo_residencia," + "end_tipo_logradouro,"
				+ "end_logradouro," + "end_numero," + "end_bairro," + "end_cep," + "end_cidade," + "end_estado, "
				+ "end_pais," + "end_padrao," + "end_observacoes," + "end_nome," + "end_tipo"
				+ " ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			Connection con = Conexao.conectar();
			System.out.println("chegou no try no inserirEndereco" + cliente.getId());
			PreparedStatement pst = con.prepareStatement(create);
			pst.setInt(1, cliente.getId());
			pst.setString(2, endereco.getTipoResidencia());
			pst.setString(3, endereco.getTipoLogradouro());
			pst.setString(4, endereco.getLogradouro());
			pst.setString(5, endereco.getNumero());
			pst.setString(6, endereco.getBairro());
			pst.setString(7, endereco.getCep());
			pst.setString(8, endereco.getCidade());
			pst.setString(9, endereco.getEstado());
			pst.setString(10, endereco.getPais());
			pst.setString(11, endereco.getPadrao());
			pst.setString(12, endereco.getObservacao());
			pst.setString(13, endereco.getNome());
			pst.setString(14, endereco.getTipos().name());

			pst.executeUpdate();
			System.err.println("inserido endereco no dao!!");
			con.close();
			return "Sucesso";

		} catch (Exception e) {
			System.out.println("erro ao inserir Endereco: " + e);
			return "Erro";
		}
	}

	public ArrayList<Endereco> ListarEnderecos(Cliente cliente) {
		ArrayList<Endereco> listaDeEnderecos = new ArrayList<>();
		String read = "SELECT end_id," + "end_cli_id," + "end_tipo_residencia," + "end_tipo_logradouro,"
				+ "end_logradouro," + "end_numero," + "end_bairro," + "end_cep," + "end_cidade," + "end_estado,"
				+ "end_pais," + "end_padrao," + "end_observacoes," + "end_nome," + "end_tipo "
				+ "FROM ecommerce.endereco " + "WHERE end_cli_id = ?";
		try {
			Connection con = Conexao.conectar();
			PreparedStatement pst = con.prepareStatement(read);

			pst.setInt(1, cliente.getId());

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Endereco endereco = new Endereco();

				endereco.setId(rs.getInt("end_id"));
				endereco.setCliente(cliente);
				endereco.setTipoResidencia(rs.getString("end_tipo_residencia"));
				endereco.setTipoLogradouro(rs.getString("end_tipo_logradouro"));
				endereco.setLogradouro(rs.getString("end_logradouro"));
				endereco.setNumero(rs.getString("end_numero"));
				endereco.setBairro(rs.getString("end_bairro"));
				endereco.setCep(rs.getString("end_cep"));
				endereco.setCidade(rs.getString("end_cidade"));
				endereco.setEstado(rs.getString("end_estado"));
				endereco.setPais(rs.getString("end_pais"));
				endereco.setPadrao(rs.getString("end_padrao"));
				endereco.setObservacao(rs.getString("end_observacoes"));
				endereco.setNome(rs.getString("end_nome"));

				// Converter a string do tipo de endereço para o enum correspondente
				String tipoEnderecoString = rs.getString("end_tipo");
				TiposEndereco tipoEndereco = TiposEndereco.valueOf(tipoEnderecoString);
				endereco.setTipos(tipoEndereco);

				listaDeEnderecos.add(endereco);
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Erro ao listar endereços: " + e);
		}
		return listaDeEnderecos;
	}

	public Endereco selecionarEndereco(Cliente cliente, Endereco endereco) {
		String read = "SELECT " + "end_cli_id," + "end_tipo_residencia," + "end_tipo_logradouro," + "end_logradouro,"
				+ "end_numero," + "end_bairro," + "end_cep," + "end_cidade," + "end_estado," + "end_pais,"
				+ "end_padrao," + "end_observacoes," + "end_nome," + "end_tipo " + "FROM ecommerce.endereco "
				+ "WHERE end_cli_id = ? and end_id = ?";
		try {
			Connection con = Conexao.conectar();
			PreparedStatement pst = con.prepareStatement(read);

			pst.setInt(1, cliente.getId());
			pst.setInt(2, endereco.getId());

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				endereco.setCliente(cliente);
				endereco.setTipoResidencia(rs.getString("end_tipo_residencia"));
				endereco.setTipoLogradouro(rs.getString("end_tipo_logradouro"));
				endereco.setLogradouro(rs.getString("end_logradouro"));
				endereco.setNumero(rs.getString("end_numero"));
				endereco.setBairro(rs.getString("end_bairro"));
				endereco.setCep(rs.getString("end_cep"));
				endereco.setCidade(rs.getString("end_cidade"));
				endereco.setEstado(rs.getString("end_estado"));
				endereco.setPais(rs.getString("end_pais"));
				endereco.setPadrao(rs.getString("end_padrao"));
				endereco.setObservacao(rs.getString("end_observacoes"));
				endereco.setNome(rs.getString("end_nome"));

				// Converter a string do tipo de endereço para o enum correspondente
				String tipoEnderecoString = rs.getString("end_tipo");
				TiposEndereco tipoEndereco = TiposEndereco.valueOf(tipoEnderecoString);
				endereco.setTipos(tipoEndereco);

			}
			con.close();
		} catch (Exception e) {
			System.out.println("Erro ao listar endereços: " + e);
		}
		return endereco;
	}

	public String EditarEndereco(Cliente cliente, Endereco endereco) {
		System.out.println("DAO : id do cliente para o endereço:" + cliente.getId());
		System.out.println("DAO : id do endereço para o endereço:" + endereco.getId());

		System.out.println(endereco.getBairro() + "," + endereco.getCep());
		String update = "UPDATE endereco SET " + "end_tipo_residencia = ?, " + "end_tipo_logradouro = ?, "
				+ "end_logradouro = ?, " + "end_numero = ?, " + "end_bairro = ?, " + "end_cep = ?, "
				+ "end_cidade = ?, " + "end_estado = ?, " + "end_pais = ?, " + "end_padrao = ?, "
				+ "end_observacoes = ?, " + "end_nome = ?, " + "end_tipo = ? " + "WHERE end_id = ?";

		try {
			Connection con = Conexao.conectar();
			System.out.println("chegou no try no editarEndereco " + cliente.getId());
			PreparedStatement pst = con.prepareStatement(update);

			pst.setString(1, endereco.getTipoResidencia());
			pst.setString(2, endereco.getTipoLogradouro());
			pst.setString(3, endereco.getLogradouro());
			pst.setString(4, endereco.getNumero());
			pst.setString(5, endereco.getBairro());
			pst.setString(6, endereco.getCep());
			pst.setString(7, endereco.getCidade());
			pst.setString(8, endereco.getEstado());
			pst.setString(9, endereco.getPais());
			pst.setString(10, endereco.getPadrao());
			pst.setString(11, endereco.getObservacao());
			pst.setString(12, endereco.getNome());
			pst.setString(13, endereco.getTipos().name());
			pst.setInt(14, endereco.getId()); // Definindo o ID do endereço na cláusula WHERE

			// Imprimindo os valores que estão sendo setados
			System.out.println("Valores a serem definidos na consulta SQL:");
			System.out.println("Tipo Residência: " + endereco.getTipoResidencia());
			System.out.println("Tipo Logradouro: " + endereco.getTipoLogradouro());
			System.out.println("Logradouro: " + endereco.getLogradouro());
			System.out.println("Número: " + endereco.getNumero());
			System.out.println("Bairro: " + endereco.getBairro());
			System.out.println("CEP: " + endereco.getCep());
			System.out.println("Cidade: " + endereco.getCidade());
			System.out.println("Estado: " + endereco.getEstado());
			System.out.println("País: " + endereco.getPais());
			System.out.println("Padrão: " + endereco.getPadrao());
			System.out.println("Observações: " + endereco.getObservacao());
			System.out.println("Nome: " + endereco.getNome());
			System.out.println("Tipo: " + endereco.getTipos().name());
			System.out.println("ID do Endereço: " + endereco.getId());

			pst.executeUpdate();
			System.err.println("Endereço atualizado no DAO!!");
			con.close();
			return "Sucesso";

		} catch (Exception e) {
			System.out.println("Erro ao editar Endereço: " + e);
			return "Erro";
		}

	}

	public String ExcluirEndereco(Cliente cliente, Endereco endereco) {
		System.out.println("DAO : id do cliente para o endereço:" + cliente.getId());
		System.out.println("DAO : id do endereço para o endereço:" + endereco.getId());

		System.out.println(endereco.getBairro() + "," + endereco.getCep());
		String delete = " DELETE FROM ENDERECO WHERE end_id = ?";

		try {
			Connection con = Conexao.conectar();
			System.out.println("chegou no try no ExcluirEndereco " + cliente.getId());
			PreparedStatement pst = con.prepareStatement(delete);

			pst.setInt(1, endereco.getId());

			pst.executeUpdate();
			System.err.println("Endereço excluido no DAO!!");
			con.close();
			return "Sucesso";

		} catch (Exception e) {
			System.out.println("Erro ao excluir Endereço: " + e);
			return "Erro";
		}
	}

}
