package model.entity;

public class Categoria {

	private int id;
	private String desc;
	private String status;

	public Categoria() {
		// TODO Auto-generated constructor stub
		super();
	}

	public Categoria(int id, String desc, String status) {
		super();
		this.id = id;
		this.desc = desc;
		this.status = status;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
