package dao;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.PedidoItens;
import model.entity.PedidoVenda;
import model.entity.Produtos;

import model.entity.SalesData;

public class SalesDataDao {
	
	public List<SalesData> fetchSalesData(String productId, String startDate, String endDate) throws SQLException {
	    List<SalesData> salesDataList = new ArrayList<>();
	    Connection con = null;

	    try {
	        con = Conexao.conectar();
	        
	        String query = "SELECT "
	                    + "DATE_FORMAT(p.ven_data, '%Y-%m') AS month_year, "
	                    + "pi.ped_item_prod_desc AS productName, "
	                    + "SUM(pi.ped_item_prod_quantidade) AS volume,"
	                    + "SUM(pi.ped_item_prod_valor_total) valorTotal "
	                    + "FROM pedido_venda p "
	                    + "JOIN pedido_itens pi ON pi.ped_item_ven_id = p.ven_id "
	                    + "WHERE (? IS NULL OR pi.ped_item_prod_id = ?) "
	                    + "AND p.ven_data BETWEEN ? AND ? "
	                    + "GROUP BY 1,2 "
	                    + "ORDER BY DATE_FORMAT(p.ven_data, '%Y-%m') ";
	        	
	        PreparedStatement statement = con.prepareStatement(query);
	        
	        // Define o par�metro productId
	        statement.setString(1, productId);
	        statement.setString(2, productId);
	        
	        // Define os par�metros de data
	        statement.setString(3, startDate);
	        statement.setString(4, endDate);
	        
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            String monthYear = resultSet.getString("month_year");
	            int volume = resultSet.getInt("volume");
	            String productName = resultSet.getString("productName");
	            Double valor = resultSet.getDouble("valorTotal");
	            
	            SalesData data = new SalesData(monthYear, volume, productName, valor);
	            salesDataList.add(data);
	        }
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        con.close();
	    }
	    return salesDataList;
	}
}
