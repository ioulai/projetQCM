package fr.eni.ihm.controller;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import fr.eni.bo.Test;
import fr.eni.tp.web.common.bll.exception.ManagerException;




public class InscriptionTestController extends HttpServlet{
	private  TestManager testManager = ManagerFactory.TestManager();
	private CandidatManager candidatManager = ManagerFactory.candidatManager();
	private EpreuveManager epreuveManager = ManagerFactory.epreuveManager();
	
	private static final long serialVersionUID = -6970893575378675464L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			List<Test> tests = null;
			List<Candidat> candidats = null;
			
			try {
				tests = testManager.selectAll();
				candidats = candidatManager.selectAllCandidat();
				req.setAttribute("candidat", candidats);
				req.setAttribute("test",tests);	
			} catch (ManagerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			req.getRequestDispatcher("InscriptionCandidat").forward(req, resp);
	}

	/* AJOUT PROMOTION */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Test> tests = null;
		List<Candidat> candidats = null;
		List<Epreuve> epreuves = null;
		Date debutdate = null;
		Date findate = null;
		final Date date = new Date();
	    boolean inscription = true;
	    
		try {
			tests = testManager.selectAll();
			candidats = candidatManager.selectAllCandidat();
			req.setAttribute("candidat", candidats);
			req.setAttribute("test",tests);	
			
			int idcandida = Integer.parseInt(req.getParameter("candidatbox"));
			int idtest = Integer.parseInt(req.getParameter("testbox"));

			String debut = req.getParameter("debutdate");
			String fin = req.getParameter("findate");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
				 debutdate = sdf.parse(debut);
				 findate = sdf.parse(fin);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(findate.after(debutdate)){
				epreuves = epreuveManager.selectByIdCandidatTest(idcandida, idtest);
				
				for(Epreuve epr : epreuves)
				{
					if(epr.getEtat().equals("EA") && date.before(epr.getDateFinValidite()))
					{
						inscription = false;
					}
				}
				
				if(inscription == true || epreuves.isEmpty())
				{
					epreuveManager.insert(idcandida, idtest, debutdate, findate);
					req.getRequestDispatcher("InscriptionCandidat").forward(req, resp);
				}else
				{
					req.setAttribute("error","Utilisateur déjà inscrit");
					req.getRequestDispatcher("InscriptionCandidat").forward(req, resp);
				}
			}else{
				req.setAttribute("error","Selectionner une date cohérente");
			}
		} catch (ManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
