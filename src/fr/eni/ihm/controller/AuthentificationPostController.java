package fr.eni.ihm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
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

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Candidat candidat = null;
		String messageErreur = "";
		String showMessage = "hidden";
		
		try {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			Object candidatConnecter= null;
			
        candidat = candidatManager.selectByEmailPassword(email, password);

			ValidationUtil.checkNotBlank(email);
			ValidationUtil.checkNotBlank(password);
			
			/** ici verif avec la bdd **/
			if (candidat != null) {
				 HttpSession session = req.getSession(true); 
				 session.setAttribute("candidatConnecter",candidat);
				 //recuperation du candidat connecter
				 candidatConnecter = session.getAttribute("candidatConnecter");
				 req.setAttribute("candidatConnecter", candidatConnecter);
				 
				req.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(req, resp);
			}else{				
				messageErreur = "<div class='alert alert-danger' role='alert'>Candidat Inconnu <br> Email ou Mot de passe incorrect</div>";
				req.setAttribute("messageErreur",messageErreur);
				req.getRequestDispatcher("authentification").forward(req, resp);
				
				System.out.println("candidat null : "+candidat);
				System.out.println(" non connecter ");
			}
			
		} catch (Exception e) {
			
		}
	}
	
	

}
