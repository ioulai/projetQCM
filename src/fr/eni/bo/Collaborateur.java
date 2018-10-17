package fr.eni.bo;

public class Collaborateur extends Utilisateur {
	/**
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param password
	 * @param profil
	 * @param idUtilisateur
	 */
	public Collaborateur(int id, String nom, String prenom, String email, String password, int profil,
			int idUtilisateur) {
		super(id, nom, prenom, email, password, profil);
		
	}

	public Collaborateur() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Collaborateur [getProfil()=").append(getProfil()).append(", getId()=").append(getId())
				.append(", getNom()=").append(getNom()).append(", getPrenom()=").append(getPrenom())
				.append(", getEmail()=").append(getEmail()).append(", getPassword()=").append(getPassword())
				.append("]");
		return builder.toString();
	}


	

}
