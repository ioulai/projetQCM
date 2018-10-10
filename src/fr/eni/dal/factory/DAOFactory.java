package fr.eni.dal.factory;

import fr.eni.dal.dao.impl.CandidatDAOImpl;
import fr.eni.dal.dao.impl.EpreuveDAOImpl;
import fr.eni.dal.dao.impl.QuestionDAOImpl;
import fr.eni.dal.dao.impl.ThemeDAOImpl;
import fr.eni.dal.dao.impl.SectionTestDAOImpl;
import fr.eni.dal.dao.CandidatDAO;
import fr.eni.dal.dao.EpreuveDAO;
import fr.eni.dal.dao.QuestionDAO;
import fr.eni.dal.dao.ThemeDAO;
import fr.eni.dal.dao.SectionTestDAO;

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
	
	public static SectionTestDAO SectionTestDAO() {
		return SectionTestDAOImpl.getInstance();
	}
	
	public static CandidatDAO candidatDAO(){
		return CandidatDAOImpl.getInstance();
	}
}
