package fr.eni.bll.manager.impl;

import java.util.ArrayList;
import java.util.List;

import fr.eni.bll.manager.EpreuveManager;

import fr.eni.bo.Epreuve;
import fr.eni.dal.dao.EpreuveDAO;
import fr.eni.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;

public class EpreuveManagerImpl implements EpreuveManager{

	private static EpreuveManager singleton = null;
	private EpreuveDAO epreuveDAO = DAOFactory.EpreuveDAO();
	
	public static EpreuveManager getInstance() {
		if (singleton == null)
			singleton = new EpreuveManagerImpl();
		
		return singleton;
	}
	
	@Override
	public List<Epreuve> selectAll() throws ManagerException {
		List<Epreuve> liste = new ArrayList<Epreuve>();
		
		try {
			liste = epreuveDAO.selectAll();
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
	
		return liste;
	}

	@Override
	public Epreuve selectById(int id) throws ManagerException {
		Epreuve epr = null;
		
		try {
			epr = epreuveDAO.selectById(id);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return epr;
	}

	@Override
	public Epreuve selectByIdTest(int id) throws ManagerException {
		Epreuve epr = null;
		
		try {
			epr = epreuveDAO.selectByIdTest(id);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return epr;
	}

}
