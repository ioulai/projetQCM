/**
 * 
 */
package fr.eni.bo;

/**
 * @author ioulai2017
 *
 */
public abstract class Utilisateur{
	private int id;
	private String nom;
	private String prenom;
	private String email;
	private String password;
	private int profil;

	/**
	 * 
	 */
	public Utilisateur() {
		// TODO Auto-generated constructor stub
	}


	
	/**
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param password
	 * @param profil
	 */
	public Utilisateur(int id, String nom, String prenom, String email, String password, int profil) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.profil = profil;
	}



	public int getProfil() {
		return profil;
	}

	public void setProfil(int profil) {
		this.profil = profil;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Utilisateur [id=").append(id).append(", nom=").append(nom).append(", prenom=").append(prenom)
				.append(", email=").append(email).append(", password=").append(password).append(", profil=")
				.append(profil).append(", getProfil()=").append(getProfil()).append(", getId()=").append(getId())
				.append(", getNom()=").append(getNom()).append(", getPrenom()=").append(getPrenom())
				.append(", getEmail()=").append(getEmail()).append(", getPassword()=").append(getPassword())
				.append(", toString()=").append(super.toString()).append("]");
		return builder.toString();
	}



	
	
	

}
