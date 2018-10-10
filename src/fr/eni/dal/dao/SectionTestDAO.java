package fr.eni.dal.dao;

import java.util.ArrayList;
import fr.eni.bo.SectionTest;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface SectionTestDAO {	
	ArrayList<SectionTest> selectByIdTest(int id) throws DaoException;
}
