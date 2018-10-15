package fr.eni.ihm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.manager.CandidatManager;
import fr.eni.bll.manager.factory.ManagerFactory;
import fr.eni.bo.Candidat;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public class SupprUtilisateurController extends HttpServlet{
	private CandidatManager candidatManager = ManagerFactory.candidatManager();
	
	private static final long serialVersionUID = -6970893575375675464L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			List<Candidat> candidats = new ArrayList<>();
			try {
				candidats = candidatManager.selectAllCollaborateur();
				req.setAttribute("candidat", candidats);	
			} catch (ManagerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			req.getRequestDispatcher("SupprimerUtilisateur").forward(req, resp);
	}

	/* AJOUT PROMOTION */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Candidat> candidats = new ArrayList<>();
		
		try {
			int idCandidat = Integer.parseInt(req.getParameter("candidatbox"));
			candidatManager.deleteCollaborateur(idCandidat);
			candidats = candidatManager.selectAllCollaborateur();
			req.setAttribute("candidat", candidats);
			
			req.getRequestDispatcher("SupprimerUtilisateur").forward(req, resp);
		} catch (ManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
