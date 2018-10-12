package fr.eni.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.bo.Candidat;
import fr.eni.dal.dao.CandidatDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class CandidatDAOImpl implements CandidatDAO{
	
private static CandidatDAOImpl singleton;

	private static final String SELECT_BY_ID = "SELECT * FROM utilisateur WHERE idUtilisateur = ?";
	private static final String SELECT_BY_EMAIL_PASSWORD = " select * from utilisateur where email = ? and password = ? ";
	private static final String SELECT_BY_EMAIL = " select * from utilisateur where email = ?";
	private static final String INSERT_UTILISATEUR = "INSERT INTO utilisateur(nom,prenom,email,password,profil_codeProfil) VALUES (RTRIM(LTRIM(?)),RTRIM(LTRIM(?)),RTRIM(LTRIM(?)),?,?)";
	private static final String INSERT_CANDIDAT = "INSERT INTO candidat(idUtilisateur,codePromo) VALUES (?,?)";
	private static final String INSERT_COLLABO = "INSERT INTO collaborateur VALUES (?)";
	
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
				candidat = map(resultSet);
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return candidat;
	}

	@Override
	public Candidat selectByEmailPassword(String email, String password) throws DaoException {

		Candidat candidat = null;
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			statement = connexion.prepareStatement(SELECT_BY_EMAIL_PASSWORD);
			statement.setString(1, email);
			statement.setString(2, password);
			
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				candidat = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
		return candidat;
	}

	public static Candidat map(ResultSet resultSet) throws SQLException {
		Candidat candidat = new Candidat();
		
		candidat.setId(resultSet.getInt("idUtilisateur"));
		candidat.setEmail(resultSet.getString("email"));
		candidat.setNom(resultSet.getString("nom"));
		candidat.setPassword(resultSet.getString("password"));
		candidat.setPrenom(resultSet.getString("prenom"));

		return candidat;
	}

	@Override
	public void insertCandidat(Candidat candidat, int codePromo) throws DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			connexion.setAutoCommit(false);
			
			statement = connexion.prepareStatement(INSERT_CANDIDAT);
			statement.setInt(1, candidat.getId());
			statement.setInt(2, codePromo);

			statement.executeUpdate();
			
			connexion.commit();

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
	}

	@Override
	public Candidat insertUtilisateur(Candidat candidat) throws DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			connexion.setAutoCommit(false);
			
			statement = connexion.prepareStatement(INSERT_UTILISATEUR, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, candidat.getNom());
			statement.setString(2, candidat.getPrenom());
			statement.setString(3, candidat.getEmail());
			statement.setString(4, candidat.getPassword());
			statement.setInt(5, candidat.getProfil().getId());

			if (statement.executeUpdate() == 1) {
				resultSet = statement.getGeneratedKeys();
				
				if (resultSet.next()) {
					candidat.setId(resultSet.getInt(1));
				}
			}
			
			connexion.commit();

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return candidat;
	}
	
	@Override
	public void insertCollaborateur(Candidat candidat) throws DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			connexion.setAutoCommit(false);
			
			statement = connexion.prepareStatement(INSERT_COLLABO);
			statement.setInt(1, candidat.getId());

			statement.executeUpdate();
			
			connexion.commit();

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
	}

	@Override
	public Candidat selectByEmail(String email) throws DaoException {
		Candidat candidat = null;
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			statement = connexion.prepareStatement(SELECT_BY_EMAIL);
			statement.setString(1, email);
			
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				candidat = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
		return candidat;
	}


}
