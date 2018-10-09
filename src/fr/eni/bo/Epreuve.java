package fr.eni.bo;

import java.sql.Time;
import java.util.Date;


public class Epreuve{
	private int idEpreuve;
	private Date dateDebutValidite;
	private Date dateFinValidite;
	private Time tempsEcoule;
	private String etat;
	private int noteObtenue;
	private String niveauObtenu;
	private Candidat candidat;
	private Test test;

	public Candidat getCandidat() {
		return candidat;
	}

	public void setCandidat(Candidat candidat) {
		this.candidat = candidat;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Epreuve() {
		super();
	}
	
	public int getIdEpreuve() {
		return idEpreuve;
	}
	
	public void setIdEpreuve(int idEpreuve) {
		this.idEpreuve = idEpreuve;
	}
	
	public Date getDateDebutValidite() {
		return dateDebutValidite;
	}
	
	public void setDateDebutValidite(Date dateDebutValidite) {
		this.dateDebutValidite = dateDebutValidite;
	}
	
	public Date getDateFinValidite() {
		return dateFinValidite;
	}
	
	public void setDateFinValidite(Date dateFinValidite) {
		this.dateFinValidite = dateFinValidite;
	}
	
	public Time getTempsEcoule() {
		return tempsEcoule;
	}
	
	public void setTempsEcoule(Time tempsEcoule) {
		this.tempsEcoule = tempsEcoule;
	}
	
	public String getEtat() {
		return etat;
	}
	
	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	public int getNoteObtenue() {
		return noteObtenue;
	}
	
	public void setNoteObtenue(int noteObtenue) {
		this.noteObtenue = noteObtenue;
	}
	
	public String getNiveauObtenu() {
		return niveauObtenu;
	}
	
	public void setNiveauObtenu(String niveauObtenu) {
		this.niveauObtenu = niveauObtenu;
	}
	
}
