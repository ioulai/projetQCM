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

public class AjoutController extends HttpServlet {

	private static final long serialVersionUID = -4396510382624616918L;
	private ListeArticleManager listeArticleManager = ManagerFactory.listeArticleManager();
	private ArticleManager articleManager = ManagerFactory.articleManager();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			
			ListeArticle listeArt = null;
			
			String nomArticle = req.getParameter("nomArticle");
			String nomListe = req.getParameter("nomListe");
			
			boolean isNew = true;
			
			if (req.getParameter("isNew") != null) {
				if (req.getParameter("isNew").equals("true")) {
					isNew = true;
				}
				else {
					isNew = false;
				}
			}
			
			ValidationUtil.checkNotBlank(nomArticle);
			ValidationUtil.checkNotBlank(nomListe);
			
			if (isNew) {
				// Création liste
				listeArt = new ListeArticle();
				listeArt.setNom(nomListe);
				listeArt = listeArticleManager.insert(listeArt);
				
				isNew = false;
			}
			else {
				// Selection liste vu que existant
				listeArt = listeArticleManager.selectByNom(nomListe);
			}
			
			// Ne peut pas être null
			ValidationUtil.checkNotNull(listeArt);
		
			// Création article
			Article art = new Article();
			art.setNom(nomArticle);
			art.setListeArticle(listeArt);
			art.setSelected(false);
			
			// Insertion du new article
			articleManager.insert(art);
			
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
