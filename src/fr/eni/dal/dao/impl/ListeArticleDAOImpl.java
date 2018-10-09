package fr.eni.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.ListeArticle;
import fr.eni.dal.dao.ListeArticleDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class ListeArticleDAOImpl implements ListeArticleDAO {

	private static ListeArticleDAOImpl singleton;

	private static final String INSERT_LISTE_ARTICLE_QUERY = "INSERT INTO liste_article(nom) VALUES(RTRIM(LTRIM(?)))";
	private static final String DELETE_BY_ID_QUERY = "DELETE FROM liste_article WHERE id = ?";
	private static final String SELECT_ALL_QUERY = "SELECT nom FROM liste_article";
	private static final String SELECT_BY_NOM = "SELECT id, nom FROM liste_article WHERE nom = ?";
	
	public static ListeArticleDAO getInstance() {
		if (singleton == null)
			singleton = new ListeArticleDAOImpl();
		
		return singleton;
	}
	
	@Override
	public ListeArticle insert(ListeArticle element) throws DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(INSERT_LISTE_ARTICLE_QUERY, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, element.getNom());

			if (statement.executeUpdate() == 1) {
				resultSet = statement.getGeneratedKeys();
				
				if (resultSet.next()) {
					element.setId(resultSet.getInt(1));
				}
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return element;
	}

	@Override
	public List<ListeArticle> selectAll() throws DaoException {
		List<ListeArticle> listeArticles = new ArrayList<ListeArticle>();
		ListeArticle listeArticle = null;
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			statement = connexion.prepareStatement(SELECT_ALL_QUERY);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				listeArticle = new ListeArticle();
				listeArticle.setNom(resultSet.getString("nom"));
				listeArticles.add(listeArticle);
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return listeArticles;
	}

	@Override
	public void deleteById(int id) throws DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(DELETE_BY_ID_QUERY);
			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(statement, connexion);
		}
	}

	@Override
	public ListeArticle selectByNom(String nom) throws DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ListeArticle listeArticle = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_BY_NOM);
			statement.setString(1, nom);

			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				listeArticle = new ListeArticle();
				listeArticle.setId(resultSet.getInt("id"));
				listeArticle.setNom(resultSet.getString("nom"));
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return listeArticle;
	}
}
