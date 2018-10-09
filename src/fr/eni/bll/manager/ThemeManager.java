package fr.eni.bll.manager;

import java.util.ArrayList;

import fr.eni.bo.Theme;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public interface ThemeManager {	
	ArrayList<Theme> selectAll() throws ManagerException;
}
