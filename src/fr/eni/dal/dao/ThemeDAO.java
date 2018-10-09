package fr.eni.dal.dao;

import java.util.ArrayList;

import fr.eni.bo.Theme;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface ThemeDAO {	
	ArrayList<Theme> selectAll() throws DaoException;
}