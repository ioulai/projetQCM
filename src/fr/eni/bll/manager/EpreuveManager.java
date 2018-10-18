package fr.eni.bll.manager;

import java.util.Date;
import java.util.List;

import fr.eni.bo.Epreuve;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public interface EpreuveManager {
	
	List<Epreuve> selectAll() throws ManagerException;
	
	Epreuve selectById(int id) throws ManagerException;
	
	Epreuve selectByIdTest(int id) throws ManagerException;
	
	List<Epreuve> selectByIdCandidatTest(int idCandidat, int idTest) throws ManagerException;
	
	Epreuve insert(int idCandidat, int idTest,Date debutValidite,Date finValidite) throws ManagerException;
	
	List<Epreuve> selectByUserId(int id) throws ManagerException;

	void update(Epreuve epreuve) throws ManagerException;
	
	void deleteById(int id) throws ManagerException;

	Epreuve selectByIdTestIdCandidat(int idTest, int idCandidat) throws ManagerException;
	
	void updateTime(Epreuve epreuve) throws ManagerException;
}
