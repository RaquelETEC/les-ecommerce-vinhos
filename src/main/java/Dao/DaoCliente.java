package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.entity.Cliente;

public class DaoCliente {

	public int inserirCliente(Cliente cliente) {
		String create = "INSERT INTO cliente" + //
				"(cli_nome,cli_email,cli_senha,cli_cpf,cli_telefone_tipo,cli_telefone,cli_dt_nascimento,cli_genero, cli_status)"
				+ //
				"VALUES (?,?,?,?,?,?,?,?,?)";

		try {
			Connection con = Conexao.conectar();
			PreparedStatement pst = con.prepareStatement(create, Statement.RETURN_GENERATED_KEYS); // Adicione
																									// Statement.RETURN_GENERATED_KEYS
																									// aqui
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getEmail());
			pst.setString(3, cliente.getSenha());
			pst.setString(4, cliente.getCpf());
			pst.setString(5, cliente.getTipoTelefone());
			pst.setString(6, cliente.getTelefone());
			pst.setDate(7, new java.sql.Date(cliente.getDataNasc().getTime()));
			pst.setString(8, cliente.getGenero());
			pst.setString(9, cliente.getStatus());

			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			int idCliente = -1; // Valor padr�o se n�o houver chaves geradas
			if (rs.next()) {
				idCliente = rs.getInt(1); // Obtendo o ID gerado
			}

			con.close();
			return idCliente;
		} catch (Exception e) {
			System.out.println("Erro ao inserir cliente: " + e);
			return -1; // Retornar um valor de erro
		}
	}

	public ArrayList<Cliente> ListarCliente() {
		System.out.println("ESTOU NO DAO CLIENTE ARRAY");

		ArrayList<Cliente> Arrayclientes = new ArrayList<>();
		String read = "select cli_id, cli_nome,cli_telefone,cli_email,cli_cpf,cli_telefone_tipo,cli_dt_nascimento,cli_genero,cli_status from cliente";
		try {
			Connection con = Conexao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				String telefone = rs.getString(3);
				String email = rs.getString(4);
				String cpf = rs.getString(5);
				String tipoTelefone = rs.getString(6);
				Date dataNasc = rs.getDate(7);
				String genero = rs.getString(8);
				String status = rs.getString(9);

				Cliente Instaclientes = new Cliente();
				Instaclientes.setId(id);
				Instaclientes.setNome(nome);
				Instaclientes.setEmail(email);
				Instaclientes.setCpf(cpf);
				Instaclientes.setTipoTelefone(tipoTelefone);
				Instaclientes.setDataNasc(dataNasc);
				Instaclientes.setTelefone(telefone);
				Instaclientes.setGenero(genero);
				Instaclientes.setStatus(status);
				Arrayclientes.add(Instaclientes);
			}
			con.close();
			return Arrayclientes;
		} catch (Exception e) {
			System.out.println("ERRO AO LISTAR" + e);
			return null;
		}

	}

	public Cliente selecionarCliente(Cliente cliente) {
		System.out.println("cheguei no SELECIONAR cliente DAO" + cliente.getId());

		String read2 = "select *  from cliente where cli_id = ?";
		try {
			Connection con = Conexao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setInt(1, cliente.getId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				cliente.setId(rs.getInt(1));
				cliente.setNome(rs.getString(2));
				cliente.setEmail(rs.getString(3));
				cliente.setSenha(rs.getString(4));
				cliente.setCpf(rs.getString(5));
				cliente.setTipoTelefone(rs.getString(6));
				cliente.setTelefone(rs.getString(7));
				cliente.setDataNasc(rs.getDate(8));
				cliente.setGenero(rs.getString(9));
				cliente.setStatus(rs.getString(10));

			}
			con.close();
		} catch (Exception e) {
			System.out.println("ERRO AO SELECIONAR" + e);
		}
		return cliente;

	}

	public String alterarCliente(Cliente cliente) {
		System.out.println("cheguei no alterar cliente" + cliente.getNome());

		String update = "update cliente set " + "cli_nome=?," // 1
				+ "cli_email=?," // 2
				+ "cli_cpf=?," // 3
				+ "cli_dt_nascimento=?," // 4
				+ "cli_telefone_tipo=?, " // 5
				+ "cli_telefone=?," // 6
				+ "cli_genero=?, " // 7
				+ "cli_status=? " // 8
				+ "where cli_id=?"; // 9
		try {
			// Formatar a data no formato 'yyyy-MM-dd'
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dataNascimentoFormatted = sdf.format(cliente.getDataNasc());

			Connection con = Conexao.conectar();
			PreparedStatement pst = con.prepareStatement(update);

			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getEmail());
			pst.setString(3, cliente.getCpf());
			pst.setString(4, dataNascimentoFormatted);
			pst.setString(5, cliente.getTipoTelefone());
			pst.setString(6, cliente.getTelefone());
			pst.setString(7, cliente.getGenero());
			pst.setString(8, cliente.getStatus());
			pst.setInt(9, cliente.getId());
			pst.executeUpdate();
			con.close();

		} catch (Exception e) {
			return ("Erro ao alterar cliente" + e);
		}
		return "Cliente alterado com sucesso" + cliente.getNome();
	}

	public String deletarCliente(Cliente cliente) {
		String delete = "delete from cliente where cli_id=?";

		System.out.println("aiai eu cheguei aqui " + cliente.getId());
		try {
			Connection con = Conexao.conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setInt(1, cliente.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			return "Erro ao cadastrar cliente: " + e;
		}
		return "Cliente Apagado com sucesso";
	}
	
	public String AlterarSenha(Cliente cliente) {
		System.out.println("cheguei no alterar senha DAO" + cliente.getNome());

		String update = "update cliente set cli_senha=?  where cli_id=?"; // 9
		try {

			Connection con = Conexao.conectar();
			PreparedStatement pst = con.prepareStatement(update);

			pst.setString(1, cliente.getSenha());
			pst.setInt(2, cliente.getId());
			
			pst.executeUpdate();
			con.close();

		} catch (Exception e) {
			return ("Erro ao alterar senha do cliente \n" + e);
		}
		return "Senha alterada com sucesso!";
	}
	
	public Cliente buscarClientePorId(int clienteId) {
        Cliente cliente = null;
        String read = "SELECT * FROM cliente WHERE cli_id = ?";
        try {
            Connection con = Conexao.conectar();
            PreparedStatement pst = con.prepareStatement(read);
            pst.setInt(1, clienteId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("cli_id"));
                cliente.setNome(rs.getString("cli_nome"));
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao buscar cliente: " + e);
        }
        return cliente;
    }

}
