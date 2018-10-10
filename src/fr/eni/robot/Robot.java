package fr.eni.robot;

import java.util.ArrayList;

import fr.eni.bll.manager.QuestionManager;
import fr.eni.bll.manager.factory.ManagerFactory;
import fr.eni.bo.Question;
import fr.eni.bo.SectionTest;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public abstract class Robot {
	public static ArrayList<Question> tirageAuSort(SectionTest sectionTest) {
		ArrayList<Question> questionsThemes = new ArrayList<Question>();
		ArrayList<Question> questionsTirees = new ArrayList<Question>();
		int nbATirer = sectionTest.getNbQuestionATirer();
		int index;
		
		try {
			// On prends toutes les questions du thème
			QuestionManager qm = ManagerFactory.questionManager();
			questionsThemes = qm.selectByTheme(sectionTest.getTheme().getId());
			
			while (nbATirer > 0) {
				index = (int)(Math.random() * questionsThemes.size());
				questionsTirees.add(questionsThemes.get(index));
				questionsThemes.remove(questionsThemes.get(index));
				nbATirer--;
			}
		} catch (ManagerException e) {
			e.printStackTrace();
		}
		
		return questionsTirees;
	}
}
