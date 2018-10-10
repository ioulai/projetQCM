package fr.eni.dal.dao;

import fr.eni.bo.Candidat;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface CandidatDAO {
	Candidat selectById(int id) throws DaoException;
	Candidat selectByEmailPassword(String email, String password) throws DaoException;
}
