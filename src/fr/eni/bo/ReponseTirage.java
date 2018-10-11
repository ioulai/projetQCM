package fr.eni.bo;

public class ReponseTirage {
	private Epreuve epreuve;
	private Proposition proposition;
	private Question question;
	
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Proposition getProposition() {
		return proposition;
	}
	public void setProposition(Proposition proposition) {
		this.proposition = proposition;
	}
	public Epreuve getEpreuve() {
		return epreuve;
	}
	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}
}
