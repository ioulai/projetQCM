package fr.eni.bll.manager.impl;

import java.util.ArrayList;

import fr.eni.bll.manager.PropositionManager;
import fr.eni.bo.Proposition;
import fr.eni.dal.dao.PropositionDAO;
import fr.eni.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;

public class PropositionManagerImpl implements PropositionManager {
	private static PropositionManager singleton = null;
	private PropositionDAO propositionDAO = DAOFactory.propositionDAO();
	
	public static PropositionManager getInstance() {
		if (singleton == null)
			singleton = new PropositionManagerImpl();
		
		return singleton;
	}
	
	@Override
	public ArrayList<Proposition> selectByIdQuestion(int id) throws ManagerException {
		ArrayList<Proposition> propositions = new ArrayList<Proposition>();
		
		try {
			propositions = propositionDAO.selectByIdQuestion(id);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return propositions;
	}

	@Override
	public Proposition selectById(int id) throws ManagerException {
		Proposition proposition;
		
		try {
			proposition = propositionDAO.selectById(id);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return proposition;
	}
}
