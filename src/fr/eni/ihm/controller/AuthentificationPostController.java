package fr.eni.ihm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.bll.manager.CandidatManager;
import fr.eni.bll.manager.factory.ManagerFactory;
import fr.eni.bo.Candidat;
import fr.eni.bo.Utilisateur;
import fr.eni.tp.web.common.util.ValidationUtil;

public class AuthentificationPostController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CandidatManager candidatManager = ManagerFactory.candidatManager();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Utilisateur utilisateur = null;
		String messageErreur = "";
		Cookie cookieUtilisateurCourant = null;
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Object utilisateurConnecte = null;

		ValidationUtil.checkNotBlank(email);
		ValidationUtil.checkNotBlank(password);

		try {
			utilisateur = candidatManager.selectByEmailPassword(email, password);

			/** ici verif avec la bdd **/
			if (utilisateur != null) {
				//mise en session
				HttpSession session = req.getSession(true);
				session.setAttribute("utilisateurConnecte", utilisateur);
				// session expire dans 30min
				session.setMaxInactiveInterval(30 * 60);
				//creation cookie utilisateur courant
				cookieUtilisateurCourant = new Cookie("utilisateurConnecte", utilisateur.getNom());
				//cookie expire dans 30min
				cookieUtilisateurCourant.setMaxAge(30*60);
				resp.addCookie(cookieUtilisateurCourant);				
				// recuperation du candidat connecter
				utilisateurConnecte = session.getAttribute("utilisateurConnecte");
				req.setAttribute("utilisateurConnecte", utilisateurConnecte);

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
