package controller;

import com.google.gson.Gson;
import model.entity.Produtos;
import model.entity.SalesData;
import service.ProdutoService;
import service.SalesDataService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/data-analysis")
public class DataAnalysisController extends HttpServlet {

    private ProdutoService produtoService;
    private SalesDataService salesDataService;
    
    

    @Override
    public void init() throws ServletException {
        super.init();
        this.produtoService = new ProdutoService();
        this.salesDataService = new SalesDataService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        switch (action) {
            case "listProducts":
                listProducts(response);
                break;
            case "salesData":
			try {
				getSalesData(request, response);
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action not recognized");
                break;
        }
    }

    private void listProducts(HttpServletResponse response) throws IOException {
        List<Produtos> produtos = produtoService.listarProdutosDataAnalysis();
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        new Gson().toJson(produtos, out);
        out.close();
    }

    private void getSalesData(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String productId = request.getParameter("productId");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        List<SalesData> salesData = salesDataService.listarDadosPedido(productId, startDate, endDate);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        new Gson().toJson(salesData, out);
        out.close();
    }
}
