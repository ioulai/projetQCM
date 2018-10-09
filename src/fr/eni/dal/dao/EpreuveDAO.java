package fr.eni.dal.dao;

import java.util.List;

import fr.eni.bo.Epreuve;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface EpreuveDAO {
	
	List<Epreuve> selectAll() throws DaoException;
	
	Epreuve selectById(int id, Epreuve epreuve) throws DaoException;

	//Epreuve update(Epreuve epreuve) throws DaoException;
	
	//Epreuve insert(Epreuve epreuve) throws DaoException;
	
	//void delete(Integer id) throws DaoException;
}
