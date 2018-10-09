package fr.eni.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eni.bo.Question;
import fr.eni.dal.dao.QuestionDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class QuestionDAOImpl implements QuestionDAO{
private static QuestionDAOImpl singleton;
	
	private static final String SELECT_BY_ID_QUERY = "SELECT * FROM question q INNER JOIN theme t ON q.theme_idTheme = t.idTheme WHERE idQuestion = ?";
	private static final String SELECT_BY_THEME_QUERY = "SELECT * FROM question q INNER JOIN theme t ON q.theme_idTheme = t.idTheme WHERE idTheme = ?";
	
	public static QuestionDAO getInstance() {
		if (singleton == null)
			singleton = new QuestionDAOImpl();
		
		return singleton;
	}

	@Override
	public Question selectById(int id) throws DaoException {
		Question question= null;
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_BY_ID_QUERY);
			statement.setInt(1, id);
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				question = map(resultSet);
				question.setTheme(ThemeDAOImpl.map(resultSet));
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return question;
	}
	
	@Override
	public ArrayList<Question> selectByTheme(int id) throws DaoException {
		Question question = null;
		ArrayList<Question> questions = new ArrayList<Question>();
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_BY_THEME_QUERY);
			statement.setInt(1, id);
			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				question = map(resultSet);
				question.setTheme(ThemeDAOImpl.map(resultSet));
				questions.add(question);
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return questions;
	}
	
	private static Question map(ResultSet rst) throws SQLException {
		Question question = new Question();
		question.setId(rst.getInt("idQuestion"));
		question.setMedia(rst.getString("media"));
		question.setEnonce(rst.getString("enonce"));
		question.setPoints(rst.getFloat("points"));
		
		return question;
	}
}
