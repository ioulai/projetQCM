package fr.eni.ihm.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.bll.manager.EpreuveManager;
import fr.eni.bll.manager.PropositionManager;
import fr.eni.bll.manager.QuestionTirageManager;
import fr.eni.bll.manager.ReponseTirageManager;
import fr.eni.bll.manager.SectionTestManager;
import fr.eni.bll.manager.TestManager;
import fr.eni.bll.manager.ThemeManager;
import fr.eni.bll.manager.factory.ManagerFactory;
import fr.eni.bo.Candidat;
import fr.eni.bo.Epreuve;
import fr.eni.bo.Proposition;
import fr.eni.bo.QuestionTirage;
import fr.eni.bo.ReponseTirage;
import fr.eni.bo.Test;
import fr.eni.bo.Theme;
import fr.eni.bo.ThemeResultat;

public class FinEpreuveController extends HttpServlet{
	private static final long serialVersionUID = -6970893575378675464L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int idTest = Integer.parseInt(req.getParameter("idTest"));
			float note = 0;
			ThemeResultat themeresult = null;
			ArrayList<ThemeResultat> themereuslts = new ArrayList<>();
			Epreuve epreuve = null;
			
			// Création des managers
			EpreuveManager em = ManagerFactory.epreuveManager();
			QuestionTirageManager qtm = ManagerFactory.questionTirageManager();
			ReponseTirageManager rtm = ManagerFactory.reponseTirageManager();
			TestManager tm = ManagerFactory.TestManager();
			ThemeManager thm = ManagerFactory.themeManager();
			SectionTestManager stm = ManagerFactory.sectionTestManager();
			PropositionManager pm = ManagerFactory.propositionManager();
			
			// Recherche de l'épreuve du test
			HttpSession session = req.getSession();
			Candidat cand = (Candidat) session.getAttribute("utilisateurConnecte");
			epreuve = em.selectByIdTestIdCandidat(idTest, cand.getId());
			
			// Recherche des questionsTirage
			ArrayList<QuestionTirage> questionTirages = qtm.selectByIdEpreuve(epreuve.getIdEpreuve());
			
			int nbRepAttendues = 0;
			int nbRepUser = 0;
			
			// Calcul de la note /20
			for (QuestionTirage qt : questionTirages) {
				boolean isValid = true;
				
				nbRepAttendues = 0;
				nbRepUser = 0;
				
				ArrayList<ReponseTirage> reponseTirages = rtm.selectByAll(epreuve.getIdEpreuve(), qt.getQuestion().getId());
				ArrayList<Proposition> propositions = pm.selectByIdQuestion(qt.getQuestion().getId());
				
				for (Proposition p : propositions) {
					if (p.isEstBonne()) {
						nbRepAttendues++;
					}
				}
				
				for (ReponseTirage reponseTirage : reponseTirages) {
					nbRepUser++;
					if (!reponseTirage.getProposition().isEstBonne()) {
						isValid = false;
						break;
					}
				}
				
				if (reponseTirages.isEmpty()) {
					isValid = false;
				}
				
				if (isValid && nbRepAttendues == nbRepUser) {
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
			
			epreuve.setEtat("T");
			
			// Modif des resultats
			em.update(epreuve);
			ArrayList<Theme> listTheme =  thm.selectByIdTest(idTest);
			
			//Affichage Pourcetage
			for(Theme theme : listTheme)
			{
				themeresult = new ThemeResultat();
				note = rtm.selectcount(epreuve.getIdEpreuve(),theme.getId());
				themeresult.setTheme(theme.getLibelle());
				themeresult.setTauxReussite(String.valueOf(Math.round((note/stm.nbQuestionATirerByThemeId(theme.getId()))*100)));
				themereuslts.add(themeresult);
			}
			
			// On vide les tables tampons de tirage
			rtm.deleteAll();
			qtm.deleteAll();
			
			req.setAttribute("niveau", niveau);
			req.setAttribute("note", epreuve.getNoteObtenue());
			req.setAttribute("themeResultats", themereuslts);
			req.getRequestDispatcher("finEpreuve").forward(req, resp);		
		} catch (Exception e) {
			//resp.sendError(500);
			e.printStackTrace();
		}
	}
}
