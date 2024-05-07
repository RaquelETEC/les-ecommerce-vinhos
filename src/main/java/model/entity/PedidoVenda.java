package model.entity;

import java.util.ArrayList;
import java.util.Date;

public class PedidoVenda {
		
	private int id;
	Cliente cliente;
	Endereco endereco;
	Cupons cupom;
	private String status;
	private Date data;
	private Double valor;
	private ArrayList<PedidoItens> PedidoItens = new ArrayList<>();

	public PedidoVenda() {
		super();
	}

	public PedidoVenda(int id, Cliente cliente, Endereco endereco, Cupons cupom, String status, Date data, Double valor,
			ArrayList<model.entity.PedidoItens> pedidoItens) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.endereco = endereco;
		this.cupom = cupom;
		this.status = status;
		this.data = data;
		this.valor = valor;
		PedidoItens = pedidoItens;
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

	public Cupons getCupom() {
		return cupom;
	}

	public void setCupom(Cupons cupom) {
		this.cupom = cupom;
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public ArrayList<PedidoItens> getPedidoItens() {
		return PedidoItens;
	}

	public void setPedidoItens(ArrayList<PedidoItens> pedidoItens) {
		PedidoItens = pedidoItens;
	}
	
}
