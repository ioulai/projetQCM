package fr.eni.bll.manager;

import fr.eni.bo.Proposition;
import fr.eni.bo.QuestionTirage;
import fr.eni.bo.ReponseTirage;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public interface ReponseTirageManager {
	
	void insert(ReponseTirage reponseTirage) throws ManagerException;

	ReponseTirage selectByAll(QuestionTirage questionTirage, Proposition proposition) throws ManagerException;
}
