package fr.eni.bll.manager;

import java.util.List;

import fr.eni.bo.ListeArticle;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public interface ListeArticleManager {		
	ListeArticle insert(ListeArticle element) throws ManagerException;
	
	List<ListeArticle> selectAll() throws ManagerException;
	
	void deleteById(int id) throws ManagerException;
	
	ListeArticle selectByNom(String nom) throws ManagerException;
}
