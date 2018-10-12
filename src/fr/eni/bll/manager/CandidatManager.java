package fr.eni.bll.manager;

import java.util.List;

import fr.eni.bo.Candidat;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public interface CandidatManager {

	Candidat selectByEmailPassword(String email, String password) throws ManagerException;
	Candidat selectByEmail(String email) throws ManagerException;
	void insertCandidat(Candidat candidat,int codePromo) throws ManagerException;
	void insertCollaborateur(Candidat candidat) throws ManagerException;
	Candidat insertUtilisateur(Candidat candidat) throws ManagerException;
	List<Candidat> selectAllCandidat() throws ManagerException;
}
