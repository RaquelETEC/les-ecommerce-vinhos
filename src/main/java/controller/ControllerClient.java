package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc

@WebServlet(urlPatterns = { "/insertCliente"  })
public class ControllerClient extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The contato. */
	JavaBeans contato = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public ControllerClient() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		
		System.out.println("chegou aqui: "+action);
		if (action.equals("/insertCliente")) {
			AdicionarCliente(request, response);
		}
		//} else if (action.equals("/report")) {
		//	gerarRelatorio(request, response);
		//} 
		else {
			response.sendRedirect("index.html");
		}
	}

	protected void AdicionarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			System.out.println("voce conseguiu chegar aqui no AdicionarCliente:)");
			String nome = request.getParameter("typeNome");
			String email = request.getParameter("typeEmail");
			String senha = request.getParameter("typeSenha");
			String senhaRepetida = request.getParameter("typeRepitaSenha");
			String cpf = request.getParameter("typeCPF");
			String tipoTelefone = request.getParameter("TypeTipoTelefone");
			String telefone = request.getParameter("typeNumeroTelefone");
			String nascimento = request.getParameter("typeNascimento");
			String genero = request.getParameter("typeGenero");
			String tipoResidencia = request.getParameter("typeTipoResidencia");
			String tipoLogradouro = request.getParameter("typeTipoLogradouro");
			String logradouro = request.getParameter("typeLogradouro");
			String numero = request.getParameter("typeNumero");
			String bairro = request.getParameter("typeBairro");
			String cidade = request.getParameter("typeCidade");
			String cep = request.getParameter("typeCep");
			String pais = request.getParameter("typePais");
			String observacoes = request.getParameter("observacoes");
			String tipoEndereco = "R";

		    // Outras operações, se necessário

		 
			// Envia as informações para a página HTML
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("<p>Nome: " + nome + "</p>");
			response.getWriter().write("<p>Email: " + email + "</p>");
			response.getWriter().write("<p>Senha: " + senha + "</p>");
			response.getWriter().write("<p>Senha Repetida: " + senhaRepetida + "</p>");
			response.getWriter().write("<p>CPF: " + cpf + "</p>");
			response.getWriter().write("<p>Tipo de Telefone: " + tipoTelefone + "</p>");
			response.getWriter().write("<p>Telefone: " + telefone + "</p>");
			response.getWriter().write("<p>Data de Nascimento: " + nascimento + "</p>");
			response.getWriter().write("<p>Gênero: " + genero + "</p>");
			response.getWriter().write("<p>Tipo de Residência: " + tipoResidencia + "</p>");
			response.getWriter().write("<p>Tipo de Logradouro: " + tipoLogradouro + "</p>");
			response.getWriter().write("<p>Logradouro: " + logradouro + "</p>");
			response.getWriter().write("<p>Número: " + numero + "</p>");
			response.getWriter().write("<p>Bairro: " + bairro + "</p>");
			response.getWriter().write("<p>Cidade: " + cidade + "</p>");
			response.getWriter().write("<p>CEP: " + cep + "</p>");
			response.getWriter().write("<p>País: " + pais + "</p>");
			response.getWriter().write("<p>Observações: " + observacoes + "</p>");
		
	}

}