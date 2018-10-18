package fr.eni.ihm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.manager.CandidatManager;
import fr.eni.bll.manager.CollaborateurManager;
import fr.eni.bll.manager.ProfilManager;
import fr.eni.bll.manager.PromotionManager;
import fr.eni.bll.manager.factory.ManagerFactory;
import fr.eni.bo.Candidat;
import fr.eni.bo.Collaborateur;
import fr.eni.bo.Profil;
import fr.eni.bo.Promotion;
import fr.eni.bo.Utilisateur;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public class AjoutUtilisateurController extends HttpServlet {
	private CandidatManager candidatManager = ManagerFactory.candidatManager();
	private ProfilManager profilManager = ManagerFactory.ProfilManager();
	private PromotionManager promotionManager = ManagerFactory.PromotionManager();

	private static final long serialVersionUID = -6970893575378675464L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Profil> profils = null;
		List<Promotion> promotions = null;
		List<Utilisateur> utilisateur = null;
		try {
			utilisateur = candidatManager.selectAll();
			promotions = promotionManager.selectAll();
			profils = profilManager.selectAll();

			req.setAttribute("utilisateur", utilisateur);
			req.setAttribute("promotion", promotions);
			req.setAttribute("profil", profils);

			req.getRequestDispatcher("AjoutCandidat").forward(req, resp);
		} catch (ManagerException e) {
			// TODO Auto-generated catch block
			throw new ServletException("erreur get ajout", e);
		}

	}

	/* AJOUT UTILISATEUR/CANDIDAT/COLLABO */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Profil profil = null;
		Promotion promotion = null;
		Candidat candidat = null;
		List<Profil> profils = null;
		List<Promotion> promotions = null;
		List<Utilisateur> utilisateur = null;
		try {
			promotions = promotionManager.selectAll();
			profils = profilManager.selectAll();
			
			req.setAttribute("profil", profils);
			req.setAttribute("promotion", promotions);
			candidat = new Candidat();
			candidat = candidatManager.selectByEmail(req.getParameter("Email"));
			profil = profilManager.selectByName(req.getParameter("profilbox"));

			if (candidat == null) {
				candidat = new Candidat();
				candidat.setEmail(req.getParameter("Email"));
				candidat.setNom(req.getParameter("Nom"));
				candidat.setPrenom(req.getParameter("Prenom"));
				candidat.setPassword(req.getParameter("mdp"));
				candidat.setProfil(profil.getId());
				candidat = candidatManager.insertUtilisateur(candidat);
				
				if (profil.getLibelle().equals("Candidat")) {
					promotion = promotionManager.selectByName(req.getParameter("promotionbox"));
					candidatManager.insertCandidat(candidat, promotion.getId());
				} else {
					candidatManager.insertCollaborateur(candidat);
				}
				utilisateur = candidatManager.selectAll();
				req.setAttribute("utilisateur", utilisateur);
				req.setAttribute("validate", "Utilisateur bien créé");
				req.getRequestDispatcher("AjoutCandidat").forward(req, resp);
			} else {
				utilisateur = candidatManager.selectAll();
				req.setAttribute("utilisateur", utilisateur);
				req.setAttribute("error", "Email déjà utilisé");
				req.getRequestDispatcher("AjoutCandidat").forward(req, resp);

			}

		} catch (ManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
