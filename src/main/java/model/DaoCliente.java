package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DaoCliente {

	private String driver = "com.mysql.cj.jdbc.Driver";

	//definindo ip, user e senha e horario  do servidor universal 
	private String url = "jdbc:mysql://localhost:3306/ecommerce?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "12345";

    private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println("ERRO: Connection conectar()"+ e);
			return null;
		}
	}

    public void inserirCliente(Cliente cliente) {
		String create =   "INSERT INTO cliente" + //
                        "(cli_nome,cli_email,cli_senha,cli_cpf,cli_telefone_tipo,cli_telefone,cli_dt_nascimento,cli_genero)" + //
                        "VALUES (?,?,?,?,?,?,?,?)";
                      
		try {
			Connection con = conectar();
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
    
        try (Connection con = conectar();
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


}
