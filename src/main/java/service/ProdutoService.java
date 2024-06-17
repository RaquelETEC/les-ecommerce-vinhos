package service;

import java.util.ArrayList;
import java.util.List;

import dao.DAOProdutos;
import model.entity.Categoria;
import model.entity.Harmonizacao;
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

	public List<Produtos> listarProdutosDataAnalysis() {
		return daoProdutos.fetchAllProducts();
	}

	public ArrayList<Produtos> listarProdutosAreaADM() {
		return daoProdutos.ListarProdutosAreaADM();
	}

	public Produtos selecionarProduto(Produtos produto, Precificacao precificacao, Categoria categoria) {
		System.out.println("Cheguei no selecionarProduto Service");

		return daoProdutos.selecionarProduto(produto, precificacao, categoria);

	}

	public List<Produtos> listarProdutosDisponiveis() {
		return daoProdutos.ProdutosDisponiveis();
	}

	public Produtos listarProdutoPorID(int id) {
		return daoProdutos.buscarProdutoPorIdSimples(id);
	}
	
	public boolean EditarProduto(Produtos produto, Precificacao precificacao, Categoria categoria) {
		System.out.println("Cheguei no EditarProduto Service");
		
		return daoProdutos.atualizarProdutoEAssociados(produto, precificacao, categoria);
		
	}
	
    public ArrayList<Harmonizacao> getHarmonizacoesByProduto(Produtos produto) {
    	return daoProdutos.getHarmonizacoesByProduto(produto);
    }

}
