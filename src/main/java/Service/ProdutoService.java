package Service;

import java.util.ArrayList;

import Dao.DAOProdutos;
import model.entity.Produtos;


public class ProdutoService {
	
	private DAOProdutos daoProdutos;
	Produtos produto = new Produtos();
	
	public ProdutoService() {
	this.daoProdutos = new DAOProdutos();
	
	}
	
	public ArrayList<Produtos> listarProdutos() {
		return daoProdutos.ListarProdutos();
	}
	


}
