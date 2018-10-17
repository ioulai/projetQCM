package fr.eni.dal.dao;

import java.util.ArrayList;

import fr.eni.bo.ReponseTirage;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface ReponseTirageDAO {	
	void insert(ReponseTirage reponseTirage) throws DaoException;

	ArrayList<ReponseTirage> selectByAll(int idEpreuve, int idQuestion) throws DaoException;

	void update(ReponseTirage reponseTirage) throws DaoException;

	void deleteAll() throws DaoException;

	void deleteByIds(int idEpreuve, int idQuestion) throws DaoException;
	
	int selectcount(int idEpreuve,int idtheme) throws DaoException;
}