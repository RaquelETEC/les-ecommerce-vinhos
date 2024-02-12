package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
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
}
