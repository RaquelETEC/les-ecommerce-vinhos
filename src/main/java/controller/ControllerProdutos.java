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
import model.entity.Harmonizacao;
import model.entity.Precificacao;
import model.entity.Produtos;
import service.ProdutoService;

@WebServlet(urlPatterns = { "/paginaInical.html", "/areaAdministrador/Produtos.html",
		"/areaAdministrador/EditarProdutos.html", "/produtosDisponiveis.html", "/buscaProduto.html",
		"/areaAdministrador/EditarProdutos", "/areaAdministrador/AreaHarmonizacao" })

public class ControllerProdutos extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	Produtos produtos = new Produtos();
	Precificacao precificacao = new Precificacao();
	Categoria categoria = new Categoria();
	Harmonizacao harmonizacao = new Harmonizacao();
	ProdutoService produtoservice = new ProdutoService();

	public ControllerProdutos() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		System.out.println("chegou aqui: " + action);

		if (action.equals("/paginaInical.html")) {
			AreaProdutos(request, response);
		} else if (action.equals("/areaAdministrador/Produtos.html")) {
			AreaProdutosAreaADM(request, response);
		} else if (action.equals("/areaAdministrador/EditarProdutos.html")) {
			TelaEditarProduto(request, response);
		} else if (action.equals("/produtosDisponiveis.html")) {
			consultaProdutoDisponiveisController(request, response);
		} else if (action.equals("/buscaProduto.html")) {
			buscaProdutoController(request, response);
		} else if (action.equals("/areaAdministrador/EditarProdutos")) {
			EditarProduto(request, response);
		} else if (action.equals("/areaAdministrador/AreaHarmonizacao")) {
			AreaHarmonizacao(request, response);
		}
	}

	private void AreaHarmonizacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idProduto = Integer.parseInt(request.getParameter("idProduto"));
		produtos.setId(idProduto);

		ArrayList<Harmonizacao> lista = produtoservice.getHarmonizacoesByProduto(produtos);
		
		request.setAttribute("listaHarmonizacao", lista);
		request.setAttribute("idProduto", idProduto);

		RequestDispatcher rd = request.getRequestDispatcher("/areaAdministrador/ProdutosEditar.jsp");
		rd.forward(request, response);
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

	private void consultaProdutoDisponiveisController(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
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

		System.out.println("o id do produto para servlet: " + produtos.getId() + "e" + precificacao.getDesc() + "e"
				+ categoria.getStatus());

		request.setAttribute("idProduto", idProduto);
		;
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

	private void EditarProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("voce conseguiu chegar aqui no EditarProduto:)");

		int idProduto = Integer.parseInt(request.getParameter("idProduto"));
		String PrecificacaoDesc = request.getParameter("PrecificacaoDesc");
		String CategoriaStatus = request.getParameter("CategoriaStatus");

		produtos.setId(idProduto);
		precificacao.setDesc(PrecificacaoDesc);
		categoria.setStatus(CategoriaStatus);

		produtos.setCodigo_barra(request.getParameter("codigoBarras"));
		produtos.setDesc(request.getParameter("descricao"));
		produtos.setPro_preco_venda(Double.parseDouble(request.getParameter("preco")));
		produtos.setVinicola(request.getParameter("vinicola"));
		produtos.setPais(request.getParameter("pais"));
		produtos.setRegiao(request.getParameter("regiao"));
		produtos.setSafra(request.getParameter("safra"));
		produtos.setTipo(request.getParameter("tipo"));
		produtos.setUva(request.getParameter("uva"));
		produtos.setAltura(request.getParameter("Altura"));
		produtos.setLargura(request.getParameter("Largura"));
		produtos.setPeso(request.getParameter("Peso"));
		produtos.setProfundidade(request.getParameter("Profundidade"));
		produtos.setAlcool(request.getParameter("alcool"));

		produtoservice.EditarProduto(produtos, precificacao, categoria);

		response.sendRedirect(request.getContextPath() + "/areaAdministrador/Produtos.html");

	}

}
