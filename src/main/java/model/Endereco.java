package model;

public class Endereco {

	private Integer id;
	private Cliente id_cliente;
	private String tipoResidencia;
	private String tipoLogradouro;
	private String logradouro;
	private String numero;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;
	private String pais;
	private String padrao;
	private String observacao;
	
	public Endereco() {
		super();
	}

	
	public Endereco(Integer id, Cliente id_cliente, String tipoResidencia, String tipoLogradouro, String logradouro,
			String numero, String bairro, String cep, String cidade, String estado, String pais, String padrao,
			String observacao) {
		super();
		this.id = id;
		this.id_cliente = id_cliente;
		this.tipoResidencia = tipoResidencia;
		this.tipoLogradouro = tipoLogradouro;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.padrao = padrao;
		this.observacao = observacao;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Cliente id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getTipoResidencia() {
		return tipoResidencia;
	}

	public void setTipoResidencia(String tipoResidencia) {
		this.tipoResidencia = tipoResidencia;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPadrao() {
		return padrao;
	}

	public void setPadrao(String pdrao) {
		this.padrao = pdrao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	
	
}
