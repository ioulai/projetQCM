package fr.eni.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Epreuve;
import fr.eni.dal.dao.EpreuveDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class EpreuveDAOImpl implements EpreuveDAO{
private static EpreuveDAOImpl singleton;
	
	private static final String SELECT_BY_ID_QUERY = "SELECT * FROM epreuve WHERE idEpreuve = ?";
	private static final String SELECT_ALL = "SELECT * FROM epreuve";
	
	public static EpreuveDAO getInstance() {
		if (singleton == null)
			singleton = new EpreuveDAOImpl();
		
		return singleton;
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
	public Epreuve selectById(int id, Epreuve epreuve) throws DaoException {
		Epreuve epr = null;
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
