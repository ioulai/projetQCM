package fr.eni.ihm.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.manager.EpreuveManager;
import fr.eni.bll.manager.PropositionManager;
import fr.eni.bll.manager.QuestionManager;
import fr.eni.bll.manager.QuestionTirageManager;
import fr.eni.bll.manager.ReponseTirageManager;
import fr.eni.bll.manager.factory.ManagerFactory;
import fr.eni.bo.Epreuve;
import fr.eni.bo.Proposition;
import fr.eni.bo.Question;
import fr.eni.bo.QuestionTirage;
import fr.eni.bo.ReponseTirage;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public class MarquageController extends HttpServlet{
	private static final long serialVersionUID = -6970893575378675464L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int idQuestion = Integer.parseInt(req.getParameter("idQuestionCourante"));
			int idTest = Integer.parseInt(req.getParameter("idTest"));
			int propSelected = 0;
			
			Question question;
			Epreuve epreuve = null;
			
			ArrayList<QuestionTirage> lstQuestionTirage = null;
			ArrayList<Question> questions = new ArrayList<Question>();
			
			// Création des managers
			EpreuveManager em = ManagerFactory.epreuveManager();
			QuestionTirageManager qtm = ManagerFactory.questionTirageManager();
			QuestionManager qm = ManagerFactory.questionManager();
			PropositionManager pm = ManagerFactory.propositionManager();
			
			// Recherche de l'épreuve du test
			epreuve = em.selectByIdTest(idTest);
			
			// Recherche de la question
			question = qm.selectById(idQuestion);
			lstQuestionTirage = qtm.selectByIdEpreuve(epreuve.getIdEpreuve());
			
			for (QuestionTirage qt : lstQuestionTirage) {
				questions.add(qt.getQuestion());
			}
			
			// Recherche proposition sélectionnée
			propSelected = searchPropSelected(epreuve, question);

			// Libellé du test à afficher
			String libelle = epreuve.getTest().getLibelle();
			
			// Recherche des propositions
			ArrayList<Proposition> propositions = pm.selectByIdQuestion(question.getId());
			req.setAttribute("propositions", propositions);
			
			// Check si question marquée
			QuestionTirage questionTirage = qtm.selectByIds(epreuve, question);
			
			// Modifier le marquage
			questionTirage.setEstMarquee(!questionTirage.isEstMarquee());
			qtm.update(questionTirage);

			boolean isMarquee = questionTirage.isEstMarquee();
			
			// Attributs à envoyer
			req.setAttribute("listeQuestions", questions);
			req.setAttribute("questionEnCours", question);
			req.setAttribute("idTest", idTest);
			req.setAttribute("propSelected", propSelected);
			req.setAttribute("libelle", libelle);
			req.setAttribute("isMarquee", isMarquee);
			
			req.getRequestDispatcher("question").forward(req, resp);
		} catch (Exception e) {
			resp.sendError(500);
		}
	}
	
	// Chercher la reponseTirage de la question en cours
	private int searchPropSelected(Epreuve epreuve, Question questionEnCours) throws ManagerException {
		ReponseTirageManager rtm = ManagerFactory.reponseTirageManager();

		int propSelected = 0;
		ReponseTirage reponseTirage;
		reponseTirage = rtm.selectByAll(epreuve.getIdEpreuve(), questionEnCours.getId());
		
		// Permet la sélection de la réponse entrée
		if (reponseTirage != null) {
			propSelected = reponseTirage.getProposition().getId();
		}
		
		return propSelected;
	}
}
