package fr.eni.ihm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.manager.PromotionManager;
import fr.eni.bll.manager.factory.ManagerFactory;



public class InscriptionTestController extends HttpServlet{
	//private  promotionManager = ManagerFactory.PromotionManager();
	
	private static final long serialVersionUID = -6970893575378675464L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			req.getRequestDispatcher("AjoutCandidat").forward(req, resp);
	}

	/* AJOUT PROMOTION */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			
	}

}
