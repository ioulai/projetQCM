package fr.eni.ihm.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.manager.CandidatManager;
import fr.eni.bll.manager.EpreuveManager;
import fr.eni.bll.manager.TestManager;
import fr.eni.bll.manager.factory.ManagerFactory;
import fr.eni.bo.Candidat;
import fr.eni.bo.Epreuve;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public class DesinscriptionCandidatController extends HttpServlet{
	private  TestManager testManager = ManagerFactory.TestManager();
	private CandidatManager candidatManager = ManagerFactory.candidatManager();
	private EpreuveManager epreuveManager = ManagerFactory.epreuveManager();
	
	private static final long serialVersionUID = -6970893575378675464L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			List<Candidat> candidats = new ArrayList<>();
			try {
				candidats = candidatManager.selectAllCandidat();
				req.setAttribute("candidat", candidats);	
			} catch (ManagerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			req.getRequestDispatcher("DesinscriptionCandidat").forward(req, resp);
	}

	/* AJOUT PROMOTION */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Epreuve> epr = new ArrayList<>();
		List<Candidat> candidats = new ArrayList<>();
		try {
			candidats = candidatManager.selectAllCandidat();
			req.setAttribute("candidat", candidats);
			
			if(req.getParameter("testbox") == null)
			{
				int idCandidat = Integer.parseInt(req.getParameter("candidatbox"));
				epr = new ArrayList<>();
				//selection des tests en fonctions de l' utilisateurs
				epr = epreuveManager.selectByUserId(idCandidat);
				req.setAttribute("epreuve", epr);
				req.setAttribute("selectionid", idCandidat);
				req.getRequestDispatcher("DesinscriptionCandidat").forward(req, resp);
			}else{
				//suppresion de l'inscription
				int idTest = Integer.parseInt(req.getParameter("testbox"));
				//testManager.s
				
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
