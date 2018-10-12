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
import fr.eni.bll.manager.SectionTestManager;
import fr.eni.bll.manager.factory.ManagerFactory;
import fr.eni.bo.Epreuve;
import fr.eni.bo.Proposition;
import fr.eni.bo.Question;
import fr.eni.bo.QuestionTirage;
import fr.eni.bo.ReponseTirage;
import fr.eni.bo.SectionTest;
import fr.eni.robot.Robot;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public class QuestionController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int propSelected = 0;
			int idPropositionValidee = Integer.parseInt(req.getParameter("idPropositionUser"));
			int idTest = Integer.parseInt(req.getParameter("idTest"));
			
			// On cherche l'épreuve du test
			EpreuveManager em = ManagerFactory.epreuveManager();
			Epreuve epreuve = null;
			epreuve = em.selectByIdTest(idTest);

			// On cherche la proposition pour prendre la question
			PropositionManager pm = ManagerFactory.propositionManager();
			Proposition proposition = null;
			proposition = pm.selectById(idPropositionValidee);
			
			// On obtient la question
			Question questionRepondue = proposition.getQuestion();
			
			// On test si il y a déjà une réponse en base
			ReponseTirageManager rtm = ManagerFactory.reponseTirageManager();
			ReponseTirage reponseEnBase = rtm.selectByAll(epreuve.getIdEpreuve(), questionRepondue.getId());
			
			// On insert la réponse en base si existe pas
			if (reponseEnBase == null) {
				ReponseTirage reponseTirage = new ReponseTirage();
				reponseTirage.setEpreuve(epreuve);
				reponseTirage.setProposition(proposition);
				reponseTirage.setQuestion(questionRepondue);
				rtm.insert(reponseTirage);
			}
			else { // Si réponse déjà en base
				// Si réponse différente de celle en base
				if (reponseEnBase.getProposition().getId() != proposition.getId()) {
					reponseEnBase.setProposition(proposition);
					rtm.update(reponseEnBase);
				}
			}
			
			/* GESTION DES DONNEES A ENVOYER A L'IHM */
			
			// Grâce à l'épreuve, on cherche ses questionsTirage
			QuestionTirageManager qtm = ManagerFactory.questionTirageManager();
			ArrayList<QuestionTirage> questionTirages = qtm.selectByIdEpreuve(epreuve.getIdEpreuve());
			
			int ordreQuestPrec = 0;
			ArrayList<Question> questions = new ArrayList<Question>();
			Question questionSuivante = null;

			for (QuestionTirage qt : questionTirages) {
				// Si c'est la questionTirage précédent, on recup son ordre pour trouver la question suivante
				if (qt.getEpreuve().getIdEpreuve() == epreuve.getIdEpreuve() && qt.getQuestion().getId() == questionRepondue.getId()) {
					ordreQuestPrec = qt.getNumOrdre();
				}
				// On a besoin de la liste des questions
				questions.add(qt.getQuestion());
			}
			
			// On reboucle pour chercher la prochaine question
			for (QuestionTirage qt : questionTirages) {
				if (questionSuivante == null && qt.getNumOrdre() == 1) {
					questionSuivante = qt.getQuestion();
				}
				else if (qt.getNumOrdre() == (ordreQuestPrec + 1)) {
					questionSuivante = qt.getQuestion();
					break;
				}
			}
			
			// Recherche des propositions de la prochaine question
			ArrayList<Proposition> propositions = pm.selectByIdQuestion(questionSuivante.getId());
			
			// Cochage de la prochaine question
			propSelected = searchPropSelected(epreuve, questionSuivante);
			
			// Libellé du test à afficher
			String libelle = epreuve.getTest().getLibelle();
			
			// Attributs à envoyer
			req.setAttribute("propositions", propositions);
			req.setAttribute("listeQuestions", questions);
			req.setAttribute("questionEnCours", questionSuivante);
			req.setAttribute("idTest", idTest);
			req.setAttribute("propSelected", propSelected);
			req.setAttribute("libelle", libelle);
			
			req.getRequestDispatcher("question").forward(req, resp);
		}
		catch (Exception e) {
			resp.sendError(500);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String strIdQuestion = req.getParameter("idQuestionCourante");
			int idTest = Integer.parseInt(req.getParameter("idTest"));
			
			int propSelected = 0;
			int idQuestion = 0;
			int compteurQuestion = 1;
			
			ArrayList<Question> questions = new ArrayList<Question>();
			ArrayList<Question> questionsTemp = new ArrayList<Question>();
			
			// Création des managers
			QuestionTirageManager qtm = ManagerFactory.questionTirageManager();
			EpreuveManager em = ManagerFactory.epreuveManager();
			SectionTestManager stm = ManagerFactory.sectionTestManager();
			QuestionManager qm = ManagerFactory.questionManager();
			PropositionManager pm = ManagerFactory.propositionManager();
			
			Epreuve epreuve = null;
			Question questionEnCours = null;
			
			if (strIdQuestion != null) {
				idQuestion = Integer.parseInt(strIdQuestion);
			}
			
			// Recherche de l'épreuve du test
			epreuve = em.selectByIdTest(idTest);
			
			// Tirage au sort et insertion si les questions n'ont pas été tirées
			if (idQuestion == 0) {
				
				// On va chercher les sections du test à passer
				ArrayList<SectionTest> sectionTests = stm.selectByIdTest(idTest);
				
				QuestionTirage questionTirage;
				
				// Pour chaque sections dans le test
				for (SectionTest st : sectionTests) {
					questionsTemp = new ArrayList<Question>();
					
					// On va chercher des questions aléatoire dans le thème de la section
					questionsTemp.addAll(Robot.tirageAuSort(st));
					questions.addAll(Robot.tirageAuSort(st));
					
					questionTirage = new QuestionTirage();
					
					// Pour chaque question du test
					for (Question q : questionsTemp) {
						if (questionEnCours == null) {
							questionEnCours = q;
						}
						
						// On insert le question tirage avec sa question, son epreuve etc...
						questionTirage = new QuestionTirage();
						questionTirage.setEstMarquee(false);
						questionTirage.setNumOrdre(compteurQuestion);
						questionTirage.setQuestion(q);
						questionTirage.setEpreuve(epreuve);
						qtm.insert(questionTirage);
						compteurQuestion++;
					}
				}
			}
			else { // Si on change de question
				ArrayList<QuestionTirage> lstQuestionTirage = null;
				
				questionEnCours = qm.selectById(idQuestion);
				lstQuestionTirage = qtm.selectByIdEpreuve(epreuve.getIdEpreuve());
				
				for (QuestionTirage qt : lstQuestionTirage) {
					questions.add(qt.getQuestion());
				}
			}
			
			// Check si question marquée
			QuestionTirage questionTirage = qtm.selectByIds(epreuve, questionEnCours);
			boolean isMarquee = questionTirage.isEstMarquee();
			
			// Recherche proposition sélectionnée
			propSelected = searchPropSelected(epreuve, questionEnCours);

			// Libellé du test à afficher
			String libelle = epreuve.getTest().getLibelle();
			
			// Recherche des propositions
			ArrayList<Proposition> propositions = pm.selectByIdQuestion(questionEnCours.getId());
			req.setAttribute("propositions", propositions);
			
			// Attributs à envoyer
			req.setAttribute("listeQuestions", questions);
			req.setAttribute("questionEnCours", questionEnCours);
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
