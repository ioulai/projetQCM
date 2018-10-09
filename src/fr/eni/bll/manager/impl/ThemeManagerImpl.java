package fr.eni.bll.manager.impl;

import java.util.ArrayList;
import java.util.List;

import fr.eni.bll.manager.ListeArticleManager;
import fr.eni.bll.manager.QuestionManager;
import fr.eni.bll.manager.ThemeManager;
import fr.eni.bo.Question;
import fr.eni.bo.Theme;
import fr.eni.dal.dao.ListeArticleDAO;
import fr.eni.dal.dao.QuestionDAO;
import fr.eni.dal.dao.ThemeDAO;
import fr.eni.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.util.ValidationUtil;

public class ThemeManagerImpl implements ThemeManager {
	private static ThemeManager singleton = null;
	private ThemeDAO themeDAO = DAOFactory.ThemeDAO();
	
	public static ThemeManager getInstance() {
		if (singleton == null)
			singleton = new ThemeManagerImpl();
		
		return singleton;
	}

	@Override
	public ArrayList<Theme> selectAll() throws ManagerException {
		ArrayList<Theme> themes = new ArrayList<Theme>();
		
		ValidationUtil.checkNotNull(themes);
		
		try {
			themes = themeDAO.selectAll();
		} catch (DaoException e) {
			throw new ManagerException("Erreur DAO", e);
		}
		
		return themes;
	}
}
