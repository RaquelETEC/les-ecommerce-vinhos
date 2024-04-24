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
import Service.CartoesService;
import Service.CupomService;
import Service.EnderecoService;
import model.entity.CarrinhoDeCompras;
import model.entity.CarrinhoItens;
import model.entity.CartaoDeCredito;
import model.entity.Cliente;
import model.entity.Cupons;
import model.entity.Endereco;
import model.entity.Produtos;
import model.entity.TiposEndereco;

@WebServlet(urlPatterns = { "/AdicionarAoCarrinho", "/ExibirCarrinho", "/AlterarQuantCarrinho", "/RemoverProdutoCarrinho" , "/FinalizarCompra"})
public class ControllerVenda extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CarrinhoService carrinhoService = new CarrinhoService();
	
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
	    }else if(action.equals("/AlterarQuantCarrinho")) {
			AlterarQuantCarrinho(request, response);
		}	
	    else if(action.equals("/RemoverProdutoCarrinho")) {
			RemoverProduto(request, response);
	    }
	    else if(action.equals("/FinalizarCompra")) {
	    	FinalizarCompraTela(request, response);
	    }
		else {
			System.out.println("erro ao redirecionar " + action);
			response.sendRedirect("index.html");
		}
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
        
       
        //itens não removidos do carrinho 
        ArrayList<CarrinhoItens> lista = new ArrayList<>();
        //itens removidos do carrinho
        ArrayList<CarrinhoItens> listaRemovidos = new ArrayList<>();
        
        for (CarrinhoItens item : listaItens) {
            if (item.isRemovido()) {
           //	System.out.println("-----------------------");
           	// 	System.out.println(item.getProduto().getDesc()+" Produto removido");
           	//	System.out.println(item.getQuantProd());
           	//	System.out.println(item.getProduto().getPro_preco_venda());
           	//	System.out.println(item.getProduto().getJustificativa());

                listaRemovidos.add(item);
            } else {
            	//	System.out.println("-----------------------");
            	//	System.out.println(item.getProduto().getDesc()+" Produto no carrinho");
            	//	System.out.println(item.getQuantProd());
            	//	System.out.println(item.getProduto().getPro_preco_venda());
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
	private void RemoverProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    System.out.println("RemoverProduto");
	    
	    // Obter parâmetros da requisição
	    int carrinhoId = Integer.parseInt(request.getParameter("carrinhoId"));
	    int prodId = Integer.parseInt(request.getParameter("produtoId"));
	    
	    // Criar objetos para o carrinho e produto
	    CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
	    carrinho.setId(carrinhoId);
	    
	    Produtos produto = new Produtos();
	    produto.setId(prodId);
	    
	    // Chamar o serviço para remover o item do carrinho
	    String resposta = carrinhoService.removerItem(carrinho, produto);
	    System.out.println(resposta);
	    
	    // Enviar resposta para o cliente
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
		
		ArrayList<Cupons> listaCuponsPromocional = new ArrayList<>();
		ArrayList<Cupons> listaCuponsTroca = new ArrayList<>();

		//Separa a lista de cupons promocionais e cupons de troca para exibir na tela
		for (Cupons cupomItem : listaCupons) {
			if (cupomItem.getTipo().contains("P")) {
				listaCuponsPromocional.add(cupomItem);
			} 
			else if (cupomItem.getTipo().contains("T")) {
				listaCuponsTroca.add(cupomItem);
			}
		} 
		
		//listagem de cartões
		CartoesService cartaoService = new CartoesService();
		//ArrayList<CartaoDeCredito> listaCartoes = cartaoService.listarCartoes(cliente);

		//Lista de endereços
		request.setAttribute("listaEnderecosEntrega", listaEntrega);
	
		//Lista de cupom
		request.setAttribute("listaCuponsPromocional", listaCuponsPromocional);
		request.setAttribute("listaCuponsTroca", listaCuponsTroca);
		
		//Lista de cartao
	//	request.setAttribute("listaCartoes", listaCartoes);		
	  
		//Lista de produtos
		request.setAttribute("itens", listaItens);
		RequestDispatcher rd = request.getRequestDispatcher("/areaVenda/Venda.jsp");
		rd.forward(request, response);
		
	}

}
