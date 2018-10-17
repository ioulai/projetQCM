package fr.eni.bo;

public class QuestionResultat {
	private int id;
	private boolean isMarquee;
	private boolean isResolue;

	public QuestionResultat(int id, boolean isMarquee, boolean isResolue) {
		super();
		this.id = id;
		this.isMarquee = isMarquee;
		this.isResolue = isResolue;
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
