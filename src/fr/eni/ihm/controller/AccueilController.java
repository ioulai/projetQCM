package fr.eni.ihm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.bll.manager.CandidatManager;
import fr.eni.bll.manager.CollaborateurManager;
import fr.eni.bll.manager.ProfilManager;
import fr.eni.bll.manager.factory.ManagerFactory;
import fr.eni.bo.Candidat;
import fr.eni.bo.Collaborateur;

public class AccueilController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public AccueilController() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Object utilisateurConnecte = null;
//		Object profilUtilisateur = null;
	
		// recup de la session
		HttpSession session = req.getSession();
		// recup utilisateur connecte
		Object utilisateurConnecte = session.getAttribute("utilisateurConnecte");
		Object profilUtilisateur = session.getAttribute("profilUtilisateur");
		req.setAttribute("utilisateurConnecte", utilisateurConnecte);
		req.setAttribute("profil", profilUtilisateur);
		//System.out.println("home");
		req.getRequestDispatcher("accueil").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
