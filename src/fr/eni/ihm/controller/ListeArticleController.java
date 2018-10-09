package fr.eni.ihm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.manager.ListeArticleManager;
import fr.eni.bll.manager.factory.ManagerFactory;
import fr.eni.bo.ListeArticle;
import fr.eni.tp.web.common.util.ValidationUtil;

public class ListeArticleController extends HttpServlet {

	private static final long serialVersionUID = -4396510382624616918L;
	private ListeArticleManager listeArticleManager = ManagerFactory.listeArticleManager();

	/* CHARGEMENT LISTES */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<ListeArticle> articles = listeArticleManager.selectAll();
			
			req.setAttribute("liste", articles);
			req.getRequestDispatcher("Welcome").forward(req, resp);
		}
		catch (Exception e) {
			resp.sendError(500);
		}
	}

	/* SUPPRESSION LISTE */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {			
			req.setCharacterEncoding("UTF-8");
			String nom = req.getParameter("nomListe");
			ValidationUtil.checkNotBlank(nom);
			
			ListeArticle lstArticle = listeArticleManager.selectByNom(nom);
			ValidationUtil.checkNotNull(lstArticle);
			
			listeArticleManager.deleteById(lstArticle.getId());
			
			List<ListeArticle> articles = listeArticleManager.selectAll();
			ValidationUtil.checkNotNull(articles);
			
			req.setAttribute("liste", articles);
			req.getRequestDispatcher("Welcome").forward(req, resp);
		}
		catch (IllegalArgumentException f) {
			resp.sendError(400);
		}
		catch (Exception e) {
			resp.sendError(500);
		}
	}
}
