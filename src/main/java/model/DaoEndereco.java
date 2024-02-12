package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DaoEndereco {

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


    public void inserirEndereco(Cliente cliente, Endereco endereco) {
        System.out.println("DAO : id do cliente para o endereço:"+ cliente.getId());

		String create =   "INSERT INTO endereco" + //
                        "(end_cli_id,end_tipo_residencia,end_tipo_logradouro,end_logradouro,end_numero,"+
                        "end_bairro,end_cep,end_cidade, end_estado, end_pais,end_padrao,end_observacoes )" + //
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			Connection con = conectar();
            System.out.println("chegou no try no inserirEndereco" +cliente.getId() );
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
			pst.setString(12, endereco.getTipoResidencia());

			pst.executeUpdate(); 
			System.err.println("inserido endereço no dao!!");
			con.close();
		} catch (Exception e) {
			System.out.println("erro ao inserir Endereço: "+e);
		}
	}


}
