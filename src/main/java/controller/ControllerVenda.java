package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.CarrinhoService;
import model.entity.CarrinhoDeCompras;
import model.entity.CarrinhoItens;
import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.TiposEndereco;

//import model.entity.CarrinhoItem;

@WebServlet(urlPatterns = { "/AdicionarAoCarrinho", "/ExibirCarrinho" })
public class ControllerVenda extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//CarrinhoItem carrinho = new CarrinhoItem();
	CarrinhoService carrinhoService = new CarrinhoService();
	CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
	Cliente cliente = new Cliente(); 
	CarrinhoItens itens = new CarrinhoItens(); 
	
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
        
        cliente.setId(Integer.parseInt(request.getParameter("id")));

        carrinho = carrinhoService.SelecionarCarrinho(cliente);
    
        ArrayList<CarrinhoItens> listaItens = carrinhoService.listarItems(carrinho);
        
        //itens não removidos do carrinho 
        ArrayList<CarrinhoItens> lista = new ArrayList<>();
        //itens removidos do carrinho
        ArrayList<CarrinhoItens> listaRemovidos = new ArrayList<>();
        
        for (CarrinhoItens item : listaItens) {
            if (item.isRemovido()) {
                listaRemovidos.add(item);
            } else {
            	lista.add(item);
            }
        }
        
		request.setAttribute("itemsCarrinho", lista);
		request.setAttribute("itemsRemovidosCarrinho", listaRemovidos);
		RequestDispatcher rd = request.getRequestDispatcher("/areaVenda/meuCarrinho.jsp");
		rd.forward(request, response);

	}
}
