package fr.eni.dal.factory;

import fr.eni.dal.dao.impl.EpreuveDAOImpl;
import fr.eni.dal.dao.impl.QuestionDAOImpl;
import fr.eni.dal.dao.impl.ThemeDAOImpl;
import fr.eni.dal.dao.EpreuveDAO;
import fr.eni.dal.dao.QuestionDAO;
import fr.eni.dal.dao.ThemeDAO;

public class DAOFactory {
	
	public static EpreuveDAO EpreuveDAO() {
		return EpreuveDAOImpl.getInstance();
	}
	
	public static QuestionDAO QuestionDAO() {
		return QuestionDAOImpl.getInstance();
	}

	public static ThemeDAO ThemeDAO() {
		return ThemeDAOImpl.getInstance();
	}
}
