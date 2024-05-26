package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.CarrinhoDeCompras;
import model.entity.CarrinhoItens;
import model.entity.CartaoDeCredito;
import model.entity.Cliente;
import model.entity.Cupons;
import model.entity.Endereco;
import model.entity.PedidoItens;
import model.entity.PedidoVenda;
import model.entity.Produtos;
import model.entity.StatusCarrinhoItens;
import model.entity.TiposEndereco;
import model.entity.TiposStatusItensPedido;
import service.CarrinhoService;
import service.CartoesService;
import service.ClienteService;
import service.CupomService;
import service.EnderecoService;
import service.PedidoVendaService;
import dto.PedidoVendaDTO;

@WebServlet(urlPatterns = { "/AdicionarAoCarrinho", "/ExibirCarrinho", 
		"/AlterarQuantCarrinho", "/AlterarStatusItemCarriho" , "/FinalizarCompra", 
		"/CadastrarPedidoVenda" , "/areaCliente/MinhasCompras.html", "/areaCliente/solicitarTroca.html"})
public class ControllerVenda extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CarrinhoService carrinhoService = new CarrinhoService();
	PedidoVendaService vendaService = new PedidoVendaService(); 
	ClienteService clienteService = new ClienteService();
	
	
	public ControllerVenda() {
		super();
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String action = request.getServletPath();
	    
	    if(action.equals("/CadastrarPedidoVenda")) {
	    	CadastrarPedidoVenda(request, response);
	    }
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String action = request.getServletPath();

	    System.out.println("chegou aqui: " + action);

	    if (action.equals("/AdicionarAoCarrinho")) {
	        AdicionarAoCarriho(request, response);
	    } else if (action.equals("/ExibirCarrinho")) {
	        ExibirCarrinho(request, response);
	    }else if(action.equals("/AlterarQuantCarrinho")) {
			AlterarQuantCarrinho(request, response);
		}	
	    else if(action.equals("/AlterarStatusItemCarriho")) {
	    	alterarStatusItemCarrihoController(request, response);
	    }
	    else if(action.equals("/FinalizarCompra")) {
	    	FinalizarCompraTela(request, response);
	    }
	    else if(action.equals("/areaCliente/MinhasCompras.html")) {
	    	MinhasComprasPage(request, response);
	    }
	    else if (action.equals("/areaCliente/solicitarTroca.html")) {
	    	editarStatusTrocas(request,response);
	    }
		else {
			System.out.println("erro ao redirecionar " + action);
			response.sendRedirect("index.html");
		}
	}

	private void editarStatusTrocas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("Controller solicitar troca");
	    PedidoVenda pedido = new PedidoVenda();

	    pedido.setId(Integer.parseInt(request.getParameter("pedido")));
	    pedido.setStatus(request.getParameter("novoStatusPedido"));
	    String[] itensSelecionados = request.getParameterValues("itens");
	    
        List<PedidoItens> listaItems = Arrays.stream(itensSelecionados)
                .map(id -> {
                    try {
                        int itemId = Integer.parseInt(id);
                        return new PedidoItens(itemId, pedido, id, null, itemId, null, null, null);
                    } catch (NumberFormatException e) {
                        return null; // Ou throw new RuntimeException("Erro ao converter ID para inteiro: " + id);
                    }
                })
                .filter(item -> item != null) 
                .collect(Collectors.toList()); 
                	    
	    
	    TiposStatusItensPedido tipoSolicitacao = TiposStatusItensPedido.valueOf(request.getParameter("tipoSolicitacao"));

	    String resposta = vendaService.trocaService(pedido,listaItems,tipoSolicitacao);
	    
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(resposta);
	}

	private void MinhasComprasPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Minhas Compras Controller");
		
		Cliente cliente = new Cliente(); 
		cliente.setId(Integer.parseInt(request.getParameter("id")));
		
		cliente = clienteService.selecionarCliente(cliente);
		
		ArrayList<PedidoVenda> listaPedidos = new ArrayList<>();
		
		listaPedidos = vendaService.listarPedidoVenda(cliente, null,0);
		
		request.setAttribute("cliente", cliente);
		request.setAttribute("listaPedidos", listaPedidos);
		RequestDispatcher rd = request.getRequestDispatcher("/areaCliente/MinhasCompras.jsp");
		rd.forward(request, response);
		
		
		
	}

	private void CadastrarPedidoVenda(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {	    System.out.println("controller cadastrar pedido venda");
	    
	    PedidoVenda pedido = new PedidoVenda();
	    Cliente cliente = new Cliente(); 
	    Endereco endereco = new Endereco();
		CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

        // Instancie um ObjectMapper para converter o JSON em objetos Java
        ObjectMapper mapper = new ObjectMapper();

        // Desserialize o JSON recebido no objeto PedidoVendaDTO
        PedidoVendaDTO pedidoVendaDTO = mapper.readValue(request.getReader(), PedidoVendaDTO.class);
        
        Date dataAtual = new Date();
	    
	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        String dataFormatada = dateFormat.format(dataAtual);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
        endereco.setId(pedidoVendaDTO.getIdEndereco());
        cliente.setId(pedidoVendaDTO.getIdCliente());
        
        carrinho = carrinhoService.SelecionarCarrinho(cliente);
	    ArrayList<CarrinhoItens> listaItens = carrinhoService.listarItensAtivos(carrinho);
	    	
        ArrayList<CartaoDeCredito> listaCartoes = (ArrayList<CartaoDeCredito>) pedidoVendaDTO.getCartoes();
        ArrayList<Cupons> listaCupons = (ArrayList<Cupons>) pedidoVendaDTO.getCupons();
	    
        pedido.setCliente(cliente);
        pedido.setTotalPedido(pedidoVendaDTO.getTotalPedido());
        pedido.setTotalDesconto(pedidoVendaDTO.getTotalDesconto());
        pedido.setTotalPagamento(pedidoVendaDTO.getTotalPagamento());
        pedido.setTotalFrete(pedidoVendaDTO.getTotalFrete());
        pedido.setTotalSaldo(pedidoVendaDTO.getTotalSaldo());
        pedido.setData(dataAtual);
        pedido.setEndereco(endereco);
	    pedido.setStatus("EM PROCESSAMENTO");
        pedido.setCupons(listaCupons);
        pedido.setCartoes(listaCartoes);
        
        String resposta = vendaService.cadastrarPedido(pedido,listaItens, listaCupons,listaCartoes );

	    
    	response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(resposta); 
	    response.getWriter().flush();
	   
   
	}

	protected void AdicionarAoCarriho(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // Obtendo os parâmetros da requisição
	    int idCliente = Integer.parseInt(request.getParameter("id"));
	    int idProduto = Integer.parseInt(request.getParameter("idProd"));
	    int quantidade = Integer.parseInt(request.getParameter("quant"));

	    System.out.println("Parâmetros recebidos: " + idCliente + ", " + idProduto + ", " + quantidade);

	    // Configurando o cliente e o produto
	    Cliente cliente = new Cliente();
	    cliente.setId(idCliente);

	    Produtos produto = new Produtos();
	    produto.setId(idProduto);

	    // Chamando o serviço para adicionar ao carrinho
	    String resposta = carrinhoService.AdicionarAoCarrinhoService(cliente, produto, quantidade);
	    System.out.println(resposta);
	    
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(resposta); 
	    response.getWriter().flush();
	    	
	}
	    
	protected void ExibirCarrinho(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		Cliente cliente = new Cliente(); 
        cliente.setId(Integer.parseInt(request.getParameter("id")));
        
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        carrinho = carrinhoService.SelecionarCarrinho(cliente);
    
        ArrayList<CarrinhoItens> listaItens = carrinhoService.listarItems(carrinho);
        ArrayList<CarrinhoItens> lista = new ArrayList<>();
        ArrayList<CarrinhoItens> listaRemovidos = new ArrayList<>();
        
        for (CarrinhoItens item : listaItens) {
            if (item.getStatus().ordinal() == StatusCarrinhoItens.REMOVIDO.ordinal()) {
                listaRemovidos.add(item);
            } else if(item.getStatus().ordinal() == StatusCarrinhoItens.ADICIONADO.ordinal()) {
            	lista.add(item);
            }
        }
        
		request.setAttribute("itensCarrinho", lista);
		request.setAttribute("itensRemovidosCarrinho", listaRemovidos);
		RequestDispatcher rd = request.getRequestDispatcher("/areaVenda/meuCarrinho.jsp");
		rd.forward(request, response);
	}
	

	protected void AlterarQuantCarrinho(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		System.out.println("cheguei no alterar carrinho");
		
		int quantidade = Integer.parseInt(request.getParameter("quant"));

	    CarrinhoItens CarrinhoItem = new CarrinhoItens();
	    CarrinhoItem.setId(Integer.parseInt(request.getParameter("idItem")));
	    
	    // Chamar o serviço para remover o item do carrinho
		String resposta = carrinhoService.AlterarQuantidadeProd(CarrinhoItem,quantidade);

	    System.out.println(resposta);
	    
	    // Enviar resposta para o cliente
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(resposta); 
	    response.getWriter().flush();
	}
	private void alterarStatusItemCarrihoController(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    System.out.println("RemoverProduto carrinho controller");
	    
	    int carrinhoId = Integer.parseInt(request.getParameter("carrinhoId"));
	    int quantidadeItem = Integer.parseInt(request.getParameter("quantidade"));
	    int prodId = Integer.parseInt(request.getParameter("produtoId"));
	    int statusOrdinal = Integer.parseInt(request.getParameter("idStatus"));
	    String motivo = request.getParameter("motivo");
	    
	    StatusCarrinhoItens status = StatusCarrinhoItens.values()[statusOrdinal];
	    
	    CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
	    carrinho.setId(carrinhoId);
	    
	    Produtos produto = new Produtos();
	    produto.setId(prodId);

	    CarrinhoItens carrinhoItens = new CarrinhoItens(); 
	    carrinhoItens.setQuantProd(quantidadeItem);
	    carrinhoItens.setMotivoRemocao(motivo);
	    carrinhoItens.setStatus(status);
	    carrinhoItens.setProduto(produto);

	    String resposta = carrinhoService.alterarStatusCarrinhoItem(carrinhoItens);
	    
	    System.out.println(resposta);
	    
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(resposta); 
	    response.getWriter().flush();
	}
	
	private void FinalizarCompraTela(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Cliente cliente = new Cliente();  
		cliente.setId(Integer.parseInt(request.getParameter("idCliente")));

		CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
	    carrinho = carrinhoService.SelecionarCarrinho(cliente);
	    
	    //listagem de itens do carrinho 
	    ArrayList<CarrinhoItens> listaItens = carrinhoService.listarItensAtivos(carrinho);
	    //Listagem de endereços do cliente 
		EnderecoService enderecoService = new EnderecoService();
		
		ArrayList<Endereco> lista = enderecoService.listarEnderecos(cliente);

		// Listas para cada tipo de endereço
		ArrayList<Endereco> listaEntrega = new ArrayList<>();

		// Separar endereços por tipo
		for (Endereco endereco : lista) {
			if (endereco.getTipos().equals(TiposEndereco.ENTREGA)) {
				System.out.println("o endereco: "+endereco.getNome());
				listaEntrega.add(endereco);
			}
		}

		CupomService cupomService = new CupomService();
		
		ArrayList<Cupons> listaCupons = cupomService.listarCupom(cliente);
		
		//listagem de cartões
		CartoesService cartaoService = new CartoesService();
		ArrayList<CartaoDeCredito> listaCartoes = cartaoService.listarCartoes(cliente);

		//Lista de endereços
		request.setAttribute("listaEnderecosEntrega", listaEntrega);
	
		//Lista de cupom
		request.setAttribute("listaCupons", listaCupons);
		
		//Lista de cartao
		request.setAttribute("listaCartoes", listaCartoes);		
	  
		//Lista de produtos
		request.setAttribute("itens", listaItens);
		RequestDispatcher rd = request.getRequestDispatcher("/areaVenda/Venda.jsp");
		rd.forward(request, response);
		
	}

}
