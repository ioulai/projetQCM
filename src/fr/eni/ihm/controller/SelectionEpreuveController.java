package fr.eni.ihm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.bll.manager.EpreuveManager;
import fr.eni.bll.manager.factory.ManagerFactory;
import fr.eni.bo.Candidat;
import fr.eni.bo.Epreuve;
import fr.eni.bo.Utilisateur;

public class SelectionEpreuveController extends HttpServlet {
	private EpreuveManager epreuveManager = ManagerFactory.epreuveManager();

	private static final long serialVersionUID = -6970893575378675464L;
	private Candidat utilisateurConnecte = null;

	/* CHARGEMENT LISTES */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		utilisateurConnecte = (Candidat) session.getAttribute("utilisateurConnecte");
		
		if (utilisateurConnecte != null) {
			try {
				List<Epreuve> epreuves = epreuveManager.selectByUserId(utilisateurConnecte.getId());
				req.setAttribute("epreuve", epreuves);
			} catch (Exception e) {
				resp.sendError(500);
			}
			req.getRequestDispatcher("Epreuve").forward(req, resp);
		}else{
			req.getRequestDispatcher("authentification").forward(req, resp);
		}
	}

	/* SUPPRESSION LISTE */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
