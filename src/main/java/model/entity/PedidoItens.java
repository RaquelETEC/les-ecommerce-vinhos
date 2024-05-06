package model.entity;

public class PedidoItens {

	private int id;
	private PedidoVenda pedidoVenda; 
	private String descricao;
	private int quantidade;
	private Double preco; 
	private Double totalProduto; 
	private TiposStatusItensPedido tipos;
	
	public PedidoItens() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PedidoItens(int id, PedidoVenda pedidoVenda, String descricao, int quantidade, Double preco,
			Double totalProduto, TiposStatusItensPedido tipos) {
		super();
		this.id = id;
		this.pedidoVenda = pedidoVenda;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco = preco;
		this.totalProduto = totalProduto;
		this.tipos = tipos;
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
	
	
}
