package model.entity;

import java.sql.Date;

public class Notificacoes {

	private String titulo;
	private String descricao;
	private Date data;
	private Cliente cliente;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(java.util.Date dataAtual) {
		this.data = (Date) dataAtual;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
