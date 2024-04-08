package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.CarrinhoItem;

@WebServlet(urlPatterns = { "/AdicionarAoCarrinho", "/ExibirCarrinho" })
public class ControllerVenda extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CarrinhoItem carrinho = new CarrinhoItem();

	public ControllerVenda() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		System.out.println("chegou aqui: " + action);

		if (action.equals("/AdicionarAoCarrinho")) {
			AdicionarAoCarriho(request, response);
		} else if (action.equals("/ExibirCarrinho")) {
			ExibirCarrinho(request, response);
		} else {
			System.out.println("erro ao redirecionar " + action);
			response.sendRedirect("index.html");
		}
	}

	protected void AdicionarAoCarriho(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/ExibirCarrinho");
		rd.forward(request, response);

	}

	protected void ExibirCarrinho(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<CarrinhoItem> lista = new ArrayList<>();

		lista.add(new CarrinhoItem("vinho", 99.30, 2));

		request.setAttribute("itemsCarrinho", lista);
		RequestDispatcher rd = request.getRequestDispatcher("meuCarrinho.jsp");
		rd.forward(request, response);

	}
}
