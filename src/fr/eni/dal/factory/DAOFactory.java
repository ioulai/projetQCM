package fr.eni.dal.factory;

import fr.eni.dal.dao.impl.CandidatDAOImpl;
import fr.eni.dal.dao.impl.CollaborateurDAOImpl;
import fr.eni.dal.dao.impl.EpreuveDAOImpl;
import fr.eni.dal.dao.impl.ProfilDAOImpl;
import fr.eni.dal.dao.impl.PromotionDAOImpl;
import fr.eni.dal.dao.impl.PropositionDAOImpl;
import fr.eni.dal.dao.impl.QuestionDAOImpl;
import fr.eni.dal.dao.impl.QuestionTirageDAOImpl;
import fr.eni.dal.dao.impl.ReponseTirageDAOImpl;
import fr.eni.dal.dao.ReponseTirageDAO;
import fr.eni.dal.dao.impl.ThemeDAOImpl;
import fr.eni.dal.dao.impl.SectionTestDAOImpl;
import fr.eni.dal.dao.impl.TestDAOImpl;
import fr.eni.dal.dao.CandidatDAO;
import fr.eni.dal.dao.CollaborateurDAO;
import fr.eni.dal.dao.EpreuveDAO;
import fr.eni.dal.dao.ProfilDAO;
import fr.eni.dal.dao.PromotionDAO;
import fr.eni.dal.dao.PropositionDAO;
import fr.eni.dal.dao.QuestionDAO;
import fr.eni.dal.dao.QuestionTirageDAO;
import fr.eni.dal.dao.ThemeDAO;
import fr.eni.dal.dao.SectionTestDAO;
import fr.eni.dal.dao.TestDAO;

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

	public static PromotionDAO PromotionDAO() {
		return PromotionDAOImpl.getInstance();
	}
	
	public static QuestionTirageDAO QuestionTirageDAO() {
		return QuestionTirageDAOImpl.getInstance();
	}
	
	public static CandidatDAO candidatDAO(){
		return CandidatDAOImpl.getInstance();
	}

	public static ProfilDAO ProfilDAO(){
		return ProfilDAOImpl.getInstance();
	}
	
	public static PropositionDAO propositionDAO(){
		return PropositionDAOImpl.getInstance();
	}
	
	public static ReponseTirageDAO ReponseTirageDAO() {
		return ReponseTirageDAOImpl.getInstance();
	}
	public static TestDAO TestDAO(){
		return TestDAOImpl.getInstance();
	}

	public static CollaborateurDAO collaborateurDao() {
		// TODO Auto-generated method stub
		return CollaborateurDAOImpl.getInstance();
	}

}
