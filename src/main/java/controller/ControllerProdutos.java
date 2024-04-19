package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.ProdutoService;
import model.entity.Produtos;


@WebServlet(urlPatterns = { "/Produtos.html"})

public class ControllerProdutos extends HttpServlet{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	Produtos produtos = new Produtos();
	
	ProdutoService produtoservice = new ProdutoService();
	
	public ControllerProdutos() {
		super();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		System.out.println("chegou aqui: " + action);

		if (action.equals("/Produtos.html")) {
			AreaProdutos(request,response);
		} 

	}
	
	
	protected void AreaProdutos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Cheguei no area produtos");
		
		ArrayList<Produtos> lista = produtoservice.listarProdutos();
		
		request.setAttribute("listaProdutos", lista);
	
		RequestDispatcher rd = request.getRequestDispatcher("/Produtos.jsp");
		rd.forward(request, response);
		
		

	}
	
	
	
}