package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Categoria;
import model.entity.Precificacao;
import model.entity.Produtos;
import service.ProdutoService;


@WebServlet(urlPatterns = { "/paginaInical.html", "/areaAdministrador/Produtos.html", "/areaAdministrador/EditarProdutos.html"})

public class ControllerProdutos extends HttpServlet{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	Produtos produtos = new Produtos();
	Precificacao precificacao = new Precificacao();
	Categoria categoria = new Categoria();
	
	ProdutoService produtoservice = new ProdutoService();
	
	public ControllerProdutos() {
		super();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		System.out.println("chegou aqui: " + action);

		if (action.equals("/paginaInical.html")) {
			AreaProdutos(request,response);
		} 
		else if (action.equals("/areaAdministrador/Produtos.html")) {
			AreaProdutosAreaADM(request,response);
		} 
		else if (action.equals("/areaAdministrador/EditarProdutos.html")) {
			TelaEditarProduto(request,response);
		} 
	}
	
	protected void AreaProdutos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Cheguei no area produtos");
		
		ArrayList<Produtos> lista = produtoservice.listarProdutos();
		
		request.setAttribute("listaProdutos", lista);
	
		RequestDispatcher rd = request.getRequestDispatcher("paginaInicial.jsp");
		rd.forward(request, response);
	
	}
	
	protected void TelaEditarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("voce conseguiu chegar aqui no EditarProduto:)");
		
		int idProduto = Integer.parseInt(request.getParameter("idProduto"));
		String PrecificacaoDesc = request.getParameter("PrecificacaoDesc");
		String CategoriaStatus = request.getParameter("CategoriaStatus");
		
		produtos.setId(idProduto);
		precificacao.setDesc(PrecificacaoDesc);
		categoria.setStatus(CategoriaStatus);
		
		System.out.println("o id do produto para servlet: " + produtos.getId() + "e" + precificacao.getId()
		+ "e" + categoria.getId());
		
		request.setAttribute("idProduto", idProduto);;
		request.setAttribute("PrecificacaoDesc", PrecificacaoDesc);
		request.setAttribute("CategoriaStatus", CategoriaStatus);
		
		produtos = produtoservice.selecionarProduto(produtos, precificacao, categoria);
		
		request.setAttribute("produtos", produtos);
		
		RequestDispatcher rd = request.getRequestDispatcher("/areaAdministrador/ProdutosEditar.jsp");
		rd.forward(request, response);
	}
	
	
	protected void AreaProdutosAreaADM(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Cheguei no area produtos do ADM");
		
		ArrayList<Produtos> lista = produtoservice.listarProdutosAreaADM();
		
		request.setAttribute("listaProdutosADM", lista);
	
		RequestDispatcher rd = request.getRequestDispatcher("/areaAdministrador/Produtos.jsp");
		rd.forward(request, response);
	
	}
	
	
}
