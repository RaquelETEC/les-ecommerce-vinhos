package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Date;

import model.entity.BandeiraCartao;
import model.entity.CartaoDeCredito;
import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.TiposEndereco;
import Service.ClienteService;

// TODO: Auto-generated Javadoc

@WebServlet(urlPatterns = { "/insertCliente", "/editarSenha", "/areaCliente", "/areaAdministrador/Clientes.html",
		"/areaCliente/Perfil.html", "/updateCliente", "/areaAdministrador/deleteClient",
		"/areaCliente/TrocarSenha.html" })
public class ControllerClient extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The dao. */

	/** The cliente. */
	Cliente cliente = new Cliente();

	/* The endereco */
	Endereco endereco = new Endereco();

	/* The cartao */
	CartaoDeCredito cartao = new CartaoDeCredito();

	/* The cartao */
	BandeiraCartao bandeira = new BandeiraCartao();

	/* The ServiceCliente */
	ClienteService clienteService = new ClienteService();
	
	/* */ 

	public ControllerClient() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		System.out.println("ESSA DESGRAÇAAAAAAAAAAA:" + action);

		if (action.equals("/insertCliente")) {
			AdicionarCliente(request, response);

		} else if (action.equals("/areaCliente")) {
			AreaCliente(request, response);

		} else if (action.equals("/areaAdministrador/Clientes.html")) {
			Clientes(request, response);

		} else if (action.equals("/areaCliente/Perfil.html")) {
			ListarCliente(request, response);

		} else if (action.equals("/updateCliente")) {
			EditarCliente(request, response);

		} else if (action.equals("/areaAdministrador/deleteClient")) {
			ExcluirCliente(request, response);
			
		} else if (action.equals("/areaCliente/TrocarSenha.html")) {
			TelaEditarSenha(request, response);
			
		} else if (action.equals("/editarSenha")) {
			EditarSenha(request, response);
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
		String cpf = request.getParameter("typeCPF");
		String tipoTelefone = request.getParameter("TypeTipoTelefone");
		String telefone = request.getParameter("typeNumeroTelefone");
		String nascimento = request.getParameter("typeNascimento");
		String genero = request.getParameter("typeGenero");

		
				
		cliente.setNome(nome);
		cliente.setEmail(email);
		cliente.setSenha(senha);
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

		// Endere�o Residencial
		Endereco enderecoR = new Endereco();

		String tipoEnderecoParam = request.getParameter("RESIDENCIAL");
		TiposEndereco tiposEndereco = TiposEndereco.valueOf(tipoEnderecoParam);

		enderecoR.setTipoResidencia(request.getParameter("typeTipoResidencia"));
		enderecoR.setTipoLogradouro(request.getParameter("typeTipoLogradouro"));
		enderecoR.setLogradouro(request.getParameter("typeLogradouro"));
		enderecoR.setNumero(request.getParameter("typeNumero"));
		enderecoR.setBairro(request.getParameter("typeBairro"));
		enderecoR.setCep(request.getParameter("typeCep"));
		enderecoR.setCidade(request.getParameter("typeCidade"));
		enderecoR.setEstado(request.getParameter("typeEstado"));
		enderecoR.setPais(request.getParameter("typePais"));
		enderecoR.setPadrao("SIM");
		enderecoR.setNome(request.getParameter("TypeNomeEnd"));
		enderecoR.setObservacao(request.getParameter("observacoes"));
		enderecoR.setTipos(tiposEndereco);

		// Endere�o Entrega
		Endereco enderecoE = new Endereco();

		System.out.println("chegamos na entrega");

		String tipoEnderecoParamE = request.getParameter("ENTREGA");
		TiposEndereco tiposEnderecoE = TiposEndereco.valueOf(tipoEnderecoParamE);

		enderecoE.setTipoResidencia(request.getParameter("typeTipoResidenciaE"));
		enderecoE.setTipoLogradouro(request.getParameter("typeTipoLogradouroE"));
		enderecoE.setLogradouro(request.getParameter("typeLogradouroE"));
		enderecoE.setNumero(request.getParameter("typeNumeroE"));
		enderecoE.setBairro(request.getParameter("typeBairroE"));
		enderecoE.setCep(request.getParameter("typeCepE"));
		enderecoE.setCidade(request.getParameter("typeCidadeE"));
		enderecoE.setEstado(request.getParameter("typeEstadoE"));
		enderecoE.setPais(request.getParameter("typePaisE"));
		enderecoE.setPadrao("SIM");
		enderecoE.setNome(request.getParameter("TypeNomeEndE"));
		enderecoE.setObservacao(request.getParameter("observacoesE"));
		enderecoE.setTipos(tiposEnderecoE);

		// Endere�o Cobran�a
		Endereco enderecoC = new Endereco();
		System.out.println("chegamos na COBRANCA");
		String tipoEnderecoParamC = request.getParameter("COBRANCA");
		TiposEndereco tiposEnderecoC = TiposEndereco.valueOf(tipoEnderecoParamC);

		enderecoC.setTipoResidencia(request.getParameter("typeTipoResidenciaC"));
		enderecoC.setTipoLogradouro(request.getParameter("typeTipoLogradouroC"));
		enderecoC.setLogradouro(request.getParameter("typeLogradouroC"));
		enderecoC.setNumero(request.getParameter("typeNumeroC"));
		enderecoC.setBairro(request.getParameter("typeBairroC"));
		enderecoC.setCep(request.getParameter("typeCepC"));
		enderecoC.setCidade(request.getParameter("typeCidadeC"));
		enderecoC.setEstado(request.getParameter("typeEstadoC"));
		enderecoC.setPais(request.getParameter("typePaisC"));
		enderecoC.setPadrao("SIM");
		enderecoC.setNome(request.getParameter("TypeNomeEndC"));
		enderecoC.setObservacao(request.getParameter("observacoesC"));
		enderecoC.setTipos(tiposEnderecoC);

		// Cart�o

		int Codigobandeira = Integer.parseInt(request.getParameter("tipoBandeira"));
		bandeira.setId(Codigobandeira);

		cartao.setNumero(request.getParameter("CartaoNumero"));
		cartao.setNome(request.getParameter("CartaoNome"));
		cartao.setPadrao("SIM");

		String codigoSegurancaStr = request.getParameter("CartaoCodigo");
		int codigoSeguranca = Integer.parseInt(codigoSegurancaStr);
		cartao.setCodigoSeguranca(codigoSeguranca);

		clienteService.adicionarCliente(cliente, enderecoR, enderecoE, enderecoC, cartao, bandeira);

		System.out.println("Passou por tudo ta joia: ");

		response.sendRedirect(request.getContextPath() + "/areaAdministrador/Clientes.html");

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

		request.setAttribute("cliente", cliente);
		request.setAttribute("id", cliente.getId());

		System.out.println("o nascimento que chegou na area do cliente: " + nascimento);

		RequestDispatcher rd = request.getRequestDispatcher("/areaCliente/Perfil.jsp");
		rd.forward(request, response);

	}

	protected void Clientes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ESTOU NO ARRAY DA LISTACLIENTE");

		ArrayList<Cliente> lista = clienteService.ListarCliente();

		request.setAttribute("Requeclientes", lista);
		RequestDispatcher rd = request.getRequestDispatcher("/areaAdministrador/Clientes.jsp");
		rd.forward(request, response);

	}

	private void ListarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ESTOU NA LISTAGEM DO CLIENTE");

		int id = Integer.parseInt(request.getParameter("id"));
		cliente.setId(id);

		cliente = clienteService.selecionarCliente(cliente);

		request.setAttribute("cliente", cliente);

		RequestDispatcher rd = request.getRequestDispatcher("/areaCliente/Perfil.jsp");
		rd.forward(request, response);
	}

	private void EditarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

		String retornoService = clienteService.alterarCliente(cliente);

		response.sendRedirect(request.getContextPath() + "/areaAdministrador/Clientes.html");

	}

	protected void ExcluirCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("EU CHEGUEI NO EXCLUIRS");

		cliente.setId(Integer.parseInt(request.getParameter("id")));

		String retornoService = clienteService.deletarCliente(cliente);
		response.sendRedirect(request.getContextPath() + "/areaAdministrador/Clientes.html");
	}

	protected void TelaEditarSenha(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("EU CHEGUEI NO TelaEditarSenha");

		int id = Integer.parseInt(request.getParameter("id"));
		
		cliente.setId(id);
		
		cliente = clienteService.selecionarCliente(cliente);
		
		
		request.setAttribute("id", id);
		request.setAttribute("cliente", cliente);
		System.out.println("o cliente:"+ cliente.getNome());

		RequestDispatcher rd = request.getRequestDispatcher("/areaCliente/PerfilTrocarSenha.jsp");
		rd.forward(request, response);

	}
	protected void EditarSenha(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("EU CHEGUEI NO EditarSenha SERVELET");

		int id = Integer.parseInt(request.getParameter("id"));
		String senhaAtual = request.getParameter("typeSenhaAtual");
		String novaSenha = request.getParameter("typeNovaSenha");
		String repitaSenha = request.getParameter("typeRepitaSenha");

		
		cliente.setId(id);
		cliente = clienteService.selecionarCliente(cliente);

		String respostaCli = clienteService.alterarSenha(cliente, senhaAtual, novaSenha, repitaSenha); 
		
		 // Escreve a resposta para o HttpServletResponse
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(respostaCli);

	    // Encerra a resposta
	    response.getWriter().flush();
		
	}

}