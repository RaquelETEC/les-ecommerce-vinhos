package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dao.DaoEndereco;
import model.entity.Cliente;
import model.entity.Endereco;

// TODO: Auto-generated Javadoc

@WebServlet(urlPatterns = { "/inserirEndereco", "/exibirEndereco" })
public class ControllerEndereco extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	DaoEndereco daoEndereco = new DaoEndereco();
	
    Endereco endereco = new Endereco();

	public ControllerEndereco() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getServletPath();
		
		System.out.println("chegou aqui: "+action);
		if (action.equals("/inserirEndereco")) {
			AdicionarEndereco(request, response);
		}
		else if(action.equals("/exibirEndereco")){
			ExibirEndereco(request, response);
		}
		else {
			response.sendRedirect("index.html");
		}
	}

	protected void AdicionarEndereco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
                
			System.out.println("voce conseguiu chegar aqui no AdicionarEndereco:)");
			
			Cliente cliente = (Cliente) request.getAttribute("cliente");
            System.out.println("o id do cliente no adicionar endereço servelet: "+cliente.getId());

			String tipoResidencia = request.getParameter("typeTipoResidencia");
			String tipoLogradouro = request.getParameter("typeTipoLogradouro");
			String logradouro = request.getParameter("typeLogradouro");
			String numero = request.getParameter("typeNumero");
			String bairro = request.getParameter("typeBairro");
			String cidade = request.getParameter("typeCidade");
			String cep = request.getParameter("typeCep");
            String estado = request.getParameter("typeEstado");
			String pais = request.getParameter("typePais");
			String observacoes = request.getParameter("observacoes");
			String tipoEndereco = "R";
            String enderecoPadrao = "N";

            System.out.println("tipoResidencia "+tipoResidencia);
            System.out.println("tipoLogradouro "+tipoLogradouro);
            System.out.println("numero: "+numero);
        
            endereco.setCliente(cliente);
            endereco.setTipoResidencia(tipoResidencia);
            endereco.setTipoLogradouro(tipoLogradouro);
            endereco.setLogradouro(logradouro);
            endereco.setNumero(numero);
            endereco.setBairro(bairro);
            endereco.setCep(cep);
            endereco.setCidade(cidade);
            endereco.setEstado(tipoEndereco);
            endereco.setEstado(estado);
            endereco.setPais(pais);
            endereco.setPadrao(enderecoPadrao);
            endereco.setObservacao(observacoes);		

			daoEndereco.inserirEndereco(cliente, endereco);
			
			System.out.println("Passou pelo Dao endereço na servlet: "+ endereco);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/areaCliente");
			dispatcher.forward(request, response);
		
	}
	protected void ExibirEndereco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			System.out.println("voce conseguiu chegar aqui no ExibirEndereco :)");
			RequestDispatcher rd = request.getRequestDispatcher("/areaCliente/PerfilEndereco.jsp");
			rd.forward(request, response);	

		}
	
}