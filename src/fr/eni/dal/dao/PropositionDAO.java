package fr.eni.dal.dao;

import java.util.ArrayList;

import fr.eni.bo.Proposition;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface PropositionDAO {
	ArrayList<Proposition> selectByIdQuestion(int id) throws DaoException;

	Proposition selectById(int id) throws DaoException;
}