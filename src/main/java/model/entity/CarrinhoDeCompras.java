package model.entity;

import java.util.ArrayList;

public class CarrinhoDeCompras {
    private int id;
    private Cliente cliente;
    private int quantItems;

	private ArrayList<CarrinhoItens> listaitems = new ArrayList<>();

	public CarrinhoDeCompras() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CarrinhoDeCompras(int id, Cliente cliente, int quantItems, ArrayList<CarrinhoItens> listaitems) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.quantItems = quantItems;
		this.listaitems = listaitems;
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

	public int getQuantItems() {
		return quantItems;
	}

	public void setQuantItems(int quantItems) {
		this.quantItems = quantItems;
	}

	public ArrayList<CarrinhoItens> getListaitems() {
		return listaitems;
	}

	public void setListaitems(ArrayList<CarrinhoItens> listaitems) {
		this.listaitems = listaitems;
	}

	
    
}