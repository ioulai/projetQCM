package fr.eni.bll.manager.factory;

import fr.eni.bll.manager.EpreuveManager;
import fr.eni.bll.manager.impl.EpreuveManagerImpl;
import fr.eni.bll.manager.QuestionManager;
import fr.eni.bll.manager.SectionTestManager;
import fr.eni.bll.manager.ThemeManager;
import fr.eni.bll.manager.impl.QuestionManagerImpl;
import fr.eni.bll.manager.impl.SectionTestManagerImpl;
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
}
