package fr.eni.bll.manager;

import java.util.List;

import fr.eni.bo.Epreuve;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public interface EpreuveManager {
	
	List<Epreuve> selectAll() throws ManagerException;
	
	Epreuve selectById(int id) throws ManagerException;
	
	Epreuve selectByIdTest(int id) throws ManagerException;
	
	Epreuve insert(int idCandidat, int idTest) throws ManagerException;
}
