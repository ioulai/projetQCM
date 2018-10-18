package fr.eni.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Candidat;
import fr.eni.bo.Collaborateur;
import fr.eni.dal.dao.CollaborateurDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class CollaborateurDAOImpl implements CollaborateurDAO {
	private static final String SELECT_ALL_COLLABORATEUR = "SELECT * FROM utilisateur as util INNER JOIN collaborateur as co ON util.idUtilisateur = co.idUtilisateur";
	private static final String SELECT_BY_EMAIL_PASSWORD = " select * from utilisateur as util INNER JOIN collaborateur as ca ON util.idUtilisateur = ca.idUtilisateur  where email = ? and password = ? ";

	private static CollaborateurDAOImpl singleton;

	public static CollaborateurDAOImpl getInstance() {
		if (singleton == null) {
			singleton = new CollaborateurDAOImpl();
		}
		return singleton;
	}

	@Override
	public Collaborateur selectById(int id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collaborateur selectByEmailPassword(String email, String password) throws DaoException {
		Collaborateur collaborateur = null;
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
				collaborateur = map(resultSet);

			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
		return collaborateur;
	}

	@Override
	public Collaborateur selectByEmail(String email) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Collaborateur> selectAllCollaborateur() throws DaoException {
		Collaborateur collaborateur = null;
		List<Collaborateur> collaborateurs = new ArrayList<Collaborateur>();
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connexion = MSSQLConnectionFactory.get();

			statement = connexion.prepareStatement(SELECT_ALL_COLLABORATEUR);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				collaborateur = new Collaborateur();
				collaborateur = map(resultSet);

				collaborateurs.add(collaborateur);
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}

		return collaborateurs;
	}

	@Override
	public void insertCollaborateur(Candidat candidat) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCollaborateur(int id) throws DaoException {
		// TODO Auto-generated method stub

	}

	public static Collaborateur map(ResultSet resultSet) throws SQLException {
		Collaborateur collaborateur = new Collaborateur();
		collaborateur.setId(resultSet.getInt("idUtilisateur"));
		collaborateur.setEmail(resultSet.getString("email"));
		collaborateur.setNom(resultSet.getString("nom"));
		collaborateur.setPassword(resultSet.getString("password"));
		collaborateur.setPrenom(resultSet.getString("prenom"));
		collaborateur.setProfil(resultSet.getInt("profil_codeProfil"));
		return collaborateur;
	}
}
