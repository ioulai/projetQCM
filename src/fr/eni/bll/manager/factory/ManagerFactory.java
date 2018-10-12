package fr.eni.bll.manager.factory;

import fr.eni.bll.manager.CandidatManager;
import fr.eni.bll.manager.EpreuveManager;
import fr.eni.bll.manager.ProfilManager;
import fr.eni.bll.manager.PromotionManager;

import fr.eni.bll.manager.impl.CandidatManagerImpl;

import fr.eni.bll.manager.impl.EpreuveManagerImpl;
import fr.eni.bll.manager.impl.ProfilManagerImpl;
import fr.eni.bll.manager.impl.PromotionManagerImpl;
import fr.eni.bll.manager.QuestionManager;
import fr.eni.bll.manager.QuestionTirageManager;
import fr.eni.bll.manager.SectionTestManager;
import fr.eni.bll.manager.TestManager;
import fr.eni.bll.manager.ThemeManager;
import fr.eni.bll.manager.impl.QuestionManagerImpl;
import fr.eni.bll.manager.impl.QuestionTirageManagerImpl;
import fr.eni.bll.manager.impl.SectionTestManagerImpl;
import fr.eni.bll.manager.impl.TestManagerImpl;
import fr.eni.bll.manager.impl.ThemeManagerImpl;

public class ManagerFactory {
	public static QuestionManager questionManager() {
		return QuestionManagerImpl.getInstance();
	}
	
	public static ThemeManager themeManager() {
		return ThemeManagerImpl.getInstance();
	}
	
	public static EpreuveManager epreuveManager() {
		return EpreuveManagerImpl.getInstance();
	}
	
	public static SectionTestManager sectionTestManager() {
		return SectionTestManagerImpl.getInstance();
	}


	public static PromotionManager PromotionManager() {
		return PromotionManagerImpl.getInstance();
	}
	
	public static QuestionTirageManager questionTirageManager() {
		return QuestionTirageManagerImpl.getInstance();
	}
	
	public static CandidatManager candidatManager(){
		return CandidatManagerImpl.getInstance();
	}
	
	public static ProfilManager ProfilManager(){
		return ProfilManagerImpl.getInstance();
	}
	
	public static TestManager TestManager(){
		return TestManagerImpl.getInstance();
	}
}
