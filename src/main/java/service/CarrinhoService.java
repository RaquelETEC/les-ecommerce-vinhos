package service;

import java.util.ArrayList;

import dao.DAOProdutos;
import dao.DaoCarrinho;
import model.entity.CarrinhoDeCompras;
import model.entity.CarrinhoItens;
import model.entity.Cliente;
import model.entity.Produtos;
import model.entity.StatusCarrinhoItens;

public class CarrinhoService {

	private DaoCarrinho daoCarrinho; 

	CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
	ArrayList<CarrinhoItens> itemsCarrinho;
	Produtos produto = new Produtos(); 
	DAOProdutos daoprod = new DAOProdutos(); 
	
	public CarrinhoService() {
		this.daoCarrinho = new DaoCarrinho();
	}
	
	public CarrinhoDeCompras SelecionarCarrinho(Cliente cliente) {
		return daoCarrinho.SelecionarCarrinho(cliente);
	}
	
	public ArrayList<CarrinhoItens> listarItems(CarrinhoDeCompras carrinho) {
		Produtos produto = new Produtos();
		
		itemsCarrinho = daoCarrinho.listarCarrinho(carrinho);
		
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

	public String alterarStatusCarrinhoItem(CarrinhoItens carrinhoItens) {
		return daoCarrinho.alterarStatusCarrinho(carrinhoItens);
	}

	public ArrayList<CarrinhoItens> listarItensAtivos(CarrinhoDeCompras carrinho) {
		Produtos produto = new Produtos();
		
		itemsCarrinho = daoCarrinho.listarItensAtivos(carrinho);
		
	  for (CarrinhoItens item : itemsCarrinho) {
	        produto = daoprod.buscarProdutoPorId(item.getProduto().getId());
	        item.setProduto(produto);  
	        item.setId(item.getId());
	    }
	  
		return itemsCarrinho;
	}

}
