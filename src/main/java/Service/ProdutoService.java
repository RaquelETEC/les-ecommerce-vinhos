package Service;

import java.util.ArrayList;

import Dao.DAOProdutos;
import model.entity.Categoria;
import model.entity.Precificacao;
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
	
	
	public ArrayList<Produtos> listarProdutosAreaADM() {
		return daoProdutos.ListarProdutosAreaADM();
	}
	
	public Produtos selecionarProduto(Produtos produto, Precificacao precificacao, Categoria categoria) {
		System.out.println("Cheguei no selecionarProduto Service");
		
		return daoProdutos.selecionarProduto(produto, precificacao, categoria);
		
	}
	


}
