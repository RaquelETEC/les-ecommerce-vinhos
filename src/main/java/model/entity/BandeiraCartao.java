package model.entity;

public class BandeiraCartao {
	private int id;
	private String desc;
	private String img;

	public BandeiraCartao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BandeiraCartao(int id, String desc, String img) {
		super();
		this.id = id;
		this.desc = desc;
		this.img = img;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
