package fr.eni.bll.manager.factory;

import fr.eni.bll.manager.QuestionManager;
import fr.eni.bll.manager.ThemeManager;
import fr.eni.bll.manager.impl.QuestionManagerImpl;

public class ManagerFactory {
	public static QuestionManager questionManager() {
		return QuestionManagerImpl.getInstance();
	}
	
	public static ThemeManager themeManager() {
		return ThemeManagerImpl.getInstance();
	}
}
