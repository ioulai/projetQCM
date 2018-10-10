package fr.eni.bll.manager;

import java.util.ArrayList;
import fr.eni.bo.SectionTest;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public interface SectionTestManager {	
	ArrayList<SectionTest> selectByIdTest(int id) throws ManagerException;
}
