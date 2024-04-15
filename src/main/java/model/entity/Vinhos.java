package model.entity;

public class Vinhos {
	private int id;
	private Cliente cliente;
	private Harmonizacao harmo;
	private int valor;
	private String vinicola;
	private String pais;
	private String regiao;
	private String safra;
	private String desc;
	private String tipo;
	private String uva;
	private String altura;
	private String largura;
	private String peso;
	private String profundidade;
	private String grupo;
	private String img;
	private String status;

	public Vinhos() {
		// TODO Auto-generated constructor stub
		super();
	}
	

	public Vinhos(int id, Cliente cliente, Harmonizacao harmo, int valor, String vinicola, String pais, String regiao,
			String safra, String desc, String tipo, String uva, String altura, String largura, String peso,
			String profundidade, String grupo, String img, String status) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.harmo = harmo;
		this.valor = valor;
		this.vinicola = vinicola;
		this.pais = pais;
		this.regiao = regiao;
		this.safra = safra;
		this.desc = desc;
		this.tipo = tipo;
		this.uva = uva;
		this.altura = altura;
		this.largura = largura;
		this.peso = peso;
		this.profundidade = profundidade;
		this.grupo = grupo;
		this.img = img;
		this.status = status;
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

	public Harmonizacao getHarmo() {
		return harmo;
	}

	public void setHarmo(Harmonizacao harmo) {
		this.harmo = harmo;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getVinicola() {
		return vinicola;
	}

	public void setVinicola(String vinicola) {
		this.vinicola = vinicola;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public String getSafra() {
		return safra;
	}

	public void setSafra(String safra) {
		this.safra = safra;
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

	public String getUva() {
		return uva;
	}

	public void setUva(String uva) {
		this.uva = uva;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getLargura() {
		return largura;
	}

	public void setLargura(String largura) {
		this.largura = largura;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(String profundidade) {
		this.profundidade = profundidade;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
