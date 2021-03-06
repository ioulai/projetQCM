package fr.eni.ihm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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
import fr.eni.bo.QuestionResultat;
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
			int idTest = Integer.parseInt(req.getParameter("idTest"));
			
			// On cherche l'�preuve du test
			EpreuveManager em = ManagerFactory.epreuveManager();
			Epreuve epreuve = null;
			epreuve = em.selectByIdTest(idTest);
			
			int sec = epreuve.getTest().getDuree().getSeconds() +  epreuve.getTest().getDuree().getHours()*3600 + epreuve.getTest().getDuree().getMinutes()*60;
			sec = sec - (epreuve.getTempsEcoule().getSeconds() +  epreuve.getTempsEcoule().getHours()*3600 + epreuve.getTempsEcoule().getMinutes()*60);

			// Si plus de temps, redirection sur preResultats
			if (sec <= 0) {
				ArrayList<QuestionResultat> listeQuestions = new ArrayList<QuestionResultat>();
				
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
					
					listeQuestions.add(new QuestionResultat(q.getQuestion(), q.isEstMarquee(), isResolue));
				}
				
				// Attributs � envoyer
				req.setAttribute("questions", listeQuestions);
				req.setAttribute("idTest", idTest);
				req.setAttribute("isModifiable", false);
				
				req.getRequestDispatcher("preResultats").forward(req, resp);
			}
			// Sinon on fait normal
			else {
				ArrayList<Integer> propSelected = new ArrayList<Integer>();
				int duree = Integer.parseInt(req.getParameter("chronoform"));
				int ordreQuestion = 1;
				
				// Gestion des ids propositions
				String[] idsProposition = req.getParameterValues("idPropositionUser");
				ArrayList<Integer> idsIntProposition = new ArrayList<Integer>();
				
				ReponseTirageManager rtm = ManagerFactory.reponseTirageManager();
				PropositionManager pm = ManagerFactory.propositionManager();
				
				String strIdQuestion = req.getParameter("idQuestionCourante");
				int idQuestion = Integer.parseInt(strIdQuestion);
				
				boolean isDispatchResults = false;
				boolean isMulti = false;
				
				// Si on valide bien avec une r�ponse de coch�e
				if (idsProposition != null){
					for (String id : idsProposition) {
						idsIntProposition.add(Integer.parseInt(id));
					}

					// On cherche la proposition pour prendre la question
					ArrayList<Proposition> propositionsUser = new ArrayList<Proposition>();
					
					for (int i : idsIntProposition) {
						propositionsUser.add(pm.selectById(i));
					}
					
					// Supprime les reponsesTirages pour cette question
					rtm.deleteByIds(epreuve.getIdEpreuve(), idQuestion);
					
					QuestionManager qm = ManagerFactory.questionManager();
					Question questionValidee = qm.selectById(idQuestion);
					
					// Pour chaque id proposition, on ajoute une ligne dans ReponseTirage
					for (Proposition p : propositionsUser) {
						ReponseTirage rt = new ReponseTirage();
						rt.setEpreuve(epreuve);
						rt.setProposition(p);
						rt.setQuestion(questionValidee);
						rtm.insert(rt);
					}
				}
				
				/* GESTION DES DONNEES A ENVOYER A L'IHM */
				
				// Gr�ce � l'�preuve, on cherche ses questionsTirage
				QuestionTirageManager qtm = ManagerFactory.questionTirageManager();
				ArrayList<QuestionTirage> questionTirages = qtm.selectByIdEpreuve(epreuve.getIdEpreuve());
				
				int ordreQuestPrec = 0;
				int ordreMax = 0;
				
				ArrayList<QuestionResultat> questions = new ArrayList<QuestionResultat>();
				Question questionSuivante = null;

				boolean isResolue = false;
				
				for (QuestionTirage qt : questionTirages) {
					// Si c'est la questionTirage pr�c�dent, on recup son ordre pour trouver la question suivante
					if (qt.getEpreuve().getIdEpreuve() == epreuve.getIdEpreuve() && qt.getQuestion().getId() == idQuestion) {
						ordreQuestPrec = qt.getNumOrdre();
					}
					
					// Mise de l'ordre max au max
					if (qt.getNumOrdre() > ordreMax) {
						ordreMax = qt.getNumOrdre();
					}
					
					// On a besoin de la liste des questions
					ArrayList<ReponseTirage> lrt = rtm.selectByAll(epreuve.getIdEpreuve(), qt.getQuestion().getId());
					isResolue = false;
					
					if (lrt != null && lrt.size() > 0) {
						isResolue = true;
					}
					
					questions.add(new QuestionResultat(qt.getQuestion(), qt.isEstMarquee(), isResolue));
				}
				
				// On reboucle pour chercher la prochaine question
				for (QuestionTirage qt : questionTirages) {
					if (questionSuivante == null && qt.getNumOrdre() == 1) {
						questionSuivante = qt.getQuestion();
						ordreQuestion = qt.getNumOrdre();
					}
					else if (qt.getNumOrdre() == (ordreQuestPrec + 1)) {
						questionSuivante = qt.getQuestion();
						ordreQuestion = qt.getNumOrdre();
						break;
					}
					// Si derni�re question, on va aux r�sultats
					else if (qt.getNumOrdre() == ordreMax) {
						isDispatchResults = true;
						ArrayList<QuestionResultat> listeQuestions = new ArrayList<QuestionResultat>();
						
						// On cherche les questions
						for (QuestionTirage q : questionTirages) {
							ArrayList<ReponseTirage> lrt = rtm.selectByAll(epreuve.getIdEpreuve(), q.getQuestion().getId());
							isResolue = false;
							
							if (lrt != null && lrt.size() > 0) {
								isResolue = true;
							}
							
							listeQuestions.add(new QuestionResultat(q.getQuestion(), q.isEstMarquee(), isResolue));
						}
						
						// Attributs � envoyer
						req.setAttribute("questions", listeQuestions);
						req.setAttribute("idTest", idTest);
						req.setAttribute("isModifiable", true);
						
						req.getRequestDispatcher("preResultats").forward(req, resp);
						break;
					}
				}
				
				if (!isDispatchResults) {
					// Recherche des propositions de la prochaine question
					ArrayList<Proposition> propositions = pm.selectByIdQuestion(questionSuivante.getId());
					int count = 0;
					
					for (Proposition p : propositions) {
						if (p.isEstBonne()) {
							count++;
						}
					}
					
					if (count > 1) {
						isMulti = true;
					}
					
					// Cochage de la prochaine question
					propSelected = searchPropSelected(epreuve, questionSuivante);
					
					// Libell� du test � afficher
					String libelle = epreuve.getTest().getLibelle();
					
					// Check si question marqu�e
					QuestionTirage questionTirage = qtm.selectByIds(epreuve, questionSuivante);
					boolean isMarquee = questionTirage.isEstMarquee();

					// Random ordre propositions
					Collections.shuffle(propositions);
					
					// Attributs � envoyer
					req.setAttribute("isMulti", isMulti);
					req.setAttribute("propositions", propositions);
					req.setAttribute("listeQuestions", questions);
					req.setAttribute("questionEnCours", questionSuivante);
					req.setAttribute("idTest", idTest);
					req.setAttribute("propSelected", propSelected);
					req.setAttribute("libelle", libelle);
					req.setAttribute("isMarquee", isMarquee);
					req.setAttribute("idEpreuve", epreuve.getIdEpreuve());
					req.setAttribute("duree", duree);
					req.setAttribute("ordreQuestion", ordreQuestion);
					
					req.getRequestDispatcher("question").forward(req, resp);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String strIdQuestion = req.getParameter("idQuestionCourante");
			int idTest = Integer.parseInt(req.getParameter("idTest"));
			
			ArrayList<Integer> propSelected = new ArrayList<Integer>();
			int idQuestion = 0;
			int compteurQuestion = 1;
			int ordreQuestion = 1;
			
			boolean isMulti = false;
			
			ArrayList<QuestionResultat> questions = new ArrayList<QuestionResultat>();
			ArrayList<Question> questionsTemp = new ArrayList<Question>();
			
			// Cr�ation des managers
			QuestionTirageManager qtm = ManagerFactory.questionTirageManager();
			EpreuveManager em = ManagerFactory.epreuveManager();
			SectionTestManager stm = ManagerFactory.sectionTestManager();
			QuestionManager qm = ManagerFactory.questionManager();
			PropositionManager pm = ManagerFactory.propositionManager();
			ReponseTirageManager rtm = ManagerFactory.reponseTirageManager();
			
			Epreuve epreuve = null;
			Question questionEnCours = null;
			
			if (strIdQuestion != null) {
				idQuestion = Integer.parseInt(strIdQuestion);
			}
			
			// Recherche de l'�preuve du test
			epreuve = em.selectByIdTest(idTest);
			
			// Duree � set
			int sec = epreuve.getTest().getDuree().getSeconds() +  epreuve.getTest().getDuree().getHours()*3600 + epreuve.getTest().getDuree().getMinutes()*60;
			
			// Test si reprise en cours de route
			if (epreuve.getEtat().equals("EC") && strIdQuestion == null) {

				sec = sec - (epreuve.getTempsEcoule().getSeconds() +  epreuve.getTempsEcoule().getHours()*3600 + epreuve.getTempsEcoule().getMinutes()*60);
				
				ArrayList<QuestionTirage> lstQuestionTirage = null;

				lstQuestionTirage = qtm.selectByIdEpreuve(epreuve.getIdEpreuve());
				boolean isResolue = true;
				
				for (QuestionTirage qt : lstQuestionTirage) {
					if (questionEnCours == null || (qt.getNumOrdre() < qtm.selectByIds(epreuve, questionEnCours).getNumOrdre())) {
						questionEnCours = qt.getQuestion();
					}
					
					ArrayList<ReponseTirage> lrt = rtm.selectByAll(epreuve.getIdEpreuve(), qt.getQuestion().getId());
					isResolue = false;
					
					if (lrt != null && lrt.size() > 0) {
						isResolue = true;
					}
					
					questions.add(new QuestionResultat(qt.getQuestion(), qt.isEstMarquee(), isResolue));
				}
			}
			
			// Tirage au sort et insertion si les questions n'ont pas �t� tir�es
			else if (idQuestion == 0) {

				// On va chercher les sections du test � passer
				ArrayList<SectionTest> sectionTests = stm.selectByIdTest(idTest);
				
				QuestionTirage questionTirage;
				
				// Mise � "en cours" de l'�preuve
				epreuve.setEtat("EC");
				em.update(epreuve);
				
				// Pour chaque sections dans le test
				for (SectionTest st : sectionTests) {
					questionsTemp = new ArrayList<Question>();
					
					// On va chercher des questions al�atoire dans le th�me de la section
					questionsTemp = Robot.tirageAuSort(st);
					
					for (Question q : questionsTemp) {
						questions.add(new QuestionResultat(q, false, false));
					}
					
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

				sec = epreuve.getTest().getDuree().getSeconds() +  epreuve.getTest().getDuree().getHours()*3600 + epreuve.getTest().getDuree().getMinutes()*60;
				sec = sec - (epreuve.getTempsEcoule().getSeconds() +  epreuve.getTempsEcoule().getHours()*3600 + epreuve.getTempsEcoule().getMinutes()*60);
				
				ArrayList<QuestionTirage> lstQuestionTirage = null;
				
				questionEnCours = qm.selectById(idQuestion);
				lstQuestionTirage = qtm.selectByIdEpreuve(epreuve.getIdEpreuve());
				boolean isResolue = false;
				
				for (QuestionTirage qt : lstQuestionTirage) {
					ArrayList<ReponseTirage> lrt = rtm.selectByAll(epreuve.getIdEpreuve(), qt.getQuestion().getId());
					isResolue = false;
					
					if (lrt != null && lrt.size() > 0) {
						isResolue = true;
					}
					
					questions.add(new QuestionResultat(qt.getQuestion(), qt.isEstMarquee(), isResolue));
				}
			}
			
			// Check si question marqu�e
			QuestionTirage questionTirage = qtm.selectByIds(epreuve, questionEnCours);
			boolean isMarquee = questionTirage.isEstMarquee();
			
			// Num ordre question en cours
			ordreQuestion = questionTirage.getNumOrdre();
			
			// Recherche proposition s�lectionn�e
			propSelected = searchPropSelected(epreuve, questionEnCours);

			// Libell� du test � afficher
			String libelle = epreuve.getTest().getLibelle();
			
			// Recherche des propositions
			ArrayList<Proposition> propositions = pm.selectByIdQuestion(questionEnCours.getId());
			int count = 0;
			
			for (Proposition p : propositions) {
				if (p.isEstBonne()) {
					count++;
				}
			}
			
			if (count > 1) {
				isMulti = true;
			}
			
			// Random ordre propositions
			Collections.shuffle(propositions);
			
			// Attributs � envoyer
			req.setAttribute("propositions", propositions);
			req.setAttribute("listeQuestions", questions);
			req.setAttribute("questionEnCours", questionEnCours);
			req.setAttribute("idTest", idTest);
			req.setAttribute("propSelected", propSelected);
			req.setAttribute("libelle", libelle);
			req.setAttribute("isMarquee", isMarquee);
			req.setAttribute("isMulti", isMulti);
			req.setAttribute("duree", sec);
			req.setAttribute("idEpreuve", epreuve.getIdEpreuve());
			req.setAttribute("ordreQuestion", ordreQuestion);
			
			req.getRequestDispatcher("question").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			//resp.sendError(500);
		}
	}

	// Chercher la reponseTirage de la question en cours
	private ArrayList<Integer> searchPropSelected(Epreuve epreuve, Question questionEnCours) throws ManagerException {
		ReponseTirageManager rtm = ManagerFactory.reponseTirageManager();
		ArrayList<Integer> liste = new ArrayList<Integer>();

		ArrayList<ReponseTirage> reponseTirages = rtm.selectByAll(epreuve.getIdEpreuve(), questionEnCours.getId());
		
		for (ReponseTirage rt : reponseTirages) {
			liste.add(rt.getProposition().getId());
		}
		
		return liste;
	}
}
