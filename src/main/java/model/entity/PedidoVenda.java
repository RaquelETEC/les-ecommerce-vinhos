package model.entity;

import java.util.ArrayList;
import java.util.Date;

public class PedidoVenda {
		
	private int id;
	Cliente cliente;
	Endereco endereco;
	private String status;
	private Date data;
	private Double totalPedido;
	private Double totalDesconto;
	private Double totalFrete;
	private Double totalPagamento;
	private Double totalSaldo;
	private ArrayList<PedidoItens> PedidoItens = new ArrayList<>();
	private ArrayList<CartaoDeCredito> cartoes = new ArrayList<>();
	private ArrayList<Cupons> cupons = new ArrayList<>();

	public PedidoVenda() {
		super();
	}
	
	public PedidoVenda(int id, Cliente cliente, Endereco endereco, String status, Date data, Double totalPedido,
			Double totalDesconto, Double totalFrete, Double totalPagamento, Double totalSaldo,
			ArrayList<model.entity.PedidoItens> pedidoItens, ArrayList<CartaoDeCredito> cartoes,
			ArrayList<Cupons> cupons) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.endereco = endereco;
		this.status = status;
		this.data = data;
		this.totalPedido = totalPedido;
		this.totalDesconto = totalDesconto;
		this.totalFrete = totalFrete;
		this.totalPagamento = totalPagamento;
		this.totalSaldo = totalSaldo;
		PedidoItens = pedidoItens;
		this.cartoes = cartoes;
		this.cupons = cupons;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getTotalPedido() {
		return totalPedido;
	}

	public void setTotalPedido(Double totalPedido) {
		this.totalPedido = totalPedido;
	}

	public Double getTotalDesconto() {
		return totalDesconto;
	}

	public void setTotalDesconto(Double totalDesconto) {
		this.totalDesconto = totalDesconto;
	}

	public Double getTotalFrete() {
		return totalFrete;
	}

	public void setTotalFrete(Double totalFrete) {
		this.totalFrete = totalFrete;
	}

	public Double getTotalPagamento() {
		return totalPagamento;
	}

	public void setTotalPagamento(Double totalPagamento) {
		this.totalPagamento = totalPagamento;
	}
	
	public Double getTotalSaldo() {
		return totalSaldo;
	}

	public void setTotalSaldo(Double totalSaldo) {
		this.totalSaldo = totalSaldo;
	}
	
	public ArrayList<PedidoItens> getPedidoItens() {
		return PedidoItens;
	}

	public void setPedidoItens(ArrayList<PedidoItens> pedidoItens) {
		PedidoItens = pedidoItens;
	}

	public ArrayList<CartaoDeCredito> getCartoes() {
		return cartoes;
	}

	public void setCartoes(ArrayList<CartaoDeCredito> cartoes) {
		this.cartoes = cartoes;
	}

	public ArrayList<Cupons> getCupons() {
		return cupons;
	}

	public void setCupons(ArrayList<Cupons> cupons) {
		this.cupons = cupons;
	}
	
	

	
	
}
