package fr.eni.bll.manager.impl;

import fr.eni.bll.manager.CandidatManager;
import fr.eni.bo.Candidat;
import fr.eni.dal.dao.CandidatDAO;
import fr.eni.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;

public class CandidatManagerImpl implements CandidatManager {

	public CandidatManagerImpl() {

	}

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

}
