package fr.eni.bo;

import org.apache.tomcat.jni.Time;

public class Test {
	private int id;
	private String libelle;
	private String description;
	private Time duree;
	private String seuilHaut;
	private String seuilBas;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Time getDuree() {
		return duree;
	}
	public void setDuree(Time duree) {
		this.duree = duree;
	}
	public String getSeuilHaut() {
		return seuilHaut;
	}
	public void setSeuilHaut(String seuilHaut) {
		this.seuilHaut = seuilHaut;
	}
	public String getSeuilBas() {
		return seuilBas;
	}
	public void setSeuilBas(String seuilBas) {
		this.seuilBas = seuilBas;
	}
}
