package fr.eni.bll.manager;

import java.util.ArrayList;

import fr.eni.bo.Epreuve;
import fr.eni.bo.Question;
import fr.eni.bo.QuestionTirage;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public interface QuestionTirageManager {
	
	void insert(QuestionTirage questionTirage) throws ManagerException;
	
	ArrayList<QuestionTirage> selectByIdEpreuve(int id) throws ManagerException;

	QuestionTirage selectByIds(Epreuve epreuve, Question questionEnCours) throws ManagerException;

	void update(QuestionTirage questionTirage) throws ManagerException;

	void deleteAll() throws ManagerException;
}
