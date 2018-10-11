package fr.eni.bll.manager;

import java.util.List;

import fr.eni.bo.Promotion;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public interface PromotionManager {
	List<Promotion> selectAll() throws ManagerException;
	Promotion selectByName(String name) throws ManagerException;
	Promotion insert(Promotion promotion) throws ManagerException;
}
