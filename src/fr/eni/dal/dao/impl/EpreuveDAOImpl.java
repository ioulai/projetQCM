package fr.eni.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Epreuve;
import fr.eni.dal.dao.EpreuveDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class EpreuveDAOImpl implements EpreuveDAO{
private static EpreuveDAOImpl singleton;
	
	private static final String SELECT_BY_ID_QUERY = "SELECT * FROM epreuve INNER JOIN Candidat as ca ON idUtilisateur = utilisateur_idUtilisateur INNER JOIN test ON test_idTest = idTest INNER JOIN utilisateur as util ON ca.idUtilisateur = util.idUtilisateur WHERE idEpreuve = ?";
	private static final String SELECT_BY_ID_TEST_QUERY = "SELECT * FROM epreuve INNER JOIN test ON test_idTest = idTest WHERE idTest = ?";
	private static final String SELECT_ALL = "SELECT * FROM epreuve INNER JOIN Candidat as ca ON idUtilisateur = utilisateur_idUtilisateur INNER JOIN test ON test_idTest = idTest INNER JOIN utilisateur as util ON ca.idUtilisateur = util.idUtilisateur";
	
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
				epr = map(resultSet);
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
	public Epreuve selectByIdTest(int id) throws DaoException {
		Epreuve epr = null;
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_BY_ID_TEST_QUERY);
			statement.setInt(1, id);
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				epr = map(resultSet);
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
				epr = map(resultSet);
				
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

	public static Epreuve map(ResultSet resultSet) throws DaoException {
		Epreuve epr = null;

		try {
			epr = new Epreuve();
			epr.setIdEpreuve(resultSet.getInt("idEpreuve"));
			epr.setDateDebutValidite(resultSet.getDate("dateDebutValidite"));
			epr.setDateFinValidite(resultSet.getDate("dateDebutValidite"));
			epr.setEtat(resultSet.getString("etat"));
			epr.setNiveauObtenu(resultSet.getString("niveauObtenu"));
			epr.setNoteObtenue(resultSet.getInt("noteObtenue"));
			epr.setTempsEcoule(resultSet.getTime("tempsEcoule"));
			
			epr.setTest(TestDAOImpl.map(resultSet));
			epr.setCandidat(CandidatDAOImpl.map(resultSet));
			
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}

		return epr;
	}
}
