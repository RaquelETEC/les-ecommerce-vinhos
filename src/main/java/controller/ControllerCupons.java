package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.CupomService;
import model.entity.Cliente;
import model.entity.Cupons;
import model.entity.PedidoVenda;
import model.entity.TiposEndereco;

@WebServlet(urlPatterns = { "/areaCliente/MeusCupons.html", "/areaCliente/InserirCupomTroca"})
public class ControllerCupons extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Cupons cupom = new Cupons();
	Cliente cliente = new Cliente();
	CupomService cupomService = new CupomService();
	PedidoVenda pedido = new PedidoVenda();
	public ControllerCupons() {
		super();	
		}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		System.out.println("chegou aqui: " + action);
		if (action.equals("/areaCliente/MeusCupons.html")) {
			areaMeusCupons(request, response);
		}else if (action.equals("/areaCliente/InserirCupomTroca")) {
			InserirCupomTroca(request, response);
		}	
	}
	
	
	protected void InserirCupomTroca(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    System.out.println("voce conseguiu chegar aqui no InserirCupomTroca:)");

	    try {

	        int idPedido = Integer.parseInt(request.getParameter("idPedido"));
	        pedido.setId(idPedido);

	        Double ValorCupom = Double.parseDouble(request.getParameter("ValorCupom"));
	        cupom.setValor(ValorCupom);
	        
	        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
	        cliente.setId(idCliente);

	        // Define os valores do cupom manualmente
	        
	        cupom.setCodigo("100OFF");
	        cupom.setValidade(new SimpleDateFormat("yyyy-MM-dd").parse("2025-04-30"));
	        cupom.setDesc("Desconto da venda do pedido%");
	        cupom.setImg("../imagens/assets/descontoCupom.png");
	        cupom.setTipo("T");

	        String resposta = String.valueOf(cupomService.adicionarCupomTroca(cliente, cupom));

	        response.setContentType("text/plain");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(resposta);
	        response.getWriter().flush();

	    } catch (Exception e) {
	        System.out.println("erro: " + e);
	    }
	}

	private void areaMeusCupons(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		cliente.setId(id);

		System.out.println("o id que chegou aqui nos cartoes:" + cliente.getId());

		ArrayList<Cupons> lista = cupomService.listarCupom(cliente);
		
		request.setAttribute("listaCupons", lista);
		request.setAttribute("id", id);

		RequestDispatcher rd = request.getRequestDispatcher("/areaCliente/PerfilMeusCupons.jsp");
		rd.forward(request, response);
	}
}
