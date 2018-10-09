package fr.eni.bll.manager.factory;

import fr.eni.bll.manager.ArticleManager;
import fr.eni.bll.manager.EpreuveManager;
import fr.eni.bll.manager.ListeArticleManager;
import fr.eni.bll.manager.impl.ArticleManagerImpl;
import fr.eni.bll.manager.impl.EpreuveManagerImpl;
import fr.eni.bll.manager.impl.ListeArticleManagerImpl;

public class ManagerFactory {
	public static ArticleManager articleManager() {
		return ArticleManagerImpl.getInstance();
	}
	
	public static ListeArticleManager listeArticleManager() {
		return ListeArticleManagerImpl.getInstance();
	}
	
	public static EpreuveManager epreuveManager() {
		return EpreuveManagerImpl.getInstance();
	}
}
