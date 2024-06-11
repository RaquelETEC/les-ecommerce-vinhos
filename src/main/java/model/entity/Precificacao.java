package model.entity;

public class Precificacao {

	private int id;
	private String desc;
	private Double margem;

	public Precificacao() {
		// TODO Auto-generated constructor stub
		super();
	}

	public Precificacao(int id, String desc, Double margem) {
		super();
		this.id = id;
		this.desc = desc;
		this.margem = margem;
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

	public Double getMargem() {
		return margem;
	}

	public void setMargem(Double margem) {
		this.margem = margem;
	}

}
