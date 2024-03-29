package model.entity;

public class CartaoDeCredito {
	private int id;
	private String numero;
	private String nome;
	private String padrao;
	private BandeiraCartao bandeira;
	private int codigoSeguranca;
	
	public CartaoDeCredito() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartaoDeCredito(int id, String numero, String nome, String padrao, BandeiraCartao bandeira, int codigoSeguranca) {
		super();
		this.id = id;
		this.numero = numero;
		this.nome = nome;
		this.padrao = padrao;
		this.bandeira = bandeira;
		this.codigoSeguranca = codigoSeguranca;
	}
	
	
	
	
	public String getPadrao() {
		return padrao;
	}

	public void setPadrao(String padrao) {
		this.padrao = padrao;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BandeiraCartao getBandeira() {
		return bandeira;
	}
	public void setBandeira(BandeiraCartao bandeira) {
		this.bandeira = bandeira;
	}
	public int getCodigoSeguranca() {
		return codigoSeguranca;
	}
	public void setCodigoSeguranca(int codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}
	
	
	
	
}
