package fr.eni.dal.dao;

import fr.eni.bo.Test;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface TestDAO {
	Test selectById(int id) throws DaoException;
}
