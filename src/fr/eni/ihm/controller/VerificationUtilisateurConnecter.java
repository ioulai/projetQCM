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
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();
		this.context.log("chemin demandé::" + uri);
		HttpSession session = req.getSession(false);
//		System.out.println("candidatConnecter" + session.getAttribute("candidatConnecter"));
		if (session == null) {			
			req.getRequestDispatcher("authentification").forward(req, res);
			this.context.log("acces non autoriser");
		}else{
			chain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
		this.context.log("verification Utilisateur Connecter");

	}

}
