package model.entity;

import java.sql.Blob;
import java.util.ArrayList;

public class Produtos {
	private int id;
	private Categoria categoria;
	private Precificacao precificacao;
	private Double pro_preco_venda;
	private Double pro_preco_compra;
	private String justificativa;
	private String codigo_barra;
	private String vinicola;
	private String pais;
	private String regiao;
	private String safra;
	private String desc;
	private String tipo;
	private String uva;
	private String alcool;
	private String altura;
	private String largura;
	private String peso;
	private String profundidade;
	private Blob inf;
	private String img;

	private ArrayList<Harmonizacao> listadeHarmonizacao = new ArrayList<>();

	public Produtos() {
		// TODO Auto-generated constructor stub
		super();
	}

	public Produtos(int id, Categoria categoria, Precificacao precificacao, Double pro_preco_venda,
			Double pro_preco_compra, String justificativa, String codigo_barra, String vinicola, String pais,
			String regiao, String safra, String desc, String tipo, String uva, String alcool, String altura,
			String largura, String peso, String profundidade, Blob inf, String img,
			ArrayList<Harmonizacao> listadeHarmonizacao) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.precificacao = precificacao;
		this.pro_preco_venda = pro_preco_venda;
		this.pro_preco_compra = pro_preco_compra;
		this.justificativa = justificativa;
		this.codigo_barra = codigo_barra;
		this.vinicola = vinicola;
		this.pais = pais;
		this.regiao = regiao;
		this.safra = safra;
		this.desc = desc;
		this.tipo = tipo;
		this.uva = uva;
		this.alcool = alcool;
		this.altura = altura;
		this.largura = largura;
		this.peso = peso;
		this.profundidade = profundidade;
		this.inf = inf;
		this.img = img;
		this.listadeHarmonizacao = listadeHarmonizacao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Precificacao getPrecificacao() {
		return precificacao;
	}

	public void setPrecificacao(Precificacao precificacao) {
		this.precificacao = precificacao;
	}

	public ArrayList<Harmonizacao> getListadeHarmonizacao() {
		return listadeHarmonizacao;
	}

	public void setListadeHarmonizacao(ArrayList<Harmonizacao> listadeHarmonizacao) {
		this.listadeHarmonizacao = listadeHarmonizacao;
	}

	public Produtos(int id, Double pro_preco_venda, Double pro_preco_compra, String justificativa, String codigo_barra,
			String vinicola, String pais, String regiao, String safra, String desc, String tipo, String uva,
			String alcool, String altura, String largura, String peso, String profundidade, Blob inf, String img) {
		super();
		this.id = id;
		this.pro_preco_venda = pro_preco_venda;
		this.pro_preco_compra = pro_preco_compra;
		this.justificativa = justificativa;
		this.codigo_barra = codigo_barra;
		this.vinicola = vinicola;
		this.pais = pais;
		this.regiao = regiao;
		this.safra = safra;
		this.desc = desc;
		this.tipo = tipo;
		this.uva = uva;
		this.alcool = alcool;
		this.altura = altura;
		this.largura = largura;
		this.peso = peso;
		this.profundidade = profundidade;
		this.inf = inf;
		this.img = img;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getPro_preco_venda() {
		return pro_preco_venda;
	}

	public void setPro_preco_venda(Double pro_preco_venda) {
		this.pro_preco_venda = pro_preco_venda;
	}

	public Double getPro_preco_compra() {
		return pro_preco_compra;
	}

	public void setPro_preco_compra(Double pro_preco_compra) {
		this.pro_preco_compra = pro_preco_compra;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public String getCodigo_barra() {
		return codigo_barra;
	}

	public void setCodigo_barra(String codigo_barra) {
		this.codigo_barra = codigo_barra;
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

	public String getAlcool() {
		return alcool;
	}

	public void setAlcool(String alcool) {
		this.alcool = alcool;
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

	public Blob getInf() {
		return inf;
	}

	public void setInf(Blob inf) {
		this.inf = inf;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
