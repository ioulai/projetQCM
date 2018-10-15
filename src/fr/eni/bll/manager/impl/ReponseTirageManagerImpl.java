package fr.eni.bll.manager.impl;

import java.util.ArrayList;

import fr.eni.bll.manager.ReponseTirageManager;
import fr.eni.bo.ReponseTirage;
import fr.eni.dal.dao.ReponseTirageDAO;
import fr.eni.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.util.ValidationUtil;

public class ReponseTirageManagerImpl implements ReponseTirageManager {
	private static ReponseTirageManager singleton = null;
	private ReponseTirageDAO reponseTirageDAO = DAOFactory.ReponseTirageDAO();
	
	public static ReponseTirageManager getInstance() {
		if (singleton == null)
			singleton = new ReponseTirageManagerImpl();
		
		return singleton;
	}

	@Override
	public void insert(ReponseTirage reponseTirage) throws ManagerException {
		ValidationUtil.checkNotNull(reponseTirage);
		
		try {
			reponseTirageDAO.insert(reponseTirage);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
	}

	@Override
	public ArrayList<ReponseTirage> selectByAll(int idEpreuve, int idQuestion) throws ManagerException {
		ArrayList<ReponseTirage> reponseTirages;
		
		try {
			reponseTirages = reponseTirageDAO.selectByAll(idEpreuve, idQuestion);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return reponseTirages;
	}

	@Override
	public void update(ReponseTirage reponseTirage) throws ManagerException {
		ValidationUtil.checkNotNull(reponseTirage);
		
		try {
			reponseTirageDAO.update(reponseTirage);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
	}

	@Override
	public void deleteAll() throws ManagerException {
		try {
			reponseTirageDAO.deleteAll();
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
	}

	@Override
	public void deleteByIds(int idEpreuve, int idQuestion) throws ManagerException {
		try {
			reponseTirageDAO.deleteByIds(idEpreuve, idQuestion);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
	}
}
