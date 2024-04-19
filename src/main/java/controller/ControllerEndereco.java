package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Service.EnderecoService;
import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.TiposEndereco;

// TODO: Auto-generated Javadoc

@WebServlet(urlPatterns = { "/areaCliente/inserirEndereco", "/areaCliente/deleteEndereco",
		"/areaCliente/EditarEndereco", "/areaCliente/MeusEnderecos.html", "/areaCliente/MeusEnderecosEditar.html" })
public class ControllerEndereco extends HttpServlet {

	private static final long serialVersionUID = 1L;

	Cliente cliente = new Cliente();

	EnderecoService enderecoService = new EnderecoService();

	Endereco endereco = new Endereco();

	public ControllerEndereco() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		System.out.println("chegou aqui: " + action);

		if (action.equals("/areaCliente/MeusEnderecos.html")) {
			ExibirEndereco(request, response);
		} else if (action.equals("/areaCliente/inserirEndereco")) {
			AdicionarEndereco(request, response);
		} else if (action.equals("/areaCliente/MeusEnderecosEditar.html")) {
			TelaEditarEndereco(request, response);
		} else if (action.equals("/areaCliente/EditarEndereco")) {
			EditarEndereco(request, response);
		} else if (action.equals("/areaCliente/deleteEndereco")) {
			ExcluirEndereco(request, response);
		}
	}

	protected void AdicionarEndereco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("voce conseguiu chegar aqui no AdicionarEndereco:)");

		int id = Integer.parseInt(request.getParameter("id"));
		cliente.setId(id);

		System.out.println("o id do cliente no adicionar endere�o servelet: " + cliente.getId());

		String nome = request.getParameter("nome");
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

		String tipoEnderecoParam = request.getParameter("tipoEndereco");
		TiposEndereco tiposEndereco = TiposEndereco.valueOf(tipoEnderecoParam);

		endereco.setCliente(cliente);
		endereco.setNome(nome);
		endereco.setTipoResidencia(tipoResidencia);
		endereco.setTipoLogradouro(tipoLogradouro);
		endereco.setLogradouro(logradouro);
		endereco.setNumero(numero);
		endereco.setBairro(bairro);
		endereco.setCep(cep);
		endereco.setCidade(cidade);
		endereco.setEstado(estado);
		endereco.setPais(pais);
		endereco.setPadrao("N");
		endereco.setObservacao(observacoes);
		endereco.setTipos(tiposEndereco);

		enderecoService.adicionarEndereco(cliente, endereco);

		response.sendRedirect(request.getContextPath() + "/areaCliente/MeusEnderecos.html?id=" + id);

	}

	protected void ExibirEndereco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("voce conseguiu chegar aqui na tela ExibirEndereco :)");

		int id = Integer.parseInt(request.getParameter("id"));
		cliente.setId(id);

		// Cliente cliente = (Cliente) request.getAttribute("cliente");

		System.out.println("o id que chegou aqui no listar endereço:" + cliente.getId());

		ArrayList<Endereco> lista = enderecoService.listarEnderecos(cliente);

		// Listas para cada tipo de endereço
		ArrayList<Endereco> listaEntrega = new ArrayList<>();
		ArrayList<Endereco> listaCobranca = new ArrayList<>();
		ArrayList<Endereco> listaResidencial = new ArrayList<>();

		// Separar endereços por tipo
		for (Endereco endereco : lista) {
			if (endereco.getTipos().equals(TiposEndereco.ENTREGA)) {
				listaEntrega.add(endereco);
			} else if (endereco.getTipos().equals(TiposEndereco.COBRANCA)) {
				listaCobranca.add(endereco);
			} else if (endereco.getTipos().equals(TiposEndereco.RESIDENCIAL)) {
				listaResidencial.add(endereco);
			}
		}

		// Enviar as listas separadas para a página .jsp
		request.setAttribute("listaEnderecosEntrega", listaEntrega);
		request.setAttribute("listaEnderecosCobranca", listaCobranca);
		request.setAttribute("listaEnderecosResidencial", listaResidencial);
		request.setAttribute("id", id);

		RequestDispatcher rd = request.getRequestDispatcher("/areaCliente/MeusEnderecos.jsp");
		rd.forward(request, response);

	}

	protected void TelaEditarEndereco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("voce conseguiu chegar aqui no EditarEndereco:)");

		int id = Integer.parseInt(request.getParameter("id"));
		int idEndereco = Integer.parseInt(request.getParameter("idEnd"));
		cliente.setId(id);
		endereco.setId(idEndereco);

		System.out.println(
				"o id do cliente no tela editar endere�o servelet: " + cliente.getId() + "e" + endereco.getId());

		endereco = enderecoService.selecionarEndereco(cliente, endereco);

		request.setAttribute("endereco", endereco);
		RequestDispatcher rd = request.getRequestDispatcher("/areaCliente/MeusEnderecosEditar.jsp");
		rd.forward(request, response);

	}

	protected void EditarEndereco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("voce conseguiu chegar aqui no EditarEndereco:)");

		int id = Integer.parseInt(request.getParameter("id"));
		int idEndereco = Integer.parseInt(request.getParameter("idEnd"));

		String nome = request.getParameter("nome");
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

		String tipoEnderecoParam = request.getParameter("tipoEndereco");
		TiposEndereco tiposEndereco = TiposEndereco.valueOf(tipoEnderecoParam);

		endereco.setCliente(cliente);
		endereco.setNome(nome);
		endereco.setTipoResidencia(tipoResidencia);
		endereco.setTipoLogradouro(tipoLogradouro);
		endereco.setLogradouro(logradouro);
		endereco.setNumero(numero);
		endereco.setBairro(bairro);
		endereco.setCep(cep);
		endereco.setCidade(cidade);
		endereco.setEstado(estado);
		endereco.setPais(pais);
		endereco.setPadrao("N");
		endereco.setObservacao(observacoes);
		endereco.setTipos(tiposEndereco);

		cliente.setId(id);
		endereco.setId(idEndereco);

		System.out
				.println("o id do cliente no  EditarEndereco�o servelet: " + cliente.getId() + "e" + endereco.getId());

		enderecoService.EditarEndereco(cliente, endereco);

		response.sendRedirect(request.getContextPath() + "/areaCliente/MeusEnderecos.html?id=" + id);

	}

	protected void ExcluirEndereco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("voce conseguiu chegar aqui no ExcluiEndereco:)");

		int id = Integer.parseInt(request.getParameter("id"));
		int idEndereco = Integer.parseInt(request.getParameter("idEnd"));

		cliente.setId(id);
		endereco.setId(idEndereco);

		System.out.println(
				"o id do cliente no tela editar endere�o servelet: " + cliente.getId() + "e" + endereco.getId());

		String retorno = enderecoService.ExcluirEndereco(cliente, endereco);

		response.sendRedirect(request.getContextPath() + "/areaCliente/MeusEnderecos.html?id=" + id);
	}
}