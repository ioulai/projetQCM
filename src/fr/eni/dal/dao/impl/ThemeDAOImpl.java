package fr.eni.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eni.bo.Theme;
import fr.eni.dal.dao.ThemeDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class ThemeDAOImpl implements ThemeDAO{
private static ThemeDAOImpl singleton;

	private static final String SELECT_ALL = "SELECT * FROM theme";
	private static final String SELECT_BY_ID_EPREUVE = "SELECT * FROM theme INNER JOIN sectionTest ON idTheme = theme_idTheme INNER JOIN test ON test_idTest = idTest WHERE idTest = ?";
	
	public static ThemeDAO getInstance() {
		if (singleton == null)
			singleton = new ThemeDAOImpl();
		
		return singleton;
	}

	@Override
	public ArrayList<Theme> selectAll() throws DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ArrayList<Theme> themes = new ArrayList<Theme>();
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_ALL);
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				themes.add(map(resultSet));
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return themes;
	}

	public static Theme map(ResultSet resultSet) throws SQLException {
		Theme theme = new Theme();
		theme.setId(resultSet.getInt("idTheme"));
		theme.setLibelle(resultSet.getString("libelle"));
		
		return theme;
	}

	@Override
	public ArrayList<Theme> selectByIdTest(int idTest) throws DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ArrayList<Theme> themes = new ArrayList<Theme>();
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_BY_ID_EPREUVE);
			statement.setInt(1, idTest);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				themes.add(map(resultSet));
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return themes;
	}
}
