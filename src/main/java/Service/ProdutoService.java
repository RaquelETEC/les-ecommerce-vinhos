package Service;

import java.util.ArrayList;

import Dao.DAOProdutos;
import model.entity.Produtos;


public class ProdutoService {
	
	private DAOProdutos daoProdutos;
	
	public ProdutoService() {
	this.daoProdutos = new DAOProdutos();
	
	}
	
	public ArrayList<Produtos> listarProdutos(Produtos produtos) {
		return daoProdutos.ListarProdutos(produtos);
	}

}
