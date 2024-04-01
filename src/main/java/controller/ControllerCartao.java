package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.CartoesService;
import model.entity.BandeiraCartao;
import model.entity.CartaoDeCredito;
import model.entity.Cliente;

// TODO: Auto-generated Javadoc

@WebServlet(urlPatterns = { "/areaCliente/inserirCartao", "/areaCliente/MeusCartoes.html", "/areaCliente/LoginCartao.html" , "/listarCartao" })
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

		if (action.equals("/areaCliente/inserirCartao")) {
			AdicionarCartao(request, response);
		}
		if (action.equals("/areaCliente/MeusCartoes.html")) {
			areaMeusCartoes(request, response);
		}
		if (action.equals("/areaCliente/LoginCartao.html")) {
			TelaCartaoNovo(request, response);
		}
		if (action.equals("/listarCartao")) {
			ListarCartao(request, response);
		}

	}

	//função para cadastrar novo cartao, pegando todos os atributos do form 
	protected void AdicionarCartao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("voce conseguiu chegar aqui no AdicionarCartao:)");

		int id = Integer.parseInt(request.getParameter("typeId"));
		cliente.setId(id);
		System.out.println("o id que chegou aqui nos AddCartao:" + cliente.getId());
		request.setAttribute("id", id);

		int Codigobandeira = Integer.parseInt(request.getParameter("tipoBandeira"));
		System.out.println("o id do bandeira cartao no adicionar cartao : " + Codigobandeira);

		String numero = request.getParameter("CartaoNumero");
		String nome = request.getParameter("CartaoNome");
		String padrao = "SIM";
		String codigoSegurancaStr = request.getParameter("CartaoCodigo");
		int codigoSeguranca = Integer.parseInt(codigoSegurancaStr);
		
		
		tipoBandeira.setId(Codigobandeira);
		
		cartao.setCliente(cliente);
		cartao.setBandeira(tipoBandeira);
		cartao.setNumero(numero);
		cartao.setNome(nome);
		cartao.setPadrao(padrao);
		cartao.setCodigoSeguranca(codigoSeguranca);
		
		
		cartaoService.adicionarCartao(cliente, cartao, tipoBandeira);
		

		response.sendRedirect(request.getContextPath() + "/areaCliente/MeusCartoes.html?id="+id);
	}

	//função para exibir a tela de adicionar cartao, passando o id do cliente e exibindo na tela 
	protected void TelaCartaoNovo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		int id = Integer.parseInt(request.getParameter("id"));
		cliente.setId(id);
		System.out.println("o id que chegou aqui nos AddCartao:" + cliente.getId());
		request.setAttribute("id", id);
		
		RequestDispatcher rd = request.getRequestDispatcher("/areaCliente/MeuCartoesADDNovo.jsp");
		rd.forward(request, response);


	
	}
	
	protected void ListarCartao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("ESTOU NA LISTAGEM DO cartao");
		
		int id = Integer.parseInt(request.getParameter("id"));
		cartao.setId(id);
		
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

}