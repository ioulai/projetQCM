package fr.eni.bll.manager.impl;

import java.util.ArrayList;
import java.util.List;

import fr.eni.bll.manager.TestManager;
import fr.eni.bo.Test;
import fr.eni.dal.dao.TestDAO;
import fr.eni.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;

public class TestManagerImpl implements TestManager {
	private static TestManager singleton = null;
	
	private TestDAO testDAO = DAOFactory.TestDAO();
	
	public static TestManager getInstance() {
		if (singleton == null)
			singleton = new TestManagerImpl();
		
		return singleton;
	}

	@Override
	public Test selectById(int id) throws ManagerException {
		Test test = new Test();
		
		try {
			test = testDAO.selectById(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return test;
	}

	@Override
	public List<Test> selectAll() throws ManagerException {
		List<Test> tests = new ArrayList<Test>();

		try {
			tests = testDAO.selectAll();
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return tests;
	}

}
