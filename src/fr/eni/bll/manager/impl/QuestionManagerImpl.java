package fr.eni.bll.manager.impl;

import java.util.ArrayList;

import fr.eni.bll.manager.QuestionManager;
import fr.eni.bo.Question;
import fr.eni.dal.dao.QuestionDAO;
import fr.eni.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.util.ValidationUtil;

public class QuestionManagerImpl implements QuestionManager {
	private static QuestionManager singleton = null;
	private QuestionDAO questionDAO = DAOFactory.QuestionDAO();
	
	public static QuestionManager getInstance() {
		if (singleton == null)
			singleton = new QuestionManagerImpl();
		
		return singleton;
	}

	@Override
	public Question selectById(int id) throws ManagerException {
		Question question = null;
		
		try {
			question = questionDAO.selectById(id);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return question;
	}
	
	@Override
	public ArrayList<Question> selectByTheme(int id) throws ManagerException {
		ArrayList<Question> questions = new ArrayList<Question>();
		
		ValidationUtil.checkNotNull(questions);
		
		try {
			questions = questionDAO.selectByTheme(id);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return questions;
	}

	@Override
	public ArrayList<Question> selectByIdTest(int id) throws ManagerException {
		ArrayList<Question> questions = new ArrayList<Question>();
		
		ValidationUtil.checkNotNull(questions);
		
		try {
			questions = questionDAO.selectByIdTest(id);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return questions;
	}
}
