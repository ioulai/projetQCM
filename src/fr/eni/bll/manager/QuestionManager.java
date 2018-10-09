package fr.eni.bll.manager;

import fr.eni.bo.Question;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public interface QuestionManager {	
	Question selectById(int id) throws ManagerException;
}
