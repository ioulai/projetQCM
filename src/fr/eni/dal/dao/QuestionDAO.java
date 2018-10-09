package fr.eni.dal.dao;

import java.util.ArrayList;

import fr.eni.bo.Question;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface QuestionDAO {	
	Question selectById(int id) throws DaoException;

	ArrayList<Question> selectByTheme(int id) throws DaoException;
}