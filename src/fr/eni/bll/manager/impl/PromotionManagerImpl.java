package fr.eni.bll.manager.impl;

import java.util.ArrayList;
import java.util.List;

import fr.eni.bll.manager.PromotionManager;
import fr.eni.bo.Promotion;
import fr.eni.dal.dao.PromotionDAO;
import fr.eni.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.util.ValidationUtil;

public class PromotionManagerImpl implements PromotionManager{

	private static PromotionManager singleton = null;
	private PromotionDAO promotionDAO = DAOFactory.PromotionDAO();
	
	public static PromotionManager getInstance() {
		if (singleton == null)
			singleton = new PromotionManagerImpl();
		
		return singleton;
	}

	@Override
	public List<Promotion> selectAll() throws ManagerException {
		List<Promotion> liste = new ArrayList<Promotion>();
		
		try {
			liste = promotionDAO.selectAll();
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
	
		return liste;
	}

	@Override
	public Promotion insert(Promotion promotion) throws ManagerException {
		ValidationUtil.checkNotNull(promotion);
		
		Promotion promo = null;
		
		try {
			promo = promotionDAO.insert(promotion);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return promo;
	}

	@Override
	public Promotion selectByName(String name) throws ManagerException {
		Promotion promotion = null;
		
		try {
			promotion = promotionDAO.selectByName(name);
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return promotion;
	}

}
