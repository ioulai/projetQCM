package fr.eni.dal.dao;

import fr.eni.bo.Proposition;
import fr.eni.bo.QuestionTirage;
import fr.eni.bo.ReponseTirage;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface ReponseTirageDAO {	
	void insert(ReponseTirage reponseTirage) throws DaoException;

	ReponseTirage selectByAll(QuestionTirage questionTirage, Proposition proposition) throws DaoException;
}