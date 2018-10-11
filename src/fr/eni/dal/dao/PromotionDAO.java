package fr.eni.dal.dao;

import java.util.List;

import fr.eni.bo.Promotion;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface PromotionDAO {

	List<Promotion> selectAll() throws DaoException;
	
	Promotion insert(Promotion promotion) throws DaoException;
	Promotion selectByName(String name) throws DaoException;
}
