package fr.eni.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.bo.Proposition;
import fr.eni.bo.QuestionTirage;
import fr.eni.bo.ReponseTirage;
import fr.eni.dal.dao.ReponseTirageDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class ReponseTirageDAOImpl implements ReponseTirageDAO{
private static ReponseTirageDAOImpl singleton;
	
	private static final String INSERT_QUERY = "INSERT INTO reponseTirage VALUES (?, ?, ?)";
	private static final String SELECT_BY_ALL_QUERY = "SELECT * FROM reponseTirage WHERE questionTirage_epreuve_idEpreuve = ? AND proposition_idProposition = ? AND proposition_question_idQuestion = ?";
	
	public static ReponseTirageDAO getInstance() {
		if (singleton == null)
			singleton = new ReponseTirageDAOImpl();
		
		return singleton;
	}
	
	@Override
	public void insert(ReponseTirage reponseTirage) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            
            statement.setInt(1, reponseTirage.getEpreuve().getIdEpreuve());
            statement.setInt(2, reponseTirage.getProposition().getId());
            statement.setInt(3, reponseTirage.getQuestion().getId());
            
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
	}

	@Override
	public ReponseTirage selectByAll(QuestionTirage questionTirage, Proposition proposition) throws DaoException {
		ReponseTirage reponseTirage = null;
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_BY_ALL_QUERY);
			statement.setInt(1, questionTirage.getEpreuve().getIdEpreuve());
			statement.setInt(2, proposition.getId());
			statement.setInt(3, questionTirage.getQuestion().getId());
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				reponseTirage = new ReponseTirage();
				reponseTirage.setEpreuve(questionTirage.getEpreuve());
				reponseTirage.setQuestion(questionTirage.getQuestion());
				reponseTirage.setProposition(proposition);
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return reponseTirage;
	}
}
