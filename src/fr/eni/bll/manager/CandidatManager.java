package fr.eni.bll.manager;

import fr.eni.bo.Candidat;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public interface CandidatManager {

	Candidat selectByEmailPassword(String email, String password) throws ManagerException;
}
