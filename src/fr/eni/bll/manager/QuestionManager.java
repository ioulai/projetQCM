package fr.eni.bll.manager;

import java.util.ArrayList;

import fr.eni.bo.Question;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public interface QuestionManager {	
	Question selectById(int id) throws ManagerException;
	
	ArrayList<Question> selectByTheme(int id) throws ManagerException;
	
	ArrayList<Question> selectByIdTest(int id)throws ManagerException;
}
