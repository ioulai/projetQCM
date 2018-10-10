package fr.eni.dal.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import fr.eni.bo.Test;

public class TestDAOImpl /*implements TestDAO */{
	private static TestDAOImpl singleton;
	
//	private static final String SELECT_BY_ID_QUERY = "SELECT * FROM epreuve INNER JOIN Candidat ON idUtilisateur = utilisateur_idUtilisateur INNER JOIN test ON test_idTest = idTest WHERE idEpreuve = ?";
//	private static final String SELECT_ALL = "SELECT * FROM epreuve INNER JOIN Candidat ON idUtilisateur = utilisateur_idUtilisateur INNER JOIN test ON test_idTest = idTest";
	
//	public static TestDAO getInstance() {
//		if (singleton == null)
//			singleton = new TestDAOImpl();
//		
//		return singleton;
//	}
//	}
	
	public static Test map(ResultSet resultSet) throws SQLException {
		Test test = new Test();
		test.setDescription(resultSet.getString("description"));
		test.setDuree(resultSet.getTime("duree"));
		test.setId(resultSet.getInt("idTest"));
		test.setLibelle(resultSet.getString("libelle"));
		test.setSeuilBas(resultSet.getString("seuilBas"));
		test.setSeuilHaut(resultSet.getString("seuilHaut"));
		
		return test;
	}
}
