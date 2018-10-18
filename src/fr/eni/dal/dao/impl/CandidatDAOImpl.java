package fr.eni.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Candidat;
import fr.eni.bo.Utilisateur;
import fr.eni.dal.dao.CandidatDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class CandidatDAOImpl implements CandidatDAO{
	
private static CandidatDAOImpl singleton;

	private static final String SELECT_BY_ID = "SELECT * FROM utilisateur WHERE idUtilisateur = ?";
	private static final String SELECT_BY_EMAIL_PASSWORD = " select * from utilisateur as util INNER JOIN candidat as ca ON util.idUtilisateur = ca.idUtilisateur join promotion p on p.codePromo=ca.codePromo  where email = ? and password = ? ";
	private static final String SELECT_BY_EMAIL = " select * from utilisateur where email = ?";
	private static final String INSERT_UTILISATEUR = "INSERT INTO utilisateur(nom,prenom,email,password,profil_codeProfil) VALUES (RTRIM(LTRIM(?)),RTRIM(LTRIM(?)),RTRIM(LTRIM(?)),?,?)";
	private static final String INSERT_CANDIDAT = "INSERT INTO candidat(idUtilisateur,codePromo) VALUES (?,?)";
	private static final String INSERT_COLLABO = "INSERT INTO collaborateur VALUES (?)";
	private static final String SELECT_ALL_CANDIDAT = "SELECT * FROM utilisateur as util INNER JOIN Candidat as ca ON util.idUtilisateur = ca.idUtilisateur";
	private static final String SELECT_ALL_COLLABORATEUR = "SELECT * FROM utilisateur as util INNER JOIN collaborateur as co ON util.idUtilisateur = co.idUtilisateur";
	private static final String DELETE_COLLABORATEUR_BY_ID = "DELETE FROM collaborateur WHERE idUtilisateur = ?";
	private static final String DELETE_UTILISATEUR_BY_ID = "DELETE FROM utilisateur WHERE idUtilisateur = ?";
	private static final String SELECT_ALL = " SELECT * FROM Utilisateur";
	
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
				candidat.setCodepromo(resultSet.getInt("codePromo"));
				
				
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
		candidat.setProfil(resultSet.getInt("profil_codeProfil"));
		

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
			statement.setInt(5, candidat.getProfil());

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

	@Override
	public List<Candidat> selectAllCandidat() throws DaoException {
		Candidat candidat = null;
		List<Candidat> candidats = new ArrayList<Candidat>();
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_ALL_CANDIDAT);
			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				candidat = new Candidat();
				candidat = map(resultSet);
				candidat.setCodepromo(resultSet.getInt("codePromo"));
				
				candidats.add(candidat);
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return candidats;
	}

	@Override
	public List<Candidat> selectAllCollaborateur() throws DaoException {
		Candidat candidat = null;
		List<Candidat> candidats = new ArrayList<Candidat>();
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_ALL_COLLABORATEUR);
			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				candidat = new Candidat();
				candidat = map(resultSet);
				
				candidats.add(candidat);
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return candidats;
	}

	@Override
	public void deleteCollaborateur(int id) throws DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
	
			statement = connexion.prepareStatement(DELETE_COLLABORATEUR_BY_ID);
			statement.setInt(1, id);
			
			statement.execute();
			
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(statement, connexion);
		}
		this.deleteUtilisateur(id);
	}

	@Override
	public void deleteUtilisateur(int id) throws DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
	
			statement = connexion.prepareStatement(DELETE_UTILISATEUR_BY_ID);
			statement.setInt(1, id);
			
			statement.execute();
			
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(statement, connexion);
		}
		
	}
	
	@Override
	public List<Utilisateur> selectAll() throws DaoException {
		Utilisateur utilisateur = null;
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_ALL);
			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				utilisateur = map(resultSet);
				
				utilisateurs.add(utilisateur);
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return utilisateurs;
	}
		
	}

