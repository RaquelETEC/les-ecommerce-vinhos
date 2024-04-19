package model.entity;

public class CarrinhoItens {
	private int id;
	private CarrinhoDeCompras carrinho;
	private Produtos produto;
	private int quantProd;
	private boolean removido;
	private String motivoRemocao;

	public CarrinhoItens() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CarrinhoItens(int id, CarrinhoDeCompras carrinho, Produtos produto, int quantProd, boolean removido,
			String motivoRemocao) {
		super();
		this.id = id;
		this.carrinho = carrinho;
		this.produto = produto;
		this.quantProd = quantProd;
		this.removido = removido;
		this.motivoRemocao = motivoRemocao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CarrinhoDeCompras getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(CarrinhoDeCompras carrinho) {
		this.carrinho = carrinho;
	}

	public Produtos getProduto() {
		return produto;
	}

	public void setProduto(Produtos produto) {
		this.produto = produto;
	}

	public int getQuantProd() {
		return quantProd;
	}

	public void setQuantProd(int quantProd) {
		this.quantProd = quantProd;
	}

	public boolean isRemovido() {
		return removido;
	}

	public void setRemovido(boolean removido) {
		this.removido = removido;
	}

	public String getMotivoRemocao() {
		return motivoRemocao;
	}

	public void setMotivoRemocao(String motivoRemocao) {
		this.motivoRemocao = motivoRemocao;
	}

	
}
