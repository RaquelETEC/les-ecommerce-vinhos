package Service;

import java.util.ArrayList;

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
		
		itemsCarrinho = daoCarrinho.ListarCarrinho(carrinho);
	
		
		return itemsCarrinho;
	}


}
