package fr.eni.bll.manager;

import java.util.List;

import fr.eni.bo.Article;
import fr.eni.bo.ListeArticle;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public interface ArticleManager {	
	Article insert(Article element) throws ManagerException;
	
	void delete(int id) throws ManagerException;
	
	List<Article> selectByIdListe(ListeArticle liste) throws ManagerException;
	
	void deleteByIdListe(int id) throws ManagerException;
	
	Article selectById(int id, ListeArticle liste) throws ManagerException;
	
	Article updateSelected(Article article) throws ManagerException;
}
