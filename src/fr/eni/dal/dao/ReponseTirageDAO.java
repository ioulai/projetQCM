package fr.eni.dal.dao;

import fr.eni.bo.ReponseTirage;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface ReponseTirageDAO {	
	void insert(ReponseTirage reponseTirage) throws DaoException;

	ReponseTirage selectByAll(int idEpreuve, int idQuestion) throws DaoException;

	void update(ReponseTirage reponseTirage) throws DaoException;
}