package fr.eni.bo;

public class ThemeResultat {
	private Theme theme;
	private String TauxReussite;
	
	public ThemeResultat(Theme theme, String tauxReussite) {
		super();
		this.theme = theme;
		TauxReussite = tauxReussite;
	}
	public Theme getTheme() {
		return theme;
	}
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	public String getTauxReussite() {
		return TauxReussite;
	}
	public void setTauxReussite(String tauxReussite) {
		TauxReussite = tauxReussite;
	}
}
