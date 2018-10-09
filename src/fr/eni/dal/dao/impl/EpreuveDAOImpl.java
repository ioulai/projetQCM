package fr.eni.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Article;
import fr.eni.bo.ListeArticle;
import fr.eni.dal.dao.ArticleDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class EpreuveDAOImpl {
private static ArticleDAOImpl singleton;
	
	private static final String SELECT_BY_ID_QUERY = "SELECT id, nom, selected FROM article WHERE id = ?";
	private static final String SELECT_BY_ID_LISTE_QUERY = "SELECT id, nom, selected FROM article WHERE liste_article_id = ?";
	private static final String INSERT_ARTICLE_QUERY = "INSERT INTO article(nom, selected, liste_article_id) VALUES(RTRIM(LTRIM(?)), ?, ?)";
	private static final String DELETE_BY_ID_LISTE_QUERY = "DELETE FROM article WHERE liste_article_id = ?";
	private static final String DELETE_BY_ID_QUERY = "DELETE FROM article WHERE id = ?";
	private static final String UPDATE_SELECTED_QUERY = "UPDATE article SET selected = ? WHERE id = ?";
	
	public static ArticleDAO getInstance() {
		if (singleton == null)
			singleton = new ArticleDAOImpl();
		
		return singleton;
	}
	
	@Override
	public Article insert(Article element) throws DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			connexion.setAutoCommit(false);
			
			statement = connexion.prepareStatement(INSERT_ARTICLE_QUERY, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, element.getNom());
			statement.setBoolean(2, false);
			statement.setInt(3, element.getListeArticle().getId());

			if (statement.executeUpdate() == 1) {
				resultSet = statement.getGeneratedKeys();
				
				if (resultSet.next()) {
					element.setId(resultSet.getInt(1));
				}
			}
			
			connexion.commit();

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return element;
	}

	@Override
	public void delete(Integer id) throws DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(DELETE_BY_ID_QUERY);
			statement.setInt(1, id);

			statement.executeUpdate();
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion, statement2);
		}
	}

	@Override
	public List<Article> selectByIdListe(ListeArticle liste) throws DaoException {
		Article article = null;
		List<Article> articles = new ArrayList<Article>();
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_BY_ID_LISTE_QUERY);
			statement.setInt(1, liste.getId());
			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				article = new Article();
				article.setId(resultSet.getInt("id"));
				article.setNom(resultSet.getString("nom"));
				article.setSelected(resultSet.getBoolean("selected"));
				article.setListeArticle(liste);
				articles.add(article);
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return articles;
	}

	@Override
	public void deleteByIdListe(int id) throws DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(DELETE_BY_ID_LISTE_QUERY);
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
	public Article selectById(int id, ListeArticle liste) throws DaoException {
		Article article = null;
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_BY_ID_QUERY);
			statement.setInt(1, id);
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				article = new Article();
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
	public Article updateSelected(Article article) throws DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(UPDATE_SELECTED_QUERY);
			statement.setBoolean(1, article.isSelected());
			statement.setInt(2, article.getId());
			statement.executeUpdate();
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return article;
	}
}
