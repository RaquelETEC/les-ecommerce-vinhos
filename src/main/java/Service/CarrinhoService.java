package Service;

import java.util.ArrayList;

import Dao.DAOProdutos;
import Dao.DaoCarrinho;
import model.entity.CarrinhoDeCompras;
import model.entity.CarrinhoItens;
import model.entity.Cliente;
import model.entity.Produtos;

public class CarrinhoService {

	private DaoCarrinho daoCarrinho; 

	CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
	ArrayList<CarrinhoItens> itemsCarrinho;
	Produtos produto = new Produtos(); 
	DAOProdutos daoprod = new DAOProdutos(); 
	
	public CarrinhoService() {
		this.daoCarrinho = new DaoCarrinho();
	}
	
	
	public String adicionarCarrinho() {
		return "eba"; 
	}
	
	public CarrinhoDeCompras SelecionarCarrinho(Cliente cliente) {
		return daoCarrinho.SelecionarCarrinho(cliente);
	}
	
	public ArrayList<CarrinhoItens> listarItems(CarrinhoDeCompras carrinho) {
		Produtos produto = new Produtos();
		
		itemsCarrinho = daoCarrinho.ListarCarrinho(carrinho);
		
	  for (CarrinhoItens item : itemsCarrinho) {
	        produto = daoprod.buscarProdutoPorId(item.getProduto().getId());
	        item.setProduto(produto);  
	        item.setId(item.getId());
	    }
	  
		return itemsCarrinho;
	}

	public String AlterarQuantidadeProd(CarrinhoItens CarrinhoItem, int quantidade) {
		
		if(quantidade <= 0 ) {
			return "Não é possivel adicionar um item com 0 quantidades ao carrinho";
		}
		else {
			return daoCarrinho.AlterarQuantidade(CarrinhoItem, quantidade);
		}
	}


	public String AdicionarAoCarrinhoService(Cliente cliente, Produtos produto, int quant) {
		
		CarrinhoDeCompras carrinho = daoCarrinho.SelecionarCarrinho(cliente);
		
		return (String) daoCarrinho.adicionarAoCarrinho(carrinho,produto,quant);
		
	}


	public String removerItem(CarrinhoDeCompras carrinho, Produtos produto) {
		// TODO Auto-generated method stub
		return (String) daoCarrinho.removerItem(carrinho,produto);
	}

	public ArrayList<CarrinhoItens> listarItensAtivos(CarrinhoDeCompras carrinho) {
		Produtos produto = new Produtos();
		
		itemsCarrinho = daoCarrinho.ListarItensAtivos(carrinho);
		
	  for (CarrinhoItens item : itemsCarrinho) {
	        produto = daoprod.buscarProdutoPorId(item.getProduto().getId());
	        item.setProduto(produto);  
	        item.setId(item.getId());
	    }
	  
		return itemsCarrinho;
	}

}
