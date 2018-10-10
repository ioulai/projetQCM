package fr.eni.ihm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.manager.EpreuveManager;
import fr.eni.bll.manager.factory.ManagerFactory;
import fr.eni.bo.Epreuve;

public class SelectionEpreuveController extends HttpServlet{
	private EpreuveManager epreuveManager = ManagerFactory.epreuveManager();

	private static final long serialVersionUID = -6970893575378675464L;

	/* CHARGEMENT LISTES */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<Epreuve> epreuves = epreuveManager.selectAll();
			req.setAttribute("epreuve", epreuves);
		}
		catch (Exception e) {
			resp.sendError(500);
		}
			req.getRequestDispatcher("ListeEpreuve").forward(req, resp);
	}

	/* SUPPRESSION LISTE */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
