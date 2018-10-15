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
		Candidat candidat = null;
		String messageErreur = "";
		Cookie cookieUtilisateurCourant = null;
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Object candidatConnecter = null;

		ValidationUtil.checkNotBlank(email);
		ValidationUtil.checkNotBlank(password);

		try {
			candidat = candidatManager.selectByEmailPassword(email, password);

			/** ici verif avec la bdd **/
			if (candidat != null) {
				//mise en session
				HttpSession session = req.getSession(true);
				session.setAttribute("candidatConnecter", candidat);
				// session expire dans 30min
				session.setMaxInactiveInterval(30 * 60);
				//creation cookie utilisateur courant
				cookieUtilisateurCourant = new Cookie("candidatConnecter", candidat.getNom());
				//cookie expire dans 30min
				cookieUtilisateurCourant.setMaxAge(30*60);
				resp.addCookie(cookieUtilisateurCourant);				
				// recuperation du candidat connecter
				candidatConnecter = session.getAttribute("candidatConnecter");
				req.setAttribute("candidatConnecter", candidatConnecter);

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
