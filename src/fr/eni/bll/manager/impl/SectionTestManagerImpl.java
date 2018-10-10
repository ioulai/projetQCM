package fr.eni.bll.manager.impl;

import java.util.ArrayList;
import fr.eni.bll.manager.SectionTestManager;
import fr.eni.bo.SectionTest;
import fr.eni.dal.dao.SectionTestDAO;
import fr.eni.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;

public class SectionTestManagerImpl implements SectionTestManager{

	private static SectionTestManager singleton = null;
	private SectionTestDAO sectionTestDAO = DAOFactory.SectionTestDAO();
	
	public static SectionTestManager getInstance() {
		if (singleton == null)
			singleton = new SectionTestManagerImpl();
		
		return singleton;
	}

	@Override
	public ArrayList<SectionTest> selectByIdTest(int id) throws ManagerException {
		ArrayList<SectionTest> lst = null;
		
		try {
			lst = sectionTestDAO.selectByIdTest(id);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return lst;
	}
}
