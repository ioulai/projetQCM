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

public class SelectionController extends HttpServlet {

	private static final long serialVersionUID = -4396510382624616918L;
	private ListeArticleManager listeArticleManager = ManagerFactory.listeArticleManager();
	private ArticleManager articleManager = ManagerFactory.articleManager();

	/* SELECTION D'UN ARTICLE & SELECTION ALL*/
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			String nom = null;
			List<Article> articles = null;
			
			// SI SELECTION ARTICLE
			if (req.getParameter("isReinitialiser") == null) {
				nom = req.getParameter("nomListe");
				String selected = req.getParameter("isSelected");
				int idArticle = Integer.parseInt(req.getParameter("idArticle"));
				boolean isSelected = false;

				ValidationUtil.checkNotBlank(nom);
				
				if (selected != null) {
					if (selected.equals("on")) {
						isSelected = true;
					}
				}
				
				ListeArticle liste = listeArticleManager.selectByNom(nom);
				ValidationUtil.checkNotNull(liste);
				
				Article article = articleManager.selectById(idArticle, liste);
				ValidationUtil.checkNotNull(article);
				
				article.setSelected(isSelected);
				
				articleManager.updateSelected(article);
				
				articles = articleManager.selectByIdListe(liste);
				ValidationUtil.checkNotNull(articles);
			}
			// SI SELECTION ALL
			else {
				nom = req.getParameter("nomListe");
				ValidationUtil.checkNotBlank(nom);
				
				ListeArticle liste = listeArticleManager.selectByNom(nom);
				ValidationUtil.checkNotNull(liste);
				
				articles = articleManager.selectByIdListe(liste);
				ValidationUtil.checkNotNull(articles);
				
				for (Article art : articles) {
					art.setSelected(false);
					articleManager.updateSelected(art);
				}
			}
			
			req.setAttribute("articles", articles);
			req.setAttribute("nomListe", nom);
			
			req.getRequestDispatcher("ListeArticles").forward(req, resp);
		} 
		catch (IllegalArgumentException f) {
			resp.sendError(400);
		}
		catch (Exception e) {
			resp.sendError(500);
		}
	}

	/* CHARGEMENT DES ARTICLES */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {			
			String nom = req.getParameter("nomListe");
			ValidationUtil.checkNotBlank(nom);
			
			ListeArticle lstArticle = listeArticleManager.selectByNom(nom);
			ValidationUtil.checkNotNull(lstArticle);
			
			List<Article> articles = articleManager.selectByIdListe(lstArticle);
			ValidationUtil.checkNotNull(articles);
			
			req.setAttribute("articles", articles);
			req.setAttribute("nomListe", nom);
			
			req.getRequestDispatcher("ListeArticles").forward(req, resp);
		}
		catch (IllegalArgumentException f) {
			resp.sendError(400);
		}
		catch (Exception e) {
			resp.sendError(500);
		}
	}
}
