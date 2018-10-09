package fr.eni.dal.dao;

import fr.eni.bo.Question;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface QuestionDAO {	
	Question selectById(int id) throws DaoException;
}