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

@WebServlet(urlPatterns = {  "/inserirCartao","/areaCliente/MeusCartoes.html"})
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
		
		if (action.equals("/inserirEndereco")) {
			AdicionarCartao(request, response);
		}
		if (action.equals("/areaCliente/MeusCartoes.html")) {
			areaMeusCartoes(request, response);
		}
	
		else {
			response.sendRedirect("index.html");
		}
	}

	protected void AdicionarCartao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("voce conseguiu chegar aqui no AdicionarCartao:)");

		Cliente cliente = (Cliente) request.getAttribute("cliente");
        System.out.println("o id do cliente no adicionar cartao : "+cliente.getId());
        

		BandeiraCartao tipoBandeira = (BandeiraCartao) request.getAttribute("tipoBandeira");
        System.out.println("o id do bandeira cartao no adicionar cartao : "+ tipoBandeira.getId());

		String numero = request.getParameter("CartaoNumero");
		String nome  = request.getParameter("CartaoNome");
		String padrao = "SIM";
		String codigoSegurancaStr = request.getParameter("CartaoCodigo");
		int codigoSeguranca = Integer.parseInt(codigoSegurancaStr);
		
        cartao.setCliente(cliente);
        cartao.setBandeira(tipoBandeira);
        cartao.setNumero(numero);
        cartao.setNome(nome);
        cartao.setPadrao(padrao);
		cartao.setCodigoSeguranca(codigoSeguranca);		


	}
	
	
	protected void areaMeusCartoes(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		cliente.setId(id);
			    
	    System.out.println("o id que chegou aqui nos cartoes:"+cliente.getId());
	    
	    ArrayList<CartaoDeCredito> lista = cartaoService.listarCartoes(cliente);

	    request.setAttribute("listaCartoes", lista);
	    request.setAttribute("id", id);
	    
	    RequestDispatcher rd = request.getRequestDispatcher("/areaCliente/MeusCartoes.jsp");
	    rd.forward(request, response);
	}

}