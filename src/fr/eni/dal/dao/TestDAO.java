package fr.eni.dal.dao;

import java.util.List;

import fr.eni.bo.Test;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface TestDAO {
	Test selectById(int id) throws DaoException;
	
	List<Test> selectAll(int id) throws DaoException;
}
