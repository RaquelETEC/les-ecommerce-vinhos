package model;

public class JavaBeansRaquel {
	/**
	 *Variaveis encapsuladas para serem usadas 
	 *esta seria a classe 'CLIENTE'
	
	 quando uma variavel esta como private, o conteudo dela 
	  so pode ser acessado pela própria classe*/
	
	private String idcon;
	private String nome;
	private String fone;
	private String email;

	/**Construtores são usados para iniciar automaticamente o objeto criado 'JavaBeans' ou 'CLIENTE' 
	 * vamos usar construtores para acessar os dados do banco de dados **/
	public JavaBeansRaquel() {
		super();
		
	}
	
	public JavaBeansRaquel(String idcon, String nome, String fone, String email) {
		super();
		this.idcon = idcon;
		this.nome = nome;
		this.fone = fone;
		this.email = email;
	}

	/**quando fazemos um encapsulamento, a unica forma de acessar o valor das variaveis 
	  é atraves de metodos publicos os famosos GET e SET: **/

	public String getIdcon() {
		return idcon;
	}

	public void setIdcon(String idcon) {
		this.idcon = idcon;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) { }
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
