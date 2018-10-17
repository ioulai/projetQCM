package fr.eni.dal.dao;

import java.util.List;

import fr.eni.bo.Candidat;
import fr.eni.bo.Collaborateur;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface CollaborateurDAO {
Collaborateur selectById(int id) throws DaoException;
Collaborateur selectByEmailPassword(String email, String password) throws DaoException;
Collaborateur selectByEmail(String email) throws DaoException;
List<Collaborateur> selectAllCollaborateur() throws DaoException;
void insertCollaborateur(Candidat candidat) throws DaoException;
void deleteCollaborateur(int id) throws DaoException;
}
