package fr.eni.bll.manager.impl;

import java.util.ArrayList;

import fr.eni.bll.manager.QuestionTirageManager;
import fr.eni.bo.QuestionTirage;
import fr.eni.dal.dao.QuestionTirageDAO;
import fr.eni.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.util.ValidationUtil;

public class QuestionTirageManagerImpl implements QuestionTirageManager {
	private static QuestionTirageManager singleton = null;
	private QuestionTirageDAO questionTirageDAO = DAOFactory.QuestionTirageDAO();
	
	public static QuestionTirageManager getInstance() {
		if (singleton == null)
			singleton = new QuestionTirageManagerImpl();
		
		return singleton;
	}

	@Override
	public void insert(QuestionTirage questionTirage) throws ManagerException {
		ValidationUtil.checkNotNull(questionTirage);
		
		try {
			questionTirageDAO.insert(questionTirage);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
	}

	@Override
	public ArrayList<QuestionTirage> selectByIdEpreuve(int id) throws ManagerException {
		ArrayList<QuestionTirage> questions = null;
		
		try {
			questions = questionTirageDAO.selectByIdEpreuve(id);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		return questions;
	}
}
