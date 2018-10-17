package fr.eni.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eni.bo.SectionTest;
import fr.eni.dal.dao.SectionTestDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class SectionTestDAOImpl implements SectionTestDAO{
private static SectionTestDAOImpl singleton;
	
	private static final String SELECT_BY_ID_TEST_QUERY = "SELECT * FROM sectionTest st INNER JOIN test t ON st.test_idTest = t.idTest INNER JOIN theme thm ON st.theme_idTheme = thm.idTheme WHERE t.idTest = ?";
	private static final String SELECT_BY_ID_THEM = "SELECT nbQuestionATirer From sectionTest WHERE theme_idTheme = ?";
	
	public static SectionTestDAO getInstance() {
		if (singleton == null)
			singleton = new SectionTestDAOImpl();
		
		return singleton;
	}
	
	@Override
	public ArrayList<SectionTest> selectByIdTest(int id) throws DaoException {
		SectionTest sectionTest = null;
		ArrayList<SectionTest> liste = new ArrayList<SectionTest>();
		
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_BY_ID_TEST_QUERY);
			statement.setInt(1, id);
			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				sectionTest = map(resultSet);
				sectionTest.setTest(TestDAOImpl.map(resultSet));
				sectionTest.setTheme(ThemeDAOImpl.map(resultSet));
				liste.add(sectionTest);
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return liste;
	}

	public static SectionTest map(ResultSet resultSet) throws SQLException {
		SectionTest sectionTest = new SectionTest();
		sectionTest.setNbQuestionATirer(resultSet.getInt("nbQuestionATirer"));
		
		return sectionTest;
	}

	@Override
	public int nbQuestionATirerByThemeId(int idTheme) throws DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int rep = 0;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_BY_ID_THEM);
			statement.setInt(1, idTheme);
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				rep = resultSet.getInt("nbQuestionATirer");
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return rep;
	}
}
