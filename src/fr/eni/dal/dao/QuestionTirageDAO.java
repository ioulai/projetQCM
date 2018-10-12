package fr.eni.dal.dao;

import java.util.ArrayList;

import fr.eni.bo.Epreuve;
import fr.eni.bo.Question;
import fr.eni.bo.QuestionTirage;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface QuestionTirageDAO {	
	void insert(QuestionTirage questionTirage) throws DaoException;

	ArrayList<QuestionTirage> selectByIdEpreuve(int id) throws DaoException;

	QuestionTirage selectByIds(Epreuve epreuve, Question questionEnCours) throws DaoException;

	void update(QuestionTirage questionTirage) throws DaoException;
}