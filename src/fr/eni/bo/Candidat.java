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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Candidat [codepromo=");
		builder.append(codepromo);
		builder.append(", ");
		if (super.toString() != null) {
			builder.append("toString()=");
			builder.append(super.toString());
		}
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
