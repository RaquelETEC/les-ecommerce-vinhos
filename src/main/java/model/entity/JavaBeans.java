package model.entity;

// TODO: Auto-generated Javadoc
/**
 * The Class JavaBeans.
 */
public class JavaBeans {
	
	/** The idcon. */
	private int idcon;
	
	/** The nome. */
	private String nome;
	
	/** The fone. */
	private String fone;
	
	/** The email. */
	private String email;

	/**
	 * Instantiates a new java beans.
	 */
	public JavaBeans() {
		super();
	}

	/**
	 * Instantiates a new java beans.
	 *
	 * @param id the idcon
	 * @param nome the nome
	 * @param fone the fone
	 * @param email the email
	 */
	public JavaBeans(int id, String nome, String fone, String email) {
		super();	
		this.nome = nome;
		this.fone = fone;
		this.email = email;
	}

	/**
	 * Gets the idcon.
	 *
	 * @return the idcon
	 */
	public int getIdcon() {
		return idcon;
	}

	/**
	 * Sets the idcon.
	 *
	 * @param i the new idcon
	 */
	public void setIdcon(int i) {
		this.idcon = i;
	}

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the fone.
	 *
	 * @return the fone
	 */
	public String getFone() {
		return fone;
	}

	/**
	 * Sets the fone.
	 *
	 * @param fone the new fone
	 */
	public void setFone(String fone) {
		this.fone = fone;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public void setIdcon(String parameter) {
		// TODO Auto-generated method stub
		
	}

}