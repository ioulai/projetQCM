package fr.eni.bll.manager;

import fr.eni.bo.ReponseTirage;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public interface ReponseTirageManager {
	
	void insert(ReponseTirage reponseTirage) throws ManagerException;

	ReponseTirage selectByAll(int idEpreuve, int idQuestion) throws ManagerException;

	void update(ReponseTirage reponseTirage) throws ManagerException;

	void deleteAll() throws ManagerException;
}
