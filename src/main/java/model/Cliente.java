package model;

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
	
	private ArrayList<Endereco> listadeEnderecos = new ArrayList<>();

	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Cliente(int id, String nome, String email, String senha, String cpf, String tipoTelefone, String telefone,
			Date dataNasc, String genero, ArrayList<Endereco> listadeEnderecos) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.tipoTelefone = tipoTelefone;
		this.telefone = telefone;
		this.dataNasc = dataNasc;
		this.genero = genero;
		this.listadeEnderecos = listadeEnderecos;
	}


	public int getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}


	public String getEmail() {
		return email;
	}


	public String getSenha() {
		return senha;
	}


	public String getCpf() {
		return cpf;
	}


	public String getTipoTelefone() {
		return tipoTelefone;
	}


	public String getTelefone() {
		return telefone;
	}


	public Date getDataNasc() {
		return dataNasc;
	}


	public String getGenero() {
		return genero;
	}


	public ArrayList<Endereco> getListadeEnderecos() {
		return listadeEnderecos;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public void setTipoTelefone(String tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public void setListadeEnderecos(ArrayList<Endereco> listadeEnderecos) {
		this.listadeEnderecos = listadeEnderecos;
	}
	
	
	

	
}
