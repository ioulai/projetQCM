package fr.eni.bll.manager.impl;

import java.util.ArrayList;
import java.util.List;

import fr.eni.bll.manager.ArticleManager;
import fr.eni.bo.Article;
import fr.eni.bo.ListeArticle;
import fr.eni.dal.dao.ArticleDAO;
import fr.eni.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.util.ValidationUtil;

public class ArticleManagerImpl implements ArticleManager {

	private static ArticleManager singleton = null;
	private ArticleDAO articleDAO = DAOFactory.ArticleDAO();
	
	public static ArticleManager getInstance() {
		if (singleton == null)
			singleton = new ArticleManagerImpl();
		
		return singleton;
	}

	@Override
	public Article insert(Article element) throws ManagerException {
		ValidationUtil.checkNotNull(element);
		
		Article article = null;
		
		try {
			article = articleDAO.insert(element);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return article;
	}

	@Override
	public void delete(int id) throws ManagerException {		
		try {
			articleDAO.delete(id);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
	}

	@Override
	public List<Article> selectByIdListe(ListeArticle liste) throws ManagerException {
		List<Article> listeArt = new ArrayList<Article>();
		
		ValidationUtil.checkNotNull(liste);
		
		try {
			listeArt = articleDAO.selectByIdListe(liste);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return listeArt;
	}

	@Override
	public void deleteByIdListe(int id) throws ManagerException {
		try {
			articleDAO.deleteByIdListe(id);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
	}

	@Override
	public Article selectById(int id, ListeArticle liste) throws ManagerException {			
		Article article = null;
		
		try {
			article = articleDAO.selectById(id, liste);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return article;
	}

	@Override
	public Article updateSelected(Article article) throws ManagerException {
		ValidationUtil.checkNotNull(article);
		
		try {
			article = articleDAO.updateSelected(article);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return article;
	}
}
