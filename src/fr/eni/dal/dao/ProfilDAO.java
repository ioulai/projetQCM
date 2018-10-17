package fr.eni.dal.dao;

import java.util.List;

import fr.eni.bo.Profil;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface ProfilDAO {
	List<Profil> selectAll() throws DaoException;
	Profil selectByName(String name) throws DaoException;
	Profil selectById(int id) throws DaoException;
}
