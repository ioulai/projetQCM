package fr.eni.ihm.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.manager.EpreuveManager;
import fr.eni.bll.manager.QuestionManager;
import fr.eni.bll.manager.QuestionTirageManager;
import fr.eni.bll.manager.SectionTestManager;
import fr.eni.bll.manager.factory.ManagerFactory;
import fr.eni.bo.Epreuve;
import fr.eni.bo.Question;
import fr.eni.bo.QuestionTirage;
import fr.eni.bo.SectionTest;
import fr.eni.robot.Robot;

public class QuestionController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			int idTest = Integer.parseInt(req.getParameter("idTest"));
			int idQuestion = 0;
			int compteurQuestion = 1;
			
			String strIdQuestion = req.getParameter("idQuestion");
			
			Epreuve epreuve;
			Question questionEnCours = null;
			
			if (strIdQuestion != null) {
				idQuestion = Integer.parseInt(strIdQuestion);
			}
			
			// Tirage au sort et insertion si les questions ont déjà été tirées
			if (idQuestion == 0) {
				// Création des managers
				SectionTestManager stm = ManagerFactory.sectionTestManager();
				QuestionTirageManager qtm = ManagerFactory.questionTirageManager();
				EpreuveManager em = ManagerFactory.epreuveManager();
				
				ArrayList<Question> questions = null;
				
				// Recherche de l'épreuve du test
				epreuve = em.selectByIdTest(idTest);
				
				// On va chercher les sections du test à passer
				ArrayList<SectionTest> sectionTests = stm.selectByIdTest(idTest);
				
				QuestionTirage questionTirage;
				
				// Pour chaque sections dans le test
				for (SectionTest st : sectionTests) {
					questions = new ArrayList<Question>();
					
					// On va chercher des questions aléatoire dans le thème de la section
					questions.addAll(Robot.tirageAuSort(st));
					
					questionTirage = new QuestionTirage();
					
					// Pour chaque question du test
					for (Question q : questions) {
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

				req.setAttribute("questions", questions);
			}
			else {
				// Si on change de question
				QuestionManager stm = ManagerFactory.questionManager();
				questionEnCours = stm.selectById(idQuestion);
			}

			req.setAttribute("questionEnCours", questionEnCours);
			req.setAttribute("idTest", idTest);
			
			req.getRequestDispatcher("question").forward(req, resp);
		} catch (Exception e) {
			resp.sendError(500);
		}
	}
}
