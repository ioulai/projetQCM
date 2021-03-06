package fr.eni.dal.dao;

import java.util.Date;
import java.util.List;

import fr.eni.bo.Epreuve;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface EpreuveDAO {
	
	List<Epreuve> selectAll() throws DaoException;
	
	Epreuve selectById(int id) throws DaoException;
	
	Epreuve selectByIdTest(int id) throws DaoException;
	
	Epreuve insert(int idCandidat, int idTest,Date debutValidite,Date finValidite) throws DaoException;
	
	List<Epreuve> selectByIdCandidatTest(int idCandidat, int idTest) throws DaoException;
	
	List<Epreuve> selectByUserId(int id) throws DaoException;

	void update(Epreuve epreuve) throws DaoException;
	
	void deleteById(int id) throws DaoException;

	Epreuve selectByIdTestIdCandidat(int idTest, int idCandidat) throws DaoException;
	
	void updateTime(Epreuve epreuve) throws DaoException;
}
