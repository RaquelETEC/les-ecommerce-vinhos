package model.entity;

public class PedidoItens {

	private int id;
	private PedidoVenda pedidoVenda;
	private Produtos produto;
	private String descricao;
	private int quantidade;
	private Double preco;
	private Double totalProduto;
	private TiposStatusItensPedido tipos;
	private int quantidadeTrocada;
	private int quantidadeSolicitadaTroca;
	private Troca troca;

	public PedidoItens() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PedidoItens(int id, PedidoVenda pedidoVenda, Produtos produto, String descricao, int quantidade,
			Double preco, Double totalProduto, TiposStatusItensPedido tipos, int quantidadeTrocada,
			int quantidadeSolicitadaTroca, Troca troca) {
		super();
		this.id = id;
		this.pedidoVenda = pedidoVenda;
		this.produto = produto;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco = preco;
		this.totalProduto = totalProduto;
		this.tipos = tipos;
		this.quantidadeTrocada = quantidadeTrocada;
		this.quantidadeSolicitadaTroca = quantidadeSolicitadaTroca;
		this.troca = troca;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PedidoVenda getPedidoVenda() {
		return pedidoVenda;
	}

	public void setPedidoVenda(PedidoVenda pedidoVenda) {
		this.pedidoVenda = pedidoVenda;
	}

	public Produtos getProduto() {
		return produto;
	}

	public void setProduto(Produtos produto) {
		this.produto = produto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Double getTotalProduto() {
		return totalProduto;
	}

	public void setTotalProduto(Double totalProduto) {
		this.totalProduto = totalProduto;
	}

	public TiposStatusItensPedido getTipos() {
		return tipos;
	}

	public void setTipos(TiposStatusItensPedido tipos) {
		this.tipos = tipos;
	}

	public int getQuantidadeTrocada() {
		return quantidadeTrocada;
	}

	public void setQuantidadeTrocada(int quantidadeTrocada) {
		this.quantidadeTrocada = quantidadeTrocada;
	}

	public int getQuantidadeSolicitadaTroca() {
		return quantidadeSolicitadaTroca;
	}

	public void setQuantidadeSolicitadaTroca(int quantidadeSolicitadaTroca) {
		this.quantidadeSolicitadaTroca = quantidadeSolicitadaTroca;
	}

	public Troca getTroca() {
		return troca;
	}

	public void setTroca(Troca troca) {
		this.troca = troca;
	}

}
