package fr.eni.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.bo.Candidat;
import fr.eni.dal.dao.CandidatDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class CandidatDAOImpl implements CandidatDAO{
	
private static CandidatDAOImpl singleton;

	private static final String SELECT_BY_ID = "SELECT * FROM utilisateur WHERE idUtilisateur = ?";
	
	public static CandidatDAO getInstance() {
		if (singleton == null)
			singleton = new CandidatDAOImpl();
		
		return singleton;
	}

	@Override
	public Candidat selectById(int id) throws DaoException {
		Candidat candidat = null;
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_BY_ID);
			statement.setInt(1, id);
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				candidat = setCandidat(resultSet);
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return candidat;
	}

	public static Candidat setCandidat(ResultSet resultSet) throws DaoException {
		Candidat candidat = null;
		
		try {
			candidat = new Candidat();
			candidat.setEmail(resultSet.getString("email"));
			candidat.setNom(resultSet.getString("nom"));
			candidat.setPassword(resultSet.getString("password"));
			candidat.setPrenom(resultSet.getString("prenom"));
			
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}

		return candidat;
	}
}
