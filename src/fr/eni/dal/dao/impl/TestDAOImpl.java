package fr.eni.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.bo.Test;
import fr.eni.dal.dao.TestDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class TestDAOImpl implements TestDAO{
	
private static TestDAOImpl singleton;

	private static final String SELECT_BY_ID = "SELECT * FROM test WHERE idTest = ?";
	
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
		test.setSeuilBas(resultSet.getString("seuilBas"));
		test.setSeuilHaut(resultSet.getString("seuilHaut"));
		
		return test;
	}
	
}
