package fr.eni.bo;

public class QuestionTirage {
	private boolean estMarquee;
	private int numOrdre;
	private Epreuve epreuve;
	private Question question;
	
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public boolean isEstMarquee() {
		return estMarquee;
	}
	public void setEstMarquee(boolean estMarquee) {
		this.estMarquee = estMarquee;
	}
	public int getNumOrdre() {
		return numOrdre;
	}
	public void setNumOrdre(int numOrdre) {
		this.numOrdre = numOrdre;
	}
	public Epreuve getEpreuve() {
		return epreuve;
	}
	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}
}
