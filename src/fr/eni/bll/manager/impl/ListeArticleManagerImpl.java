package fr.eni.bll.manager.impl;

import java.util.ArrayList;
import java.util.List;

import fr.eni.bll.manager.ListeArticleManager;
import fr.eni.bo.ListeArticle;
import fr.eni.dal.dao.ListeArticleDAO;
import fr.eni.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.util.ValidationUtil;

public class ListeArticleManagerImpl implements ListeArticleManager {

	private static ListeArticleManager singleton = null;
	private ListeArticleDAO listeArticleDAO = DAOFactory.ListeArticleDAO();
	
	public static ListeArticleManager getInstance() {
		if (singleton == null)
			singleton = new ListeArticleManagerImpl();
		
		return singleton;
	}

	@Override
	public ListeArticle insert(ListeArticle element) throws ManagerException {
		ListeArticle liste = null;
		
		ValidationUtil.checkNotNull(element);
		
		try {
			liste = listeArticleDAO.insert(element);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		return liste;
	}

	@Override
	public List<ListeArticle> selectAll() throws ManagerException {
		List<ListeArticle> liste = new ArrayList<ListeArticle>();
		
		try {
			liste = listeArticleDAO.selectAll();
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
	
		return liste;
	}

	@Override
	public void deleteById(int id) throws ManagerException {
		try {
			listeArticleDAO.deleteById(id);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
	}

	@Override
	public ListeArticle selectByNom(String nom) throws ManagerException {
		ListeArticle liste = null;
		
		ValidationUtil.checkNotNull(nom);
		
		try {
			liste = listeArticleDAO.selectByNom(nom);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		return liste;
	}
}
