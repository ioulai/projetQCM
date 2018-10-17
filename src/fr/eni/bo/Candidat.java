package fr.eni.bo;

public class Candidat extends Utilisateur{
	

	private int codepromo;



	/**
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param password
	 * @param profil
	 * @param codepromo
	 */
	public Candidat(int id, String nom, String prenom, String email, String password, int profil, int codepromo) {
		super(id, nom, prenom, email, password, profil);
		this.codepromo = codepromo;
	}

	public Candidat() {
		// TODO Auto-generated constructor stub
	}

	public int getCodepromo() {
		return codepromo;
	}

	public void setCodepromo(int codepromo) {
		this.codepromo = codepromo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Candidat [codepromo=").append(codepromo).append(", getCodepromo()=").append(getCodepromo())
				.append(", getProfil()=").append(getProfil()).append(", getId()=").append(getId()).append(", getNom()=")
				.append(getNom()).append(", getPrenom()=").append(getPrenom()).append(", getEmail()=")
				.append(getEmail()).append(", getPassword()=").append(getPassword()).append("]");
		return builder.toString();
	}





	
	
	
	
}
