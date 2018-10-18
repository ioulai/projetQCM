package fr.eni.ihm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
import fr.eni.bo.Profil;
import fr.eni.bo.Utilisateur;
import fr.eni.tp.web.common.util.ValidationUtil;

public class AuthentificationPostController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CandidatManager candidatManager = ManagerFactory.candidatManager();
	private CollaborateurManager collaborateurManager = ManagerFactory.CollaborateurManager();
	private ProfilManager profilManager = ManagerFactory.ProfilManager();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Candidat candidat = null;
		Collaborateur collaborateur = null;
		String messageErreur = "";
		Cookie cookieUtilisateurCourant = null;
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Object utilisateurConnecte = null;
		Object profilUtilisateur = null;
		Profil profil = null;
		HttpSession session = req.getSession(true);
		ValidationUtil.checkNotBlank(email);
		ValidationUtil.checkNotBlank(password);
//		System.out.println("tentative connexion");
		try {

			collaborateur = collaborateurManager.selectByEmailPassword(email, password);
			candidat = candidatManager.selectByEmailPassword(email, password);

			/** ici verif avec la bdd **/
			if (collaborateur != null) {
				profil = profilManager.selectById(collaborateur.getProfil());
				//System.out.println("collaborateur [" + collaborateur + "], profil [" + profil.getLibelle() + "]");
				// mise en session
				session.setAttribute("utilisateurConnecte", collaborateur);
				session.setAttribute("profilUtilisateur", profil.getLibelle());
				// session expire dans 30min
				session.setMaxInactiveInterval(30 * 60);
				// creation cookie utilisateur courant
				cookieUtilisateurCourant = new Cookie("utilisateurConnecte", collaborateur.getNom());
				// cookie expire dans 30min
				cookieUtilisateurCourant.setMaxAge(30 * 60);
				resp.addCookie(cookieUtilisateurCourant);
				// recuperation du collaborateur connecter
				utilisateurConnecte = session.getAttribute("utilisateurConnecte");
				profilUtilisateur = session.getAttribute("profilUtilisateur");
				req.setAttribute("utilisateurConnecte", utilisateurConnecte);
				req.setAttribute("profil", profilUtilisateur);

				req.getRequestDispatcher("accueil").forward(req, resp);
			} else if (candidat != null) {
				profil = profilManager.selectById(candidat.getProfil());
				//System.out.println("candidat [" + candidat + "], profil [" + profil.getLibelle() + "]");

				// mise en session				
				session.setAttribute("utilisateurConnecte", candidat);
				session.setAttribute("profilUtilisateur", profil.getLibelle());
				// session expire dans 30min
				session.setMaxInactiveInterval(30 * 60);
				// creation cookie utilisateur courant
				cookieUtilisateurCourant = new Cookie("utilisateurConnecte", candidat.getNom());
				// cookie expire dans 30min
				cookieUtilisateurCourant.setMaxAge(30 * 60);
				resp.addCookie(cookieUtilisateurCourant);
				// recuperation du candidat connecter
				utilisateurConnecte = session.getAttribute("utilisateurConnecte");
				profilUtilisateur = session.getAttribute("profilUtilisateur");
				req.setAttribute("utilisateurConnecte", utilisateurConnecte);
				req.setAttribute("profil", profilUtilisateur);

				req.getRequestDispatcher("accueil").forward(req, resp);
			} else {
				messageErreur = "<div class='alert alert-danger' role='alert'>Candidat Inconnu <br> Email ou Mot de passe incorrect</div>";
				req.setAttribute("messageErreur", messageErreur);
				req.getRequestDispatcher("authentification").forward(req, resp);

			}

		} catch (Exception e) {

		}
	}

}
