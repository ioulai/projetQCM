package fr.eni.dal.factory;

import fr.eni.dal.dao.ArticleDAO;
import fr.eni.dal.dao.impl.ArticleDAOImpl;
import fr.eni.dal.dao.impl.ListeArticleDAOImpl;
import fr.eni.dal.dao.ListeArticleDAO;

public class DAOFactory {
	public static ArticleDAO ArticleDAO() {
		return ArticleDAOImpl.getInstance();
	}
	
	public static ListeArticleDAO ListeArticleDAO() {
		return ListeArticleDAOImpl.getInstance();
	}
}
