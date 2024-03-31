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

@WebServlet(urlPatterns = { "/inserirEndereco", "/areaCliente/MeusEnderecos.html" })
public class ControllerEndereco extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	Cliente cliente = new Cliente();

	EnderecoService enderecoService = new EnderecoService();
	
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
		else if(action.equals("/areaCliente/MeusEnderecos.html")){
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
            System.out.println("o id do cliente no adicionar endere�o servelet: "+cliente.getId());

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
            String nome = request.getParameter("TypeNomeEnd");
            
            endereco.setCliente(cliente);
            endereco.setTipoResidencia(tipoResidencia);
            endereco.setTipoLogradouro(tipoLogradouro);
            endereco.setLogradouro(logradouro);
            endereco.setNumero(numero);
            endereco.setBairro(bairro);
            endereco.setCep(cep);
            endereco.setCidade(cidade);
            endereco.setEstado(estado);
            endereco.setPais(pais);
            endereco.setPadrao(enderecoPadrao);
            endereco.setObservacao(observacoes);		
            endereco.setNome(nome);

			//daoEndereco.inserirEndereco(cliente, endereco);
			
	}
	
	protected void ExibirEndereco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("voce conseguiu chegar aqui no ExibirEndereco :)");

		int id = Integer.parseInt(request.getParameter("id"));
		cliente.setId(id);
		
	    //Cliente cliente = (Cliente) request.getAttribute("cliente");
	    
	    System.out.println("o id que chegou aqui no listar endereço:"+cliente.getId());
	    
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
	
}