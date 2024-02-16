package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import model.Cliente;
import model.DaoCliente;

// TODO: Auto-generated Javadoc

@WebServlet(urlPatterns = { "/insertCliente", "/areaCliente" })
public class ControllerClient extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DaoCliente daoCliente = new DaoCliente();
	
	/** The cliente. */
	Cliente cliente = new Cliente();

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
		else if(action.equals("/areaCliente")) {
			AreaCliente(request, response);
		}
		
		//} else if (action.equals("/report")) {
		//	gerarRelatorio(request, response);
		//} 
		else {
			response.sendRedirect("index.html");
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
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
		
			// response.sendRedirect("areaCliente");

			cliente.setNome(nome);
			cliente.setEmail(email);
			cliente.setSenha(senhaRepetida);
			cliente.setCpf(cpf);
			cliente.setTipoTelefone(tipoTelefone);
			cliente.setTelefone(telefone);
			cliente.setGenero(genero);
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Adapte o formato conforme necessário
				Date nascimentoDate = dateFormat.parse(nascimento);
				cliente.setDataNasc(nascimentoDate);
			} catch (ParseException e) {
				e.printStackTrace(); 
			}

			daoCliente.inserirCliente(cliente);
			
			System.out.println("cliente cadastrado com sucesso: "+ nome);
			
			//response.sendRedirect("main");
			int id_cliente = Integer.parseInt(daoCliente.selectIdCliente(cliente));

			cliente.setId(id_cliente);
			
			System.out.println("o id do CLIENTE Objeto:"+ cliente.getId());
			
			// Adicione o cliente ao request
			request.setAttribute("cliente", cliente);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/inserirEndereco");
			
			dispatcher.forward(request, response);
			
			
	}
	protected void AreaCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ArrayList<JavaBeans> lista = dao.listarclientes();
		//request.setAttribute("clientes", lista);
		String nome = request.getParameter("typeNome");
		String email = request.getParameter("typeEmail");
		String senha = request.getParameter("typeSenha");
		String senhaRepetida = request.getParameter("typeRepitaSenha");
		String cpf = request.getParameter("typeCPF");
		String tipoTelefone = request.getParameter("TypeTipoTelefone");
		String telefone = request.getParameter("typeNumeroTelefone");
		String nascimento = request.getParameter("typeNascimento");
		String genero = request.getParameter("typeGenero");
		
		System.out.println("o nome que chegou na area do cliente: "+nome);
		System.out.println("o genero que chegou na area do cliente: "+genero);

		request.setAttribute("nome", nome);
		request.setAttribute("email", email);
		request.setAttribute("senha", senha);
		request.setAttribute("senhaRepetida", senhaRepetida);
		request.setAttribute("cpf", cpf);
		request.setAttribute("tipoTelefone", tipoTelefone);
		request.setAttribute("telefone", telefone);
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Adapte o formato conforme necessário
			Date nascimentoDate = dateFormat.parse(nascimento);
			request.setAttribute("nascimento", nascimentoDate);
		} catch (ParseException e) {
			e.printStackTrace(); 
		}
		request.setAttribute("genero", genero);
		
		System.out.println("o nascimento que chegou na area do cliente: "+ nascimento);
		
		RequestDispatcher rd = request.getRequestDispatcher("areaCliente.jsp");
		rd.forward(request, response);	
	
	}
}