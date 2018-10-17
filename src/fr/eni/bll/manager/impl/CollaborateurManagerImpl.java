package fr.eni.bll.manager.impl;

import java.util.ArrayList;
import java.util.List;

import fr.eni.bll.manager.CollaborateurManager;
import fr.eni.bo.Candidat;
import fr.eni.bo.Collaborateur;
import fr.eni.dal.dao.CollaborateurDAO;
import fr.eni.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;

public class CollaborateurManagerImpl implements CollaborateurManager {
	private static CollaborateurManager singleton = null;
	private CollaborateurDAO collaborateurDAO = DAOFactory.collaborateurDao();

	public static CollaborateurManager getInstance() {
		if (singleton == null) {
			singleton = new CollaborateurManagerImpl();
		}
		return singleton;

	}


	@Override
	public Collaborateur selectByEmailPassword(String email, String password) throws  ManagerException {
		Collaborateur collaborateur = null;
		try {
			collaborateur = collaborateurDAO.selectByEmailPassword(email, password);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}

		return collaborateur;
	}


	@Override
	public Collaborateur selectById(int id) throws ManagerException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Collaborateur selectByEmail(String email) throws ManagerException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Collaborateur> selectAllCollaborateur() throws ManagerException {
List<Collaborateur> liste = new ArrayList<Collaborateur>();
		
		try {
			liste = collaborateurDAO.selectAllCollaborateur();
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
	
		return liste;
	}


	@Override
	public void insertCollaborateur(Candidat candidat) throws ManagerException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteCollaborateur(int id) throws ManagerException {
		// TODO Auto-generated method stub
		
	}


}
