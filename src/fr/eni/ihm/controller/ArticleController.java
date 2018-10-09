package fr.eni.ihm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.manager.ArticleManager;
import fr.eni.bll.manager.ListeArticleManager;
import fr.eni.bll.manager.factory.ManagerFactory;
import fr.eni.bo.Article;
import fr.eni.bo.ListeArticle;
import fr.eni.tp.web.common.util.ValidationUtil;

public class ArticleController extends HttpServlet {

	private static final long serialVersionUID = -4396510382624616918L;
	private ArticleManager articleManager = ManagerFactory.articleManager();
	private ListeArticleManager listeArticleManager = ManagerFactory.listeArticleManager();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("Ajout").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idArt = 0;
		req.setCharacterEncoding("UTF-8");
		
		try {
			ValidationUtil.checkNotNull(req.getParameter("id"));
			ValidationUtil.checkNotNull(req.getParameter("nomListe"));
			
			idArt = Integer.parseInt(req.getParameter("id"));
			articleManager.delete(idArt);

			ListeArticle listeArt = null;
			
			String nomListe = (String)req.getParameter("nomListe");
			boolean isNew = false;
			
			ValidationUtil.checkNotBlank(nomListe);
			
			listeArt = listeArticleManager.selectByNom(nomListe);
			
			// Ne peut pas être null
			ValidationUtil.checkNotNull(listeArt);
			
			// Recherche de tous les articles de cette liste
			List<Article> listeArts = articleManager.selectByIdListe(listeArt);
			
			req.setAttribute("articles", listeArts);
			req.setAttribute("isNew", isNew);
			req.setAttribute("nomListe", nomListe);
			
			req.getRequestDispatcher("Ajout").forward(req, resp);
		}
		catch (IllegalArgumentException f) {
			resp.sendError(400);
		}
		catch (Exception e) {
			resp.sendError(500);
		}
	}
}
