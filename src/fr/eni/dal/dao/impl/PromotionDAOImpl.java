package fr.eni.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import fr.eni.bo.Promotion;
import fr.eni.dal.dao.PromotionDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class PromotionDAOImpl implements PromotionDAO{
	
private static PromotionDAOImpl singleton;

	private static final String INSERT_QUERY = "INSERT INTO promotion(libelle) VALUES (RTRIM(LTRIM(?)))";
	private static final String SELECT_ALL = "SELECT * FROM promotion";
	
	public static PromotionDAO getInstance() {
		if (singleton == null)
			singleton = new PromotionDAOImpl();
		
		return singleton;
	}

	@Override
	public List<Promotion> selectAll() throws DaoException {
		Promotion promo = null;
		List<Promotion> promotions = new ArrayList<Promotion>();
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			statement = connexion.prepareStatement(SELECT_ALL);
			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				promo = new Promotion();
				promo = map(resultSet);
				
				promotions.add(promo);
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return promotions;
	}

	@Override
	public Promotion insert(Promotion promotion) throws DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connexion = MSSQLConnectionFactory.get();
			
			connexion.setAutoCommit(false);
			
			statement = connexion.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, promotion.getLibelle());

			if (statement.executeUpdate() == 1) {
				resultSet = statement.getGeneratedKeys();
				
				if (resultSet.next()) {
					promotion.setId(resultSet.getInt(1));
				}
			}
			
			connexion.commit();

		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
		finally {
			ResourceUtil.safeClose(resultSet, statement, connexion);
		}
		
		return promotion;
	}
	
	
	public static Promotion map(ResultSet resultSet) throws DaoException {
		Promotion promo = null;
		
		try {
			promo = new Promotion();
			promo.setId(resultSet.getInt("codePromo"));
			promo.setLibelle(resultSet.getString("libelle"));
			
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}

		return promo;
	}
	
}
