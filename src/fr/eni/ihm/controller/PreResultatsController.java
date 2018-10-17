package fr.eni.ihm.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.manager.EpreuveManager;
import fr.eni.bll.manager.QuestionTirageManager;
import fr.eni.bll.manager.ReponseTirageManager;
import fr.eni.bll.manager.factory.ManagerFactory;
import fr.eni.bo.Epreuve;
import fr.eni.bo.QuestionResultat;
import fr.eni.bo.QuestionTirage;
import fr.eni.bo.ReponseTirage;

public class PreResultatsController extends HttpServlet{
	private static final long serialVersionUID = -6970893575378675464L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.getRequestDispatcher("preResultats").forward(req, resp);
		} catch (Exception e) {
			resp.sendError(500);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int idTest = Integer.parseInt(req.getParameter("idTest"));
			ArrayList<QuestionResultat> listeQuestions = new ArrayList<QuestionResultat>();

			// On cherche l'épreuve du test
			EpreuveManager em = ManagerFactory.epreuveManager();
			Epreuve epreuve = em.selectByIdTest(idTest);
			
			//Timer
			int sec = epreuve.getTest().getDuree().getSeconds() +  epreuve.getTest().getDuree().getHours()*3600 + epreuve.getTest().getDuree().getMinutes()*60;
			sec = sec - (epreuve.getTempsEcoule().getSeconds() +  epreuve.getTempsEcoule().getHours()*3600 + epreuve.getTempsEcoule().getMinutes()*60);
			
			// Recherche des questionTirages
			QuestionTirageManager qtm = ManagerFactory.questionTirageManager();
			ArrayList<QuestionTirage> questionTirages = qtm.selectByIdEpreuve(epreuve.getIdEpreuve());
			
			ReponseTirageManager rtm = ManagerFactory.reponseTirageManager();
			
			boolean isResolue = false;
			
			// On cherche les questions
			for (QuestionTirage q : questionTirages) {
				ArrayList<ReponseTirage> lrt = rtm.selectByAll(epreuve.getIdEpreuve(), q.getQuestion().getId());
				isResolue = false;
				
				if (lrt != null && lrt.size() > 0) {
					isResolue = true;
				}
				
				listeQuestions.add(new QuestionResultat(q.getQuestion().getId(), q.isEstMarquee(), isResolue));
			}
			
			// Attributs à envoyer
			req.setAttribute("questions", listeQuestions);
			req.setAttribute("idTest", idTest);
			req.setAttribute("isModifiable", true);
			req.setAttribute("duree", sec);
			req.setAttribute("idEpreuve", epreuve.getIdEpreuve());
			
			req.getRequestDispatcher("preResultats").forward(req, resp);
		} catch (Exception e) {
			resp.sendError(500);
		}
	}
}
