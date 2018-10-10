package fr.eni.dal.dao;

import java.util.List;

import fr.eni.bo.Epreuve;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface EpreuveDAO {
	
	List<Epreuve> selectAll() throws DaoException;
	
	Epreuve selectById(int id) throws DaoException;
	
	Epreuve selectByIdTest(int id) throws DaoException;
}
