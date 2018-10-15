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
import fr.eni.bll.manager.TestManager;
import fr.eni.bll.manager.factory.ManagerFactory;
import fr.eni.bo.Epreuve;
import fr.eni.bo.QuestionTirage;
import fr.eni.bo.ReponseTirage;
import fr.eni.bo.Test;

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
			TestManager tm = ManagerFactory.TestManager();
			
			// Recherche de l'épreuve du test
			epreuve = em.selectByIdTest(idTest);
			
			// Recherche des questionsTirage
			ArrayList<QuestionTirage> questionTirages = qtm.selectByIdEpreuve(epreuve.getIdEpreuve());
			
			// Calcul de la note /20
			for (QuestionTirage qt : questionTirages) {
				boolean isValid = true;
				
				ArrayList<ReponseTirage> reponseTirages = rtm.selectByAll(epreuve.getIdEpreuve(), qt.getQuestion().getId());
				
				for (ReponseTirage reponseTirage : reponseTirages) {
					if (!reponseTirage.getProposition().isEstBonne()) {
						isValid = false;
						break;
					}
				}
				
				if (isValid) {
					note += qt.getQuestion().getPoints();
				}
			}
			
			// Selection du test
			Test test = tm.selectById(idTest);
			
			// Modif de la note
			epreuve.setNoteObtenue(note);
			
			// Modif du niveau obtenu
			String niveau;
			
			if (note < test.getSeuilBas()) {
				epreuve.setNiveauObtenu("NA");
				niveau = "Non acquis";
			}
			else if (note >= test.getSeuilBas() && note < test.getSeuilHaut()) {
				epreuve.setNiveauObtenu("ECA");
				niveau = "En cours d'acquisition";
			}
			else {
				epreuve.setNiveauObtenu("A");
				niveau = "Acquis";
			}
			
			// Modif des resultats
			em.update(epreuve);
			
			// On vide les tables tampons de tirage
			rtm.deleteAll();
			qtm.deleteAll();
			
			req.setAttribute("niveau", niveau);
			req.setAttribute("note", epreuve.getNoteObtenue());
			req.getRequestDispatcher("finEpreuve").forward(req, resp);
		} catch (Exception e) {
			resp.sendError(500);
		}
	}
}
