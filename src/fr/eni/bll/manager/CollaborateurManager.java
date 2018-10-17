package fr.eni.bll.manager;

import java.util.List;

import fr.eni.bo.Candidat;
import fr.eni.bo.Collaborateur;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface CollaborateurManager {
	Collaborateur selectById(int id) throws ManagerException;
	Collaborateur selectByEmailPassword(String email, String password) throws  ManagerException;
	Collaborateur selectByEmail(String email) throws ManagerException;
	List<Collaborateur> selectAllCollaborateur() throws ManagerException;
	void insertCollaborateur(Candidat candidat) throws ManagerException;
	void deleteCollaborateur(int id) throws ManagerException;
}
