package fr.eni.ihm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.manager.CandidatManager;
import fr.eni.bll.manager.CollaborateurManager;
import fr.eni.bll.manager.factory.ManagerFactory;
import fr.eni.bo.Collaborateur;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public class SupprUtilisateurController extends HttpServlet{
	private CandidatManager candidatManager = ManagerFactory.candidatManager();
	private CollaborateurManager collaborateurManager = ManagerFactory.CollaborateurManager();
	
	private static final long serialVersionUID = -6970893575375675464L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			List<Collaborateur> collaborateurs = new ArrayList<>();
			try {
				collaborateurs = collaborateurManager.selectAllCollaborateur();
				req.setAttribute("collaborateurs", collaborateurs);	
			} catch (ManagerException e) {
				e.printStackTrace();
			}
			
			req.getRequestDispatcher("SupprimerUtilisateur").forward(req, resp);
	}

	/* AJOUT PROMOTION */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Collaborateur> collaborateurs = new ArrayList<>();
		
		try {
			int idCandidat = Integer.parseInt(req.getParameter("candidatbox"));
			candidatManager.deleteCollaborateur(idCandidat);
			collaborateurs = collaborateurManager.selectAllCollaborateur();
			req.setAttribute("collaborateurs", collaborateurs);	
			
			req.getRequestDispatcher("SupprimerUtilisateur").forward(req, resp);
		} catch (ManagerException e) {
			e.printStackTrace();
		}
		
	}

}
