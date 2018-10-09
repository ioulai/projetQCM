package fr.eni.dal.dao;

import java.util.List;

import fr.eni.bo.ListeArticle;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface ListeArticleDAO {
	ListeArticle insert(ListeArticle element) throws DaoException;
	
	List<ListeArticle> selectAll() throws DaoException;
	
	void deleteById(int id) throws DaoException;

	ListeArticle selectByNom(String nom) throws DaoException;
}