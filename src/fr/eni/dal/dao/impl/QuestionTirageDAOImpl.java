package fr.eni.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.bo.QuestionTirage;
import fr.eni.dal.dao.QuestionTirageDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class QuestionTirageDAOImpl implements QuestionTirageDAO{
private static QuestionTirageDAOImpl singleton;
	
	private static final String INSERT_QUERY = "INSERT INTO questionTirage VALUES (?, ?, ?)";
	
	public static QuestionTirageDAO getInstance() {
		if (singleton == null)
			singleton = new QuestionTirageDAOImpl();
		
		return singleton;
	}
	
	@Override
	public void insert(QuestionTirage questionTirage) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            
            statement.setBoolean(1, questionTirage.isEstMarquee());
            statement.setInt(2, questionTirage.getNumOrdre());
            statement.setInt(3, questionTirage.getEpreuve().getIdEpreuve());
            
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
	}
	
//	private static QuestionTirage map(ResultSet rst) throws SQLException {
//		QuestionTirage questionTirage = new QuestionTirage();
//		questionTirage.setEstMarquee(rst.getBoolean("estMarquee"));
//		questionTirage.setNumOrdre(rst.getInt("numOrdre"));
//		
//		return questionTirage;
//	}
}
