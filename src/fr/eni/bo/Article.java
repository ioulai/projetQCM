package fr.eni.bo;

public class Article {
    private int id;
    private String nom ;
	private boolean selected;
	private ListeArticle listeArticle;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public ListeArticle getListeArticle() {
		return listeArticle;
	}
	public void setListeArticle(ListeArticle listeArticle) {
		this.listeArticle = listeArticle;
	}
}
