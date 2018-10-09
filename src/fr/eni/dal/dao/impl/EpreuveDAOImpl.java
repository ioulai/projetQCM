package fr.eni.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Candidat;
import fr.eni.bo.Epreuve;
import fr.eni.bo.Test;
import fr.eni.dal.dao.EpreuveDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class EpreuveDAOImpl implements EpreuveDAO{
private static EpreuveDAOImpl singleton;
	
	private static final String SELECT_BY_ID_QUERY = "SELECT * FROM epreuve INNER JOIN Candidat ON idUtilisateur = utilisateur_idUtilisateur INNER JOIN test ON test_idTest = idTest WHERE idEpreuve = ?";
	private static final String SELECT_ALL = "SELECT * FROM epreuve INNER JOIN Candidat ON idUtilisateur = utilisateur_idUtilisateur INNER JOIN test ON test_idTest = idTest";
	
	public static EpreuveDAO getInstance() {
		if (singleton == null)
			singleton = new EpreuveDAOImpl();
		
		return singleton;
	}
	
	@Override
	public Epreuve selectById(int id) throws DaoException {
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
				epr = setEpreuve(resultSet);
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return epr;
	}

	@Override
	public List<Epreuve> selectAll() throws DaoException {
		Epreuve epr = null;
		List<Epreuve> epreuves = new ArrayList<Epreuve>();
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_ALL);
			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				epr = new Epreuve();
				epr = setEpreuve(resultSet);
				
				epreuves.add(epr);
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return epreuves;
	}

	public static Epreuve setEpreuve(ResultSet resultSet) throws DaoException {
		Epreuve epr = null;
		Candidat candidat = null;
		Test test = null;
		try {
			epr = new Epreuve();
			epr.setIdEpreuve(resultSet.getInt("idEpreuve"));
			epr.setDateDebutValidite(resultSet.getDate("dateDebutValidite"));
			epr.setDateFinValidite(resultSet.getDate("dateDebutValidite"));
			epr.setEtat(resultSet.getString("etat"));
			epr.setNiveauObtenu(resultSet.getString("niveauObtenu"));
			epr.setNoteObtenue(resultSet.getInt("noteObtenue"));
			epr.setTempsEcoule(resultSet.getTime("tempsEcoule"));
			
			candidat = new Candidat();
			candidat.setEmail(resultSet.getString("email"));
			candidat.setNom(resultSet.getString("nom"));
			candidat.setPassword(resultSet.getString("password"));
			candidat.setPrenom(resultSet.getString("prenom"));
			
			test = new Test();
			test.setDescription(resultSet.getString("description"));
			test.setDuree(resultSet.getTime("duree"));
			test.setId(resultSet.getInt("idTest"));
			test.setLibelle(resultSet.getString("libelle"));
			test.setSeuilBas(resultSet.getString("seuilBas"));
			test.setSeuilHaut(resultSet.getString("seuilHaut"));
			
			epr.setTest(test);
			epr.setCandidat(candidat);
			
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}

		return epr;
	}
}
