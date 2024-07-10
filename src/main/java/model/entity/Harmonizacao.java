package model.entity;

public class Harmonizacao {
	private int id;
	private String desc;
	private String tipo;

	public Harmonizacao() {
		// TODO Auto-generated constructor stub
		super();

	}

	public Harmonizacao(int id, String desc, String tipo) {
		super();
		this.id = id;
		this.desc = desc;
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
