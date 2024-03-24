package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.DaoCliente;

import java.util.ArrayList;
import java.util.Date;

import model.entity.Cliente;

// TODO: Auto-generated Javadoc

@WebServlet(urlPatterns = { "/insertCliente", "/areaCliente", "/telaCliente", "/selectCliente", "/updateCliente" , "/deleteClient"})
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

		System.out.println("chegou aqui: " + action);
		if (action.equals("/insertCliente")) {
			AdicionarCliente(request, response);
			
		} else if (action.equals("/areaCliente")) {
			AreaCliente(request, response);
			
		} else if (action.equals("/telaCliente")) {
			Clientes(request, response);
			
		} else if (action.equals("/selectCliente")) {
			ListarCliente(request, response);
		}
		else if (action.equals("/updateCliente")) {
			EditarCliente(request, response);
		}	
		else if (action.equals("/deleteClient")) {
			System.out.println("if eu cheguei");
			ExcluirCliente(request, response);
		}
		// } else if (action.equals("/report")) {
		// gerarRelatorio(request, response);
		// }
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
		cliente.setStatus("Ativo");
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Adapte o formato conforme necessário
			Date nascimentoDate = dateFormat.parse(nascimento);
			cliente.setDataNasc(nascimentoDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		daoCliente.inserirCliente(cliente);

		System.out.println("cliente cadastrado com sucesso: " + nome);

		int id_cliente = Integer.parseInt(daoCliente.selectIdCliente(cliente));

		cliente.setId(id_cliente);

		System.out.println("o id do CLIENTE Objeto:" + cliente.getId());

		request.setAttribute("cliente", cliente);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/inserirEndereco");

		dispatcher.forward(request, response);

	}

	protected void AreaCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cliente cliente = (Cliente) request.getAttribute("cliente");
		String nome = request.getParameter("typeNome");
		String email = request.getParameter("typeEmail");
		String senha = request.getParameter("typeSenha");
		String senhaRepetida = request.getParameter("typeRepitaSenha");
		String cpf = request.getParameter("typeCPF");
		String tipoTelefone = request.getParameter("TypeTipoTelefone");
		String telefone = request.getParameter("typeNumeroTelefone");
		String nascimento = request.getParameter("typeNascimento");
		String genero = request.getParameter("typeGenero");

		System.out.println("o nome que chegou na area do cliente: " + nome);
		System.out.println("o genero que chegou na area do cliente: " + genero);

		request.setAttribute("id", cliente.getId());
		request.setAttribute("nome", nome);
		request.setAttribute("email", email);
		request.setAttribute("senha", senha);
		request.setAttribute("senhaRepetida", senhaRepetida);
		request.setAttribute("cpf", cpf);
		request.setAttribute("tipoTelefone", tipoTelefone);
		request.setAttribute("telefone", telefone);
		request.setAttribute("nascimento", nascimento);
		request.setAttribute("genero", genero);

		System.out.println("o nascimento que chegou na area do cliente: " + nascimento);

		RequestDispatcher rd = request.getRequestDispatcher("/areaCliente/Perfil.jsp");
		rd.forward(request, response);

	}

	protected void Clientes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ESTOU NO ARRAY DA LISTACLIENTE");

		ArrayList<Cliente> lista = daoCliente.ListarCliente();
		request.setAttribute("Requeclientes", lista);
		RequestDispatcher rd = request.getRequestDispatcher("/areaAdministrador/Clientes.jsp");
		rd.forward(request, response);

	}
	
	private void ListarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ESTOU NA LISTAGEM DO CLIENTE");

		int id = Integer.parseInt(request.getParameter("id"));
		cliente.setId(id);
		daoCliente.selecionarCliente(cliente);
		
		request.setAttribute("id", cliente.getId());
		request.setAttribute("nome", cliente.getNome());
		request.setAttribute("email", cliente.getEmail());
		request.setAttribute("cpf", cliente.getCpf());
		request.setAttribute("nascimento", cliente.getDataNasc());
		request.setAttribute("telefoneTipo", cliente.getTipoTelefone());
		request.setAttribute("telefone", cliente.getTelefone());
		request.setAttribute("genero", cliente.getGenero());
		request.setAttribute("status", cliente.getStatus());
		
		RequestDispatcher rd = request.getRequestDispatcher("/areaCliente/Perfil.jsp");
		rd.forward(request, response);	
	}
	
	private void EditarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		System.out.println("cheguei no editar cliente em");
		
		
		
		int id = Integer.parseInt(request.getParameter("typeId"));
		cliente.setId(id);
		cliente.setNome(request.getParameter("typeNome"));
		cliente.setEmail(request.getParameter("typeEmail"));
		cliente.setCpf(request.getParameter("typeCPF"));
		String nascimento = request.getParameter("typeNascimento");
	
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Adapte o formato conforme necessário
			Date nascimentoDate = dateFormat.parse(nascimento);
			cliente.setDataNasc(nascimentoDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
				
		cliente.setTelefone(request.getParameter("typeNumeroTelefone"));
		cliente.setTipoTelefone(request.getParameter("tipoTelefone"));
		cliente.setGenero(request.getParameter("genero"));
		cliente.setStatus(request.getParameter("statusCliente"));
		
		
		
		daoCliente.alterarCliente(cliente);
		
		response.sendRedirect(request.getContextPath() + "/telaCliente");

	}
	
	protected void ExcluirCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("EU CHEGUEI NO EXCLUIRS");
		
		cliente.setId(Integer.parseInt(request.getParameter("id")));
		daoCliente.deletarCliente(cliente);
		response.sendRedirect(request.getContextPath() + "/telaCliente");
	}

}