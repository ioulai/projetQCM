package fr.eni.bll.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.bll.manager.EpreuveManager;

import fr.eni.bo.Epreuve;
import fr.eni.dal.dao.EpreuveDAO;
import fr.eni.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.util.ValidationUtil;

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

	@Override
	public Epreuve insert(int idCandidat, int idTest, Date debutValidite, Date finValidite) throws ManagerException {
		ValidationUtil.checkNotNull(debutValidite);
		ValidationUtil.checkNotNull(finValidite);
		
		Epreuve epr = null;
		
		try {
			epr = epreuveDAO.insert(idCandidat, idTest, debutValidite, finValidite);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return epr;
	}

	@Override
	public List<Epreuve> selectByIdCandidatTest(int idCandidat, int idTest) throws ManagerException {
		List<Epreuve> epr = null;
		
		try {
			epr = epreuveDAO.selectByIdCandidatTest(idCandidat, idTest);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return epr;
	}

	@Override
	public List<Epreuve> selectByUserId(int id) throws ManagerException {
		List<Epreuve> epr = null;
		
		try {
			epr = epreuveDAO.selectByUserId(id);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return epr;
	}
	
	public void update(Epreuve epreuve) throws ManagerException {
		try {
			epreuveDAO.update(epreuve);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
	}

	@Override
	public void deleteById(int id) throws ManagerException {
		try {
			epreuveDAO.deleteById(id);;
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
	}

	@Override
	public Epreuve selectByIdTestIdCandidat(int idTest, int idCandidat) throws ManagerException {
		Epreuve epr = null;
		
		try {
			epr = epreuveDAO.selectByIdTestIdCandidat(idTest, idCandidat);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return epr;
	}

	@Override
	public void updateTime(Epreuve epreuve) throws ManagerException {
		try {
			epreuveDAO.updateTime(epreuve);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
	}
}
