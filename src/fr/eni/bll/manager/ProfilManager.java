package fr.eni.bll.manager;

import java.util.List;

import fr.eni.bo.Profil;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public interface ProfilManager {
	List<Profil> selectAll() throws ManagerException;
	Profil selectByName(String name) throws ManagerException;
	Profil selectById(int id)throws ManagerException;
}
