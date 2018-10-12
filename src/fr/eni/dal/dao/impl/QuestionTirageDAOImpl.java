package fr.eni.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.eni.bo.Epreuve;
import fr.eni.bo.Question;
import fr.eni.bo.QuestionTirage;
import fr.eni.dal.dao.QuestionTirageDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class QuestionTirageDAOImpl implements QuestionTirageDAO{
private static QuestionTirageDAOImpl singleton;
	
	private static final String INSERT_QUERY = "INSERT INTO questionTirage VALUES (?, ?, ?, ?)";
	private static final String SELECT_BY_ID_EPREUVE_QUERY = "SELECT * FROM questionTirage qt INNER JOIN epreuve e ON e.idEpreuve = qt.epreuve_idEpreuve INNER JOIN question q ON q.idQuestion = qt.question_idQuestion WHERE epreuve_idEpreuve = ? ORDER BY numOrdre ASC";
	private static final String SELECT_BY_IDS_QUERY = "SELECT * FROM questionTirage qt INNER JOIN epreuve e ON e.idEpreuve = qt.epreuve_idEpreuve INNER JOIN question q ON q.idQuestion = qt.question_idQuestion WHERE epreuve_idEpreuve = ? AND question_idQuestion = ?";
	private static final String UPDATE_QUERY = "UPDATE questionTirage SET estMarquee = ?, numOrdre = ? WHERE epreuve_idEpreuve = ? AND question_idQuestion = ?";
	
	public static QuestionTirageDAO getInstance() {
		if (singleton == null)
			singleton = new QuestionTirageDAOImpl();
		
		return singleton;
	}
	
	@Override
	public void insert(QuestionTirage questionTirage) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            
            statement.setBoolean(1, questionTirage.isEstMarquee());
            statement.setInt(2, questionTirage.getNumOrdre());
            statement.setInt(3, questionTirage.getEpreuve().getIdEpreuve());
            statement.setInt(4, questionTirage.getQuestion().getId());
            
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
	}
	
	@Override
	public ArrayList<QuestionTirage> selectByIdEpreuve(int id) throws DaoException {
		QuestionTirage questionTirage = null;
		ArrayList<QuestionTirage> questions = new ArrayList<QuestionTirage>();
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_BY_ID_EPREUVE_QUERY);
			statement.setInt(1, id);
			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				questionTirage = map(resultSet);
				questionTirage.setEpreuve(EpreuveDAOImpl.map(resultSet));
				questionTirage.setQuestion(QuestionDAOImpl.map(resultSet));
				questions.add(questionTirage);
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return questions;
	}
	
	@Override
	public QuestionTirage selectByIds(Epreuve epreuve, Question questionEnCours) throws DaoException {
		QuestionTirage questionTirage = null;
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_BY_IDS_QUERY);
			statement.setInt(1, epreuve.getIdEpreuve());
			statement.setInt(2, questionEnCours.getId());
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				questionTirage = map(resultSet);
				questionTirage.setEpreuve(EpreuveDAOImpl.map(resultSet));
				questionTirage.setQuestion(QuestionDAOImpl.map(resultSet));
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return questionTirage;
	}
	
	@Override
	public void update(QuestionTirage questionTirage) throws DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(UPDATE_QUERY);
			statement.setBoolean(1, questionTirage.isEstMarquee());
			statement.setInt(2, questionTirage.getNumOrdre());
			statement.setInt(3, questionTirage.getEpreuve().getIdEpreuve());
			statement.setInt(4, questionTirage.getQuestion().getId());
			
			statement.execute();
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
	}
	
	public static QuestionTirage map(ResultSet rst) throws SQLException {
		QuestionTirage questionTirage = new QuestionTirage();
		questionTirage.setEstMarquee(rst.getBoolean("estMarquee"));
		questionTirage.setNumOrdre(rst.getInt("numOrdre"));
		
		return questionTirage;
	}
}
