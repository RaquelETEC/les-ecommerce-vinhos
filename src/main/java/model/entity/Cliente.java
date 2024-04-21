package model.entity;

import java.util.ArrayList;
import java.util.Date;

public class Cliente {

	private int id;
	private String nome;
	private String email;
	private String senha;
	private String cpf;
	private String tipoTelefone;
	private String telefone;
	private Date dataNasc;
	private String genero;
	private String status;

	private ArrayList<Endereco> listadeEnderecos = new ArrayList<>();
	private ArrayList<CartaoDeCredito> listaDeCartoes = new ArrayList<>();
	private ArrayList<Cupons> listadeCupons = new ArrayList<>();


	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

	public Cliente(int id, String nome, String email, String senha, String cpf, String tipoTelefone, String telefone,
			Date dataNasc, String genero, String status, ArrayList<Endereco> listadeEnderecos,
			ArrayList<CartaoDeCredito> listaDeCartoes, ArrayList<Cupons> listadeCupons) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.tipoTelefone = tipoTelefone;
		this.telefone = telefone;
		this.dataNasc = dataNasc;
		this.genero = genero;
		this.status = status;
		this.listadeEnderecos = listadeEnderecos;
		this.listaDeCartoes = listaDeCartoes;
		this.listadeCupons = listadeCupons;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getTipoTelefone() {
		return tipoTelefone;
	}


	public void setTipoTelefone(String tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public Date getDataNasc() {
		return dataNasc;
	}


	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public ArrayList<Endereco> getListadeEnderecos() {
		return listadeEnderecos;
	}


	public void setListadeEnderecos(ArrayList<Endereco> listadeEnderecos) {
		this.listadeEnderecos = listadeEnderecos;
	}


	public ArrayList<CartaoDeCredito> getListaDeCartoes() {
		return listaDeCartoes;
	}


	public void setListaDeCartoes(ArrayList<CartaoDeCredito> listaDeCartoes) {
		this.listaDeCartoes = listaDeCartoes;
	}


	public ArrayList<Cupons> getListadeCupons() {
		return listadeCupons;
	}


	public void setListadeCupons(ArrayList<Cupons> listadeCupons) {
		this.listadeCupons = listadeCupons;
	}

	
}
