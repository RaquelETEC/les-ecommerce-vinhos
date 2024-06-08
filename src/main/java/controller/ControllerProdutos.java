package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.entity.Categoria;
import model.entity.Precificacao;
import model.entity.Produtos;
import service.ProdutoService;


@WebServlet(urlPatterns = { "/paginaInical.html", 
		"/areaAdministrador/Produtos.html", 
		"/areaAdministrador/EditarProdutos.html",
		"/produtosDisponiveis.html", 
		"/buscaProduto.html"
		})

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
		else if(action.equals("/produtosDisponiveis.html")) {
			consultaProdutoDisponiveisController(request,response);
		}
		else if(action.equals("/buscaProduto.html")) {
			buscaProdutoController(request,response);
		}
	}
	
	private void buscaProdutoController(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("acesso consulta buscaProdutoController");
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        int id = Integer.parseInt(request.getParameter("id"));
        Produtos produto = new Produtos();
        
        produto = produtoservice.listarProdutoPorID(id);
        
        PrintWriter out = response.getWriter();
        new Gson().toJson(produto, out);
        out.close();
	}


	private void consultaProdutoDisponiveisController(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("acesso consulta produto disponivel");
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        List<Produtos> produtos = produtoservice.listarProdutosDisponiveis();
        PrintWriter out = response.getWriter();
        new Gson().toJson(produtos, out);
        out.close();
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
