package service;
import java.sql.SQLException;
import java.util.List;

import dao.DAOProdutos;
import dao.SalesDataDao;
import model.entity.SalesData;

public class SalesDataService {
	
	private SalesDataDao daoSalesData;
	
    public SalesDataService() {
        this.daoSalesData = new SalesDataDao();
    }
    
	public List<SalesData> listarDadosPedido(String productId, String startDate, String endDate) throws SQLException {
		return daoSalesData.fetchSalesData(productId, startDate, endDate);
	}
}
