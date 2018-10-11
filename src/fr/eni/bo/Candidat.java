package fr.eni.bo;

public class Candidat extends Utilisateur{
	
	public Candidat() {
		super();
	}

	private int codepromo;

	public int getCodepromo() {
		return codepromo;
	}

	public void setCodepromo(int codepromo) {
		this.codepromo = codepromo;
	}
}
