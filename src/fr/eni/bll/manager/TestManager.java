package fr.eni.bll.manager;

import java.util.List;

import fr.eni.bo.Test;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public interface TestManager {
	Test selectById(int id) throws ManagerException;
	
	List<Test> selectAll() throws ManagerException;
	
	void deleteById(int id) throws ManagerException;
}
