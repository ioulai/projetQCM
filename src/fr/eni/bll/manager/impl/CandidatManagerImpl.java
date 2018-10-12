package fr.eni.bll.manager.impl;

import java.util.ArrayList;
import java.util.List;

import fr.eni.bll.manager.CandidatManager;
import fr.eni.bo.Candidat;
import fr.eni.bo.Promotion;
import fr.eni.dal.dao.CandidatDAO;
import fr.eni.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.util.ValidationUtil;

public class CandidatManagerImpl implements CandidatManager {

	private static CandidatManager singleton = null;
	private CandidatDAO candidatDAO = DAOFactory.candidatDAO();

	public static CandidatManager getInstance() {
		if (singleton == null) {
			singleton = new CandidatManagerImpl();
		}
		return singleton;

	}

	@Override
	public Candidat selectByEmailPassword(String email, String password) throws ManagerException {
		Candidat candidat = null;

		try {
			candidat = candidatDAO.selectByEmailPassword(email, password);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		return candidat;
	}

	@Override
	public void insertCandidat(Candidat candidat, int codePromo) throws ManagerException {
		ValidationUtil.checkNotNull(candidat);
		try {
			candidatDAO.insertCandidat(candidat,codePromo);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
	}

	@Override
	public Candidat insertUtilisateur(Candidat candidat) throws ManagerException {
		ValidationUtil.checkNotNull(candidat);
		
		Candidat cand = null;
		
		try {
			cand = candidatDAO.insertUtilisateur(candidat);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return cand;
	}

	@Override
	public void insertCollaborateur(Candidat candidat) throws ManagerException {
		ValidationUtil.checkNotNull(candidat);
		try {
			candidatDAO.insertCollaborateur(candidat);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
	}

	@Override
	public Candidat selectByEmail(String email) throws ManagerException {
		Candidat candidat = null;

		try {
			candidat = candidatDAO.selectByEmail(email);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		return candidat;
	}

	@Override
	public List<Candidat> selectAllCandidat() throws ManagerException {
		List<Candidat> liste = new ArrayList<Candidat>();
		
		try {
			liste = candidatDAO.selectAllCandidat();
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
	
		return liste;
	}

}
