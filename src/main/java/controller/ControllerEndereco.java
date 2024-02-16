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
import model.Endereco;
import model.DaoEndereco;

// TODO: Auto-generated Javadoc

@WebServlet(urlPatterns = { "/inserirEndereco" })
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
        
            endereco.setId_cliente(cliente);
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
			//response.sendRedirect("main");    

             
			
		    response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
            response.getWriter().write("<p>ID cliente: " + cliente.getId() + "</p>");
            response.getWriter().write("<p>Nome: " + cliente.getNome() + "</p>");
			response.getWriter().write("<p>Email: " + cliente.getEmail() + "</p>");
			response.getWriter().write("<p>Senha: " + cliente.getSenha() + "</p>");
			response.getWriter().write("<p>Senha Repetida: " + cliente.getSenha() + "</p>");
			response.getWriter().write("<p>CPF: " + cliente.getCpf() + "</p>");
			response.getWriter().write("<p>Tipo de Telefone: " + cliente.getTipoTelefone() + "</p>");
			response.getWriter().write("<p>Telefone: " + cliente.getTelefone() + "</p>");
			response.getWriter().write("<p>Data de Nascimento: " + cliente.getDataNasc() + "</p>");
			response.getWriter().write("<p>Gênero: " + cliente.getGenero() + "</p>");
            response.getWriter().write("<hr> Endereço ");
			response.getWriter().write("<p>Tipo de Residência: " + endereco.getTipoResidencia() + "</p>");
			response.getWriter().write("<p>Tipo de Logradouro: " + endereco.getTipoLogradouro() + "</p>");
			response.getWriter().write("<p>Logradouro: " + endereco.getLogradouro() + "</p>");
			response.getWriter().write("<p>Número: " + endereco.getNumero() + "</p>");
			response.getWriter().write("<p>Bairro: " + endereco.getBairro() + "</p>");
			response.getWriter().write("<p>Cidade: " + endereco.getCidade() + "</p>");
			response.getWriter().write("<p>País: " + endereco.getPais() + "</p>");
			response.getWriter().write("<p>Observações: " + endereco.getObservacao() + "</p>");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/areaCliente");
			dispatcher.forward(request, response);
		
	}
}