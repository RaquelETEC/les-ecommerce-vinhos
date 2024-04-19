package model.entity;

import java.util.Date;

public class Cupons {
	
	private Integer id;
	private Cliente cliente;
	private String codigo;
	private String desc;
	private String img;
	private String tipo;
	private Double valor;
	private Date validade;
	
	public Cupons() {
		super();
	}

	
	public Cupons(Integer id, Cliente cliente, String codigo, String desc, String img, String tipo, Double valor,
			Date validade) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.codigo = codigo;
		this.desc = desc;
		this.img = img;
		this.tipo = tipo;
		this.valor = valor;
		this.validade = validade;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}
	
	
	
}
