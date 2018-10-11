package fr.eni.ihm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.manager.CandidatManager;
import fr.eni.bll.manager.ProfilManager;
import fr.eni.bll.manager.PromotionManager;
import fr.eni.bll.manager.factory.ManagerFactory;
import fr.eni.bo.Candidat;
import fr.eni.bo.Profil;
import fr.eni.bo.Promotion;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public class AjoutUtilisateurController extends HttpServlet{
	private CandidatManager candidatManager = ManagerFactory.candidatManager();
	private ProfilManager profilManager = ManagerFactory.ProfilManager();
	private PromotionManager promotionManager = ManagerFactory.PromotionManager();
	
	private static final long serialVersionUID = -6970893575378675464L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			List<Profil> profils = null;
			List<Promotion> promotions = null;
			try {
				promotions = promotionManager.selectAll();
				profils = profilManager.selectAll();
			} catch (ManagerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("promotion",promotions);
			req.setAttribute("profil",profils);
			req.getRequestDispatcher("AjoutCandidat").forward(req, resp);
	}

	/* AJOUT UTILISATEUR/CANDIDAT/COLLABO */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			Profil profil = null;
			Promotion promotion = null;
			Candidat candidat = null;
			List<Profil> profils = null;
			List<Promotion> promotions = null;
			try {
				promotions = promotionManager.selectAll();
				profils = profilManager.selectAll();
				req.setAttribute("profil",profils);
				req.setAttribute("promotion",promotions);
				
				candidat = new Candidat();
				candidat = candidatManager.selectByEmail(req.getParameter("Email"));
				profil = profilManager.selectByName(req.getParameter("profilbox"));
				
				if(candidat == null)
				{
					candidat = new Candidat();
					candidat.setEmail(req.getParameter("Email"));
					candidat.setNom(req.getParameter("Nom"));
					candidat.setPrenom(req.getParameter("Prenom"));
					candidat.setPassword(req.getParameter("mdp"));
					candidat.setProfil(profil);
					candidat = candidatManager.insertUtilisateur(candidat);
					
					if(profil.getLibelle().equals("Candidat"))
					{
						promotion = promotionManager.selectByName(req.getParameter("promotionbox"));
						candidatManager.insertCandidat(candidat,promotion.getId());
					}else{
						candidatManager.insertCollaborateur(candidat);
					}
					
					req.getRequestDispatcher("AjoutCandidat").forward(req, resp);
				}else{
					req.setAttribute("error","Email déjà utilisé");
					req.getRequestDispatcher("AjoutCandidat").forward(req, resp);;
				}

				
			} catch (ManagerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
	}
}
