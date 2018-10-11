package fr.eni.bll.manager.impl;

import fr.eni.bll.manager.ReponseTirageManager;
import fr.eni.bo.Proposition;
import fr.eni.bo.QuestionTirage;
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
	public ReponseTirage selectByAll(QuestionTirage questionTirage, Proposition proposition) throws ManagerException {
		ValidationUtil.checkNotNull(questionTirage);
		ValidationUtil.checkNotNull(proposition);
		
		ReponseTirage reponseTirage;
		
		try {
			reponseTirage = reponseTirageDAO.selectByAll(questionTirage, proposition);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return reponseTirage;
	}
}
