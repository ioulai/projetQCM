package fr.eni.bo;

public class Proposition {
	private int id;
	private String enonce;
	private boolean estBonne;
	private Question question;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEnonce() {
		return enonce;
	}
	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}
	public boolean isEstBonne() {
		return estBonne;
	}
	public void setEstBonne(boolean estBonne) {
		this.estBonne = estBonne;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
}
