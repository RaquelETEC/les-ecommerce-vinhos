package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.PedidoVendaService;
import model.entity.PedidoVenda;
import model.entity.Cliente;

@WebServlet(urlPatterns = { "/areaAdministrador/PedidoVenda.html", "/areaAdministrador/EditarPedido" })

public class ControllerAdministracao extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	PedidoVenda pedidoVenda = new PedidoVenda();
	Cliente cliente = new Cliente();
	PedidoVendaService pedidoVendaservice = new PedidoVendaService();

	public ControllerAdministracao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		System.out.println("chegou aqui area adm: " + action);

		if (action.equals("/areaAdministrador/PedidoVenda.html")) {
			AreaPedidos(request, response);
		} else if (action.equals("/areaAdministrador/EditarPedido")) {
			EditarPedido(request, response);
		}

	}

	protected void AreaPedidos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Cheguei no area pedidos");

		ArrayList<PedidoVenda> lista = pedidoVendaservice.listarPedidoVenda();

		request.setAttribute("listaPedidos", lista);

		RequestDispatcher rd = request.getRequestDispatcher("/areaAdministrador/AprovacaoPedidos.jsp");
		rd.forward(request, response);

	}

	protected void TelaEditarPedido(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idPedido = Integer.parseInt(request.getParameter("idPedido"));
		pedidoVenda.setId(idPedido);

		pedidoVenda = pedidoVendaservice.selecionarPedido(pedidoVenda);

		request.setAttribute("pedidoVenda", pedidoVenda);

		RequestDispatcher rd = request.getRequestDispatcher("/areaAdministrador/AprovacaoPedidosEditar.jsp");
		rd.forward(request, response);
	}

	protected void EditarPedido(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idPedido = Integer.parseInt(request.getParameter("id"));
		pedidoVenda.setId(idPedido);

		pedidoVenda.setStatus(request.getParameter("PedidoStatus"));

		pedidoVendaservice.editarPedido(pedidoVenda);

		String resposta = pedidoVendaservice.editarPedido(pedidoVenda);

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(resposta);
		response.getWriter().flush();
		
		System.out.println("Estou no editar pedido");
	}

}
