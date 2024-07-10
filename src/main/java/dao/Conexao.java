package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	private static String driver = "com.mysql.cj.jdbc.Driver";

	// BANCO DE DADOS LOCAL
	// private static String url =
	// "jdbc:mysql://localhost:3306/ecommerce?useTimezone=true&serverTimezone=UTC";
	// private static String user = "root";
	// private static String password = "12345";

	// BANCO DE DADOS PRODUÇÃO
	private static String url = "jdbc:mysql://db-cr-wines.cbmaesa0gr8n.us-east-1.rds.amazonaws.com:3306/ecommerce?useTimezone=true&serverTimezone=UTC";
	private static String user = "root";
	private static String password = "wine12345";

	public static Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println("ERRO: Connection conectar()" + e);
			return null;
		}
	}
}
