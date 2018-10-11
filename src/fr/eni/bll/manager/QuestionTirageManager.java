package fr.eni.bll.manager;

import java.util.ArrayList;

import fr.eni.bo.QuestionTirage;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public interface QuestionTirageManager {
	
	void insert(QuestionTirage questionTirage) throws ManagerException;
	
	ArrayList<QuestionTirage> selectByIdEpreuve(int id) throws ManagerException;
}
