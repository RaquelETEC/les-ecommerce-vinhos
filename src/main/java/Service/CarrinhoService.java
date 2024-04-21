package Service;

import java.util.ArrayList;

import Dao.DAOProdutos;
import Dao.DaoCarrinho;
import Dao.DaoEndereco;
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
	    }
	  
		return itemsCarrinho;
	}


	public String AlterarQuantidadeProd(int carrinhoId, int prodId, int quantidade) {
		
		if(quantidade <= 0 ) {
			return "Não é possivel adicionar um item com 0 quantidades ao carrinho";
		}
		else {
			return daoCarrinho.AlterarQuantidade(carrinhoId,prodId,quantidade);
		}
	}


}
