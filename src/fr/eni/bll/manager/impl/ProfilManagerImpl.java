package fr.eni.bll.manager.impl;

import java.util.ArrayList;
import java.util.List;

import fr.eni.bll.manager.ProfilManager;
import fr.eni.bo.Profil;
import fr.eni.dal.dao.ProfilDAO;
import fr.eni.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;

public class ProfilManagerImpl implements ProfilManager{

	private static ProfilManager singleton = null;
	private ProfilDAO profilDAO = DAOFactory.ProfilDAO();

	public static ProfilManager getInstance() {
		if (singleton == null) {
			singleton = new ProfilManagerImpl();
		}
		return singleton;

	}
	@Override
	public List<Profil> selectAll() throws ManagerException {
		List<Profil> liste = new ArrayList<Profil>();
		
		try {
			liste = profilDAO.selectAll();
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
	
		return liste;
	}
	@Override
	public Profil selectByName(String name) throws ManagerException {
		Profil profil = null;
		
		try {
			profil = profilDAO.selectByName(name);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return profil;
	}

}
