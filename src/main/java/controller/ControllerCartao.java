package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.BandeiraCartao;
import model.entity.CartaoDeCredito;
import model.entity.Cliente;
import service.CartoesService;

// TODO: Auto-generated Javadoc

@WebServlet(urlPatterns = { "/areaCliente/inserirCartao", "/inserirCartao", "/areaCliente/EditarCartao",
		"/areaCliente/MeusCartoes.html", "/areaCliente/LoginCartao.html", "/areaCliente/EditarCartao.html",
		"/areaCliente/deleteCartao" })
public class ControllerCartao extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	Cliente cliente = new Cliente();
	CartaoDeCredito cartao = new CartaoDeCredito();
	BandeiraCartao tipoBandeira = new BandeiraCartao();
	CartoesService cartaoService = new CartoesService();

	public ControllerCartao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		System.out.println("chegou aqui: " + action);

		if (action.equals("/areaCliente/inserirCartao") || action.equals("inserirCartao")) {
			AdicionarCartao(request, response);
		} else if (action.equals("/areaCliente/MeusCartoes.html")) {
			areaMeusCartoes(request, response);
		} else if (action.equals("/areaCliente/LoginCartao.html")) {
			TelaCartaoNovo(request, response);
		} else if (action.equals("/areaCliente/EditarCartao.html")) {
			TelaEditarCartao(request, response);
		} else if (action.equals("/areaCliente/EditarCartao")) {
			EditarCartao(request, response);
		}

		else if (action.equals("/areaCliente/deleteCartao")) {
			ExcluirCartao(request, response);
		}

	}

	// fun��o para cadastrar novo cartao, pegando todos os atributos do form
	protected void AdicionarCartao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("voce conseguiu chegar aqui no AdicionarCartao:)");

		int id = Integer.parseInt(request.getParameter("typeId"));
		cliente.setId(id);
		System.out.println("o id que chegou aqui nos AddCartao:" + cliente.getId());

		int Codigobandeira = Integer.parseInt(request.getParameter("tipoBandeira"));
		System.out.println("o id do bandeira cartao no adicionar cartao : " + Codigobandeira);

		String numero = request.getParameter("CartaoNumero");
		String nome = request.getParameter("CartaoNome");
		String padrao = request.getParameter("CartaoPadrao");
		String codigoSegurancaStr = request.getParameter("CartaoCodigo");
		int codigoSeguranca = Integer.parseInt(codigoSegurancaStr);
		String cadPerfil = request.getParameter("CadastrarNoPerfil");

		boolean cadastrarNoPerfil = cadPerfil.toUpperCase() == "SIM" ? true : false;

		tipoBandeira.setId(Codigobandeira);

		cartao.setCliente(cliente);
		cartao.setBandeira(tipoBandeira);
		cartao.setNumero(numero);
		cartao.setNome(nome);
		cartao.setPadrao(padrao);
		cartao.setCodigoSeguranca(codigoSeguranca);
		cartao.setCartaoNoPerfil(cadastrarNoPerfil);

		String resposta = cartaoService.adicionarCartao(cliente, cartao, tipoBandeira);

		String solicitacao = request.getParameter("tipoSolicitacao");

		if (solicitacao != null) {
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(resposta);
			response.getWriter().flush();
		} else {
			request.setAttribute("id", id);
			response.sendRedirect(request.getContextPath() + "/areaCliente/MeusCartoes.html?id=" + id);

		}

	}

	// fun��o para exibir a tela de adicionar cartao, passando o id do cliente e
	// exibindo na tela
	protected void TelaCartaoNovo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		cliente.setId(id);
		System.out.println("o id que chegou aqui nos AddCartao:" + cliente.getId());
		request.setAttribute("id", id);

		RequestDispatcher rd = request.getRequestDispatcher("/areaCliente/MeuCartoesADDNovo.jsp");
		rd.forward(request, response);

	}

	protected void areaMeusCartoes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		cliente.setId(id);

		System.out.println("o id que chegou aqui nos cartoes:" + cliente.getId());

		ArrayList<CartaoDeCredito> lista = cartaoService.listarCartoes(cliente);

		request.setAttribute("listaCartoes", lista);
		request.setAttribute("id", id);

		RequestDispatcher rd = request.getRequestDispatcher("/areaCliente/MeusCartoes.jsp");
		rd.forward(request, response);
	}

	protected void TelaEditarCartao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("voce conseguiu chegar aqui no EditarEndereco:)");

		int id = Integer.parseInt(request.getParameter("id"));
		int idCartao = Integer.parseInt(request.getParameter("idCartao"));
		int idBandeira = Integer.parseInt(request.getParameter("idBandeira"));
		cliente.setId(id);
		cartao.setId(idCartao);
		tipoBandeira.setId(idBandeira);

		System.out.println("o id do cliente no tela editar cartao servelet: " + cliente.getId() + "e" + cartao.getId()
				+ "e" + tipoBandeira.getId());
		request.setAttribute("id", id);
		request.setAttribute("idCartao", idCartao);
		request.setAttribute("idBandeira", idBandeira);

		cartao = cartaoService.selecionarCartao(cliente, cartao, tipoBandeira);

		request.setAttribute("cartao", cartao);

		RequestDispatcher rd = request.getRequestDispatcher("/areaCliente/EditarCartao.jsp");
		rd.forward(request, response);

	}

	protected void EditarCartao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("voce conseguiu chegar aqui no EditarCartao:)");

		int id = Integer.parseInt(request.getParameter("id"));
		int idCartao = Integer.parseInt(request.getParameter("idCartao"));
		cliente.setId(id);
		cartao.setId(idCartao);

		int Codigobandeira = Integer.parseInt(request.getParameter("tipoBandeira"));
		tipoBandeira.setId(Codigobandeira);

		cartao.setNumero(request.getParameter("CartaoNumero"));
		cartao.setNome(request.getParameter("CartaoNome"));
		cartao.setPadrao(request.getParameter("CartaoPadrao"));
		cartao.setCodigoSeguranca(Integer.parseInt(request.getParameter("CartaoCodigo")));
		cartao.setBandeira(tipoBandeira);
		cartaoService.editarCartao(cliente, cartao, tipoBandeira);

		response.sendRedirect(request.getContextPath() + "/areaCliente/MeusCartoes.html?id=" + id);

	}

	protected void ExcluirCartao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("EU CHEGUEI NO EXCLUIR CARTAO");

		int id = Integer.parseInt(request.getParameter("id"));
		cliente.setId(id);

		cartao.setId(Integer.parseInt(request.getParameter("idCartao")));

		cartaoService.deletarCartao(cartao);

		System.out.println("ID" + cartao.getId());
		System.out.println("ID do cliente no excluir cartao" + cliente.getId());

		response.sendRedirect(request.getContextPath() + "/areaCliente/MeusCartoes.html?id=" + id);

	}

}