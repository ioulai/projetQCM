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
import fr.eni.bo.QuestionTirage;
import fr.eni.bo.ReponseTirage;

public class FinEpreuveController extends HttpServlet{
	private static final long serialVersionUID = -6970893575378675464L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int idTest = Integer.parseInt(req.getParameter("idTest"));
			float note = 0;
			
			Epreuve epreuve = null;
			
			// Création des managers
			EpreuveManager em = ManagerFactory.epreuveManager();
			QuestionTirageManager qtm = ManagerFactory.questionTirageManager();
			ReponseTirageManager rtm = ManagerFactory.reponseTirageManager();
			
			// Recherche de l'épreuve du test
			epreuve = em.selectByIdTest(idTest);
			
			// Recherche des questionsTirage
			ArrayList<QuestionTirage> questionTirages = qtm.selectByIdEpreuve(epreuve.getIdEpreuve());
			
			// Calcul de la note /20
			for (QuestionTirage qt : questionTirages) {
				ReponseTirage reponseTirage = rtm.selectByAll(epreuve.getIdEpreuve(), qt.getQuestion().getId());
				
				if (reponseTirage.getProposition().isEstBonne()) {
					note += qt.getQuestion().getPoints();
				}
			}
			
			req.getRequestDispatcher("finEpreuve").forward(req, resp);
		} catch (Exception e) {
			resp.sendError(500);
		}
	}
}
