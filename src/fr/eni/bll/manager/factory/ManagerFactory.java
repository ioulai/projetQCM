package fr.eni.bll.manager.factory;

<<<<<<< HEAD
import fr.eni.bll.manager.ArticleManager;
import fr.eni.bll.manager.EpreuveManager;
import fr.eni.bll.manager.ListeArticleManager;
import fr.eni.bll.manager.impl.ArticleManagerImpl;
import fr.eni.bll.manager.impl.EpreuveManagerImpl;
import fr.eni.bll.manager.impl.ListeArticleManagerImpl;
=======
import fr.eni.bll.manager.QuestionManager;
import fr.eni.bll.manager.ThemeManager;
import fr.eni.bll.manager.impl.QuestionManagerImpl;
>>>>>>> TirageSort

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
}
