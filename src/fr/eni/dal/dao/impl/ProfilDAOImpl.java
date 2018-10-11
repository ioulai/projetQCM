package fr.eni.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Profil;
import fr.eni.dal.dao.ProfilDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class ProfilDAOImpl implements ProfilDAO{
private static ProfilDAOImpl singleton;
	
	private static final String SELECT_ALL = "SELECT * FROM profil";
	private static final String SELECT_BY_NAME = "SELECT * FROM profil WHERE libelle = ?";
	
	public static ProfilDAO getInstance() {
		if (singleton == null)
			singleton = new ProfilDAOImpl();
		
		return singleton;
	}

	@Override
	public List<Profil> selectAll() throws DaoException {
		Profil prof = null;
		List<Profil> profils = new ArrayList<Profil>();
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_ALL);
			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				prof = new Profil();
				prof = map(resultSet);
				
				profils.add(prof);
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return profils;
	}

	public static Profil map(ResultSet resultSet) throws DaoException {
		Profil profil = null;

		try {
			profil = new Profil();
			profil.setId(resultSet.getInt("codeProfil"));
			profil.setLibelle(resultSet.getString("libelle"));
			
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}

		return profil;
	}

	@Override
	public Profil selectByName(String name) throws DaoException {
		Profil profil = null;
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_BY_NAME);
			statement.setString(1, name);
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				profil = map(resultSet);
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return profil;
	}
}
