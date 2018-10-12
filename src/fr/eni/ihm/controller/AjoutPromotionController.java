package fr.eni.ihm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.bll.manager.PromotionManager;
import fr.eni.bll.manager.factory.ManagerFactory;
import fr.eni.bo.Promotion;
import fr.eni.tp.web.common.util.ValidationUtil;


public class AjoutPromotionController extends HttpServlet{
	private PromotionManager promotionManager = ManagerFactory.PromotionManager();
	
	private static final long serialVersionUID = -6970893575378675464L;
	private Object utilisateurConnecte = null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			req.getRequestDispatcher("AjoutPromotion").forward(req, resp);
	}

	/* AJOUT PROMOTION */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		utilisateurConnecte = session.getAttribute("utilisateurConnecte");
		if(utilisateurConnecte != null){
			
		
			Promotion promo = new Promotion();
			try {
				ValidationUtil.checkNotBlank(req.getParameter("message"));
				promo.setLibelle(req.getParameter("message"));
				promotionManager.insert(promo);
			} catch (Exception e) {
				resp.sendError(500);
			}
				
			req.getRequestDispatcher("ListeEpreuve").forward(req, resp);
		}else{
			req.getRequestDispatcher("authentification").forward(req, resp);
		}
	}
}
