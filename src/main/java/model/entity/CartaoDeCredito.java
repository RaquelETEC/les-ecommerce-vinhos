package model.entity;

public class CartaoDeCredito {
	private int id;
	private Cliente cliente;
	private String numero;
	private String nome;
	private String padrao;
	private BandeiraCartao bandeira;
	private int codigoSeguranca;
	private Double valor;
	private boolean cartaoNoPerfil; 

	public CartaoDeCredito() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartaoDeCredito(int id, Cliente cliente, String numero, String nome, String padrao, BandeiraCartao bandeira,
			int codigoSeguranca, Double valor, boolean cartaoNoPerfil) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.numero = numero;
		this.nome = nome;
		this.padrao = padrao;
		this.bandeira = bandeira;
		this.codigoSeguranca = codigoSeguranca;
		this.valor = valor;
		this.cartaoNoPerfil = cartaoNoPerfil;
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

	public String getPadrao() {
		return padrao;
	}

	public void setPadrao(String padrao) {
		this.padrao = padrao;
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public boolean isCartaoNoPerfil() {
		return cartaoNoPerfil;
	}

	public void setCartaoNoPerfil(boolean cartaoNoPerfil) {
		this.cartaoNoPerfil = cartaoNoPerfil;
	}

	
}
