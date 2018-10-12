package fr.eni.bo;

import java.sql.Time;

public class Test {
	private int id;
	private String libelle;
	private String description;
	private Time duree;
	private int seuilHaut;
	private int seuilBas;
	
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
	public int getSeuilHaut() {
		return seuilHaut;
	}
	public void setSeuilHaut(int seuilHaut) {
		this.seuilHaut = seuilHaut;
	}
	public int getSeuilBas() {
		return seuilBas;
	}
	public void setSeuilBas(int seuilBas) {
		this.seuilBas = seuilBas;
	}
}
