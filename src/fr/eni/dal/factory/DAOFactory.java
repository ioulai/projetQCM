package fr.eni.dal.factory;

import fr.eni.dal.dao.ArticleDAO;
import fr.eni.dal.dao.impl.ArticleDAOImpl;
import fr.eni.dal.dao.impl.ListeArticleDAOImpl;
import fr.eni.dal.dao.ListeArticleDAO;
import fr.eni.dal.dao.EpreuveDAO;

public class DAOFactory {
	public static ArticleDAO ArticleDAO() {
		return ArticleDAOImpl.getInstance();
	}
	
	public static ListeArticleDAO ListeArticleDAO() {
		return ListeArticleDAOImpl.getInstance();
	}
	
	public static EpreuveDAO EpreuveDAO() {
		return EpreuveDAOImpl.getInstance();
	}
}
