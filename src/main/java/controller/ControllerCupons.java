package controller;

import java.io.IOException;
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

@WebServlet(urlPatterns = { "/areaCliente/MeusCupons.html"})
public class ControllerCupons extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Cupons cupom = new Cupons();
	Cliente cliente = new Cliente();
	CupomService cupomService = new CupomService();
	
	public ControllerCupons() {
		super();	
		}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		System.out.println("chegou aqui: " + action);
		if (action.equals("/areaCliente/MeusCupons.html")) {
			areaMeusCupons(request, response);
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
