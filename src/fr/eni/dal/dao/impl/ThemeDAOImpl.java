package fr.eni.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Epreuve;
import fr.eni.dal.dao.EpreuveDAO;
import fr.eni.dal.dao.ThemeDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class ThemeDAOImpl implements ThemeDAO{
private static ThemeDAOImpl singleton;

	private static final String SELECT_ALL = "SELECT * FROM epreuve";
	
	public static ThemeDAO getInstance() {
		if (singleton == null)
			singleton = new ThemeDAOImpl();
		
		return singleton;
	}

	@Override
	public Epreuve selectAll() throws DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_BY_ID_QUERY);
			statement.setInt(1, id);
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				epr = new Epreuve();
				epr.setIdEpreuve(resultSet.getInt("idEpreuve"));
				epr.setDateDebutValidite(resultSet.getDate("dateDebutValidite"));
				epr.setDateFinValidite(resultSet.getDate("dateDebutValidite"));
				epr.setEtat(resultSet.getString("etat"));
				epr.setNiveauObtenu(resultSet.getString("niveauObtenu"));
				epr.setNoteObtenue(resultSet.getInt("noteObtenue"));
				epr.setTempsEcoule(resultSet.getTime("tempsEcoule"));
				ep
				
				
				article.setId(resultSet.getInt("id"));
				article.setNom(resultSet.getString("nom"));
				article.setSelected(resultSet.getBoolean("selected"));
				article.setListeArticle(liste);
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return article;
	}

	@Override
	public List<Epreuve> selectAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
