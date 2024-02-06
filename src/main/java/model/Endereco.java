package model;

public class Endereco {

	private String logradouro;
	private String numero;
	private String bairro;
	private String cep;
	private String tipoLogradouro;
	private String cidade;
	private String tipoEndereco;
	private String observacao;
	private Integer enderecoUsuarioId;
	
	public Endereco() {
		super();
	}

	public Endereco(String logradouro, String numero, String bairro, String cep, String tipoLogradouro, String cidade,
			String tipoEndereco, String observacao, Integer enderecoUsuarioId) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.tipoLogradouro = tipoLogradouro;
		this.cidade = cidade;
		this.tipoEndereco = tipoEndereco;
		this.observacao = observacao;
		this.enderecoUsuarioId = enderecoUsuarioId;
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
	public String getTipoLogradouro() {
		return tipoLogradouro;
	}
	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getTipoEndereco() {
		return tipoEndereco;
	}
	public void setTipoEndereco(String tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Integer getEnderecoUsuarioId() {
		return enderecoUsuarioId;
	}
	public void setEnderecoUsuarioId(Integer enderecoUsuarioId) {
		this.enderecoUsuarioId = enderecoUsuarioId;
	}
}
