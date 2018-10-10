package fr.eni.dal.dao;

import fr.eni.bo.QuestionTirage;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface QuestionTirageDAO {	
	void insert(QuestionTirage questionTirage) throws DaoException;
}