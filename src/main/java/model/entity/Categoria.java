package model.entity;

public class Categoria {
	
	private int id;
	private String desc;
	private int status;
	
	public Categoria() {
		// TODO Auto-generated constructor stub
		super();
	}

	public Categoria(int id, String desc, int status) {
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
