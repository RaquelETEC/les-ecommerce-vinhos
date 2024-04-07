package model.entity;

public class CarrinhoItem {
    private String produto;
    private double preco;
    private int quantidade;
    
    
	public CarrinhoItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CarrinhoItem(String produto, double preco, int quantidade) {
		super();
		this.produto = produto;
		this.preco = preco;
		this.quantidade = quantidade;
	}
	
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

   
}