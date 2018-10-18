package fr.eni.ihm.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VerificationUtilisateurConnecter implements Filter {

	private ServletContext context;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String messageErreur = null;
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		if (session != null) {
			String uri = req.getRequestURI();
			Object utilisateurConnecte = session.getAttribute("utilisateurConnecte");
			Object profilUtilisateur = session.getAttribute("profilUtilisateur");
			// System.out.println("utilisateurConnecte [" +
			// session.getAttribute("utilisateurConnecte"));
			if (utilisateurConnecte == null && !uri.contains("bootstrap") && !uri.contains("rocket")
					&& !uri.contains("AuthentificationPostController") && !uri.matches("/QCM/")
					&& !uri.contains("deconnecter") && !uri.contains("authentification")) {
				this.context.log("chemin demandé::" + uri);

				this.context.log("acces non autoriser");
				messageErreur = "<div class='alert alert-danger' role='alert'>Acces non autorisé !<br> Authentification requise</div>";
				req.setAttribute("messageErreur", messageErreur);
				req.getRequestDispatcher("authentification").forward(req, res);
			} else {
			
				if (profilUtilisateur!=null && profilUtilisateur.equals("CANDIDAT") && (uri.contains("AjoutUtilisateur")
						|| uri.contains("SupprUtilisateur") || uri.contains("Inscription")
						|| uri.contains("Desinscription") || uri.contains("AjoutPromo"))) {
					//System.out.println("non autorise" + profilUtilisateur);
					 req.getRequestDispatcher("authorizationError").forward(req,
					 res);
				} else {
					chain.doFilter(request, response);
				}
			}
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
		this.context.log("verification Utilisateur Connecter");

	}

}
