package model;

import java.util.ArrayList;
import java.util.Date;

public class Cliente {
	
	private int id;
	private String nome;
	private String genero;
	private String cpf;
	private Date dataNasc;
	private Telefone telefone; 
	
	private ArrayList<Endereco> listadeEnderecos = new ArrayList<>();
	//private ArrayList<CartaoDeCredito> listadeCartoes = new ArrayList<>();
	//private ArrayList<Pedido> listadePedidos = new ArrayList<>();
	//private Login login;
	//private CarrinhoDeCompras carrinho;	

	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Cliente(int id, String nome, String genero, String cpf, Date dataNasc, Telefone telefone,
			ArrayList<Endereco> listadeEnderecos) {
		this.id = id;
		this.nome = nome;
		this.genero = genero;
		this.cpf = cpf;
		this.dataNasc = dataNasc;
		this.telefone = telefone;
		this.listadeEnderecos = listadeEnderecos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public ArrayList<Endereco> getListadeEnderecos() {
		return listadeEnderecos;
	}

	public void setListadeEnderecos(ArrayList<Endereco> listadeEnderecos) {
		this.listadeEnderecos = listadeEnderecos;
	}
	
	

	
}
