package fr.eni.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eni.bo.Proposition;
import fr.eni.dal.dao.PropositionDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class PropositionDAOImpl implements PropositionDAO{
	private static PropositionDAOImpl singleton;
	
	private static final String SELECT_BY_ID_QUESTION_QUERY = "SELECT * FROM proposition p INNER JOIN question q ON p.question_idQuestion = q.idQuestion WHERE idQuestion = ?";
	private static final String SELECT_BY_ID_QUERY = "SELECT * FROM proposition p INNER JOIN question q ON p.question_idQuestion = q.idQuestion WHERE idProposition = ?";
	
	public static PropositionDAO getInstance() {
		if (singleton == null)
			singleton = new PropositionDAOImpl();
		
		return singleton;
	}
	
	@Override
	public ArrayList<Proposition> selectByIdQuestion(int id) throws DaoException {
		Proposition proposition = null;
		ArrayList<Proposition> propositions = new ArrayList<Proposition>();
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_BY_ID_QUESTION_QUERY);
			statement.setInt(1, id);
			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				proposition = map(resultSet);
				proposition.setQuestion(QuestionDAOImpl.map(resultSet));
				propositions.add(proposition);
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return propositions;
	}
	
	@Override
	public Proposition selectById(int id) throws DaoException {
		Proposition proposition = null;
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_BY_ID_QUERY);
			statement.setInt(1, id);
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				proposition = map(resultSet);
				proposition.setQuestion(QuestionDAOImpl.map(resultSet));
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return proposition;
	}
	
	public static Proposition map(ResultSet rst) throws SQLException {
		Proposition proposition = new Proposition();
		proposition.setId(rst.getInt("idProposition"));
		proposition.setEstBonne(rst.getBoolean("estBonne"));
		proposition.setEnonce(rst.getString("enonce"));
		
		return proposition;
	}
}
