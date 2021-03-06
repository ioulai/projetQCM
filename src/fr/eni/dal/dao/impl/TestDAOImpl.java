package fr.eni.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Test;
import fr.eni.dal.dao.TestDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class TestDAOImpl implements TestDAO{
	
private static TestDAOImpl singleton;

	private static final String SELECT_BY_ID = "SELECT * FROM test WHERE idTest = ?";
	private static final String SELECT_ALL = "SELECT * FROM test";
	private static final String DELETE_QUERY_BY_ID = "DELETE FROM test WHERE idTest = ?";
	
	public static TestDAO getInstance() {
		if (singleton == null)
			singleton = new TestDAOImpl();
		
		return singleton;
	}

	@Override
	public Test selectById(int id) throws DaoException {
		Test test = null;
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_BY_ID);
			statement.setInt(1, id);
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				test = map(resultSet);
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return test;
	}

	public static Test map(ResultSet resultSet) throws SQLException {
		Test test = new Test();
		test.setDescription(resultSet.getString("description"));
		test.setDuree(resultSet.getTime("duree"));
		test.setId(resultSet.getInt("idTest"));
		test.setLibelle(resultSet.getString("libelle"));
		test.setSeuilBas(resultSet.getInt("seuilBas"));
		test.setSeuilHaut(resultSet.getInt("seuilHaut"));
		
		return test;
	}

	@Override
	public List<Test> selectAll() throws DaoException {
		Test test = null;
		List<Test> tests = new ArrayList<Test>();
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_ALL);
			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				test = new Test();
				test = map(resultSet);
				
				tests.add(test);
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return tests;
	}

	@Override
	public void deleteById(int id) throws DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
	
			statement = connexion.prepareStatement(DELETE_QUERY_BY_ID);
			statement.setInt(1, id);
			
			statement.execute();
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(statement, connexion);
		}
		
	}
	
}
