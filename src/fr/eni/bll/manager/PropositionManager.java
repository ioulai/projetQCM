package fr.eni.bll.manager;

import java.util.ArrayList;

import fr.eni.bo.Proposition;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public interface PropositionManager {	
	ArrayList<Proposition> selectByIdQuestion(int id) throws ManagerException;
	
	Proposition selectById(int id) throws ManagerException;
}
