package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.entity.Cliente;

public class DaoCliente {


    public void inserirCliente(Cliente cliente) {
		String create =   "INSERT INTO cliente" + //
                        "(cli_nome,cli_email,cli_senha,cli_cpf,cli_telefone_tipo,cli_telefone,cli_dt_nascimento,cli_genero, cli_status)" + //
                        "VALUES (?,?,?,?,?,?,?,?,?)";
                      
		try {
			Connection con = Conexao.conectar();
            System.out.println("chegou no try no inserir cliente");
			PreparedStatement pst = con.prepareStatement(create);
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
			System.err.println("inserido no dao!!");
			con.close();
		} catch (Exception e) {
			System.out.println("erro ao inserir cliente: "+e);
		}
	}

    public String selectIdCliente(Cliente cliente) {
        String id_cliente = null;
        String read = "SELECT cli_id FROM cliente where  cli_email = ? AND cli_senha = ? AND cli_cpf = ? order by cli_id desc LIMIT 1;";
    
        try (Connection con = Conexao.conectar();

             PreparedStatement pst = con.prepareStatement(read)) {
    
            pst.setString(1, cliente.getEmail());
            pst.setString(2, cliente.getSenha());
            pst.setString(3, cliente.getCpf());
    
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    id_cliente = String.valueOf(rs.getInt("cli_id"));
                }
            }
    
        } catch (Exception e) {
            System.out.println("Erro ao recuperar ID do cliente: " + e);
        }
    
        return id_cliente;
    }
    
    public ArrayList<Cliente> ListarCliente(){
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
    
	public void selecionarCliente(Cliente cliente) {
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
		
	}
	
	public void alterarCliente(Cliente cliente) {
		System.out.println("cheguei no alterar cliente"+ cliente.getNome());
		
		String update = "update cliente set "
				+ "cli_nome=?," //1
				+ "cli_email=?," //2
				+ "cli_cpf=?," //3
				+ "cli_dt_nascimento=?," //4
				+ "cli_telefone_tipo=?, " //5
				+ "cli_telefone=?," //6
				+ "cli_genero=?, " //7
				+ "cli_status=? " //8
				+ "where cli_id=?"; //9
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
		        
		        System.out.println("CLIENTE ATUALIZADO COM SUCESSO" + cliente.getNome());
		    } catch (Exception e) {
		        System.out.println("ERRO AO SELECIONAR" + e);
		    }
		}

	public void deletarCliente(Cliente cliente) {
		String delete = "delete from cliente where cli_id=?";
		
		System.out.println("aiai eu cheguei aqui "+ cliente.getId());
		try {
	        Connection con = Conexao.conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setInt(1, cliente.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
