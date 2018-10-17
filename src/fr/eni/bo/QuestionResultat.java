package fr.eni.bo;

public class QuestionResultat {
	private int id;
	private boolean isMarquee;
	private boolean isResolue;
	private Question question;

	public QuestionResultat(Question question, boolean isMarquee, boolean isResolue) {
		super();
		this.question = question;
		this.isMarquee = isMarquee;
		this.isResolue = isResolue;
	}
	
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isMarquee() {
		return isMarquee;
	}
	public void setMarquee(boolean isMarquee) {
		this.isMarquee = isMarquee;
	}
	public boolean isResolue() {
		return isResolue;
	}
	public void setResolue(boolean isResolue) {
		this.isResolue = isResolue;
	}
}
