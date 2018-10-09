package fr.eni.dal.dao;

import java.util.List;

import fr.eni.bo.Article;
import fr.eni.bo.ListeArticle;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface ArticleDAO {
	List<Article> selectByIdListe(ListeArticle liste) throws DaoException;
	
	void deleteByIdListe(int id) throws DaoException;

	Article selectById(int id, ListeArticle liste) throws DaoException;

	Article updateSelected(Article article) throws DaoException;
	
	Article insert(Article element) throws DaoException;
	
	void delete(Integer id) throws DaoException;
}