package fr.eni.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.eni.bo.ReponseTirage;
import fr.eni.dal.dao.ReponseTirageDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class ReponseTirageDAOImpl implements ReponseTirageDAO{
private static ReponseTirageDAOImpl singleton;
	
	private static final String INSERT_QUERY = "INSERT INTO reponseTirage VALUES (?, ?, ?)";
	private static final String SELECT_BY_ALL_QUERY = "SELECT * FROM reponseTirage rt INNER JOIN proposition p ON p.idProposition = rt.proposition_idProposition INNER JOIN question q ON q.idQuestion = rt.proposition_question_idQuestion INNER JOIN epreuve e ON e.idEpreuve = rt.questionTirage_epreuve_idEpreuve WHERE questionTirage_epreuve_idEpreuve = ? AND proposition_question_idQuestion = ?";
	private static final String UPDATE_QUERY = "UPDATE reponseTirage SET proposition_idProposition = ? WHERE questionTirage_epreuve_idEpreuve = ? AND proposition_question_idQuestion = ?";
	private static final String DELETE_QUERY = "DELETE FROM reponseTirage";
	private static final String DELETE_BY_IDS_QUERY = "DELETE FROM reponseTirage WHERE questionTirage_epreuve_idEpreuve = ? AND proposition_question_idQuestion = ?";
	
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
	public ArrayList<ReponseTirage> selectByAll(int idEpreuve, int idQuestion) throws DaoException {
		ReponseTirage reponseTirage = null;
		ArrayList<ReponseTirage> liste = new ArrayList<ReponseTirage>();
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_BY_ALL_QUERY);
			statement.setInt(1, idEpreuve);
			statement.setInt(2, idQuestion);
			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				reponseTirage = new ReponseTirage();
				reponseTirage.setEpreuve(EpreuveDAOImpl.map(resultSet));
				reponseTirage.setQuestion(QuestionDAOImpl.map(resultSet));
				reponseTirage.setProposition(PropositionDAOImpl.map(resultSet));
				liste.add(reponseTirage);
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return liste;
	}

	@Override
	public void update(ReponseTirage reponseTirage) throws DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(UPDATE_QUERY);
			statement.setInt(1, reponseTirage.getProposition().getId());
			statement.setInt(2, reponseTirage.getEpreuve().getIdEpreuve());
			statement.setInt(3, reponseTirage.getQuestion().getId());
			
			statement.execute();
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(statement, connexion);
		}
	}

	@Override
	public void deleteAll() throws DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(DELETE_QUERY);
			statement.execute();
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(statement, connexion);
		}
	}

	@Override
	public void deleteByIds(int idEpreuve, int idQuestion) throws DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(DELETE_BY_IDS_QUERY);
			statement.setInt(1, idEpreuve);
			statement.setInt(2, idQuestion);
			statement.execute();
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(statement, connexion);
		}
	}
}
