package fr.eni.ihm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeconnecterController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DeconnecterController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("JSESSIONID")) {
					//System.out.println("JSESSIONID" + cookie.getValue());
					break;
				}
			}
		}

		// invalider la session
		HttpSession session = req.getSession(false);
		System.out.println("candidatConnecter" + session.getAttribute("candidatConnecter"));
		if (session != null) {
			session.invalidate();
			System.out.println("deconnecter");
			req.getRequestDispatcher("authentification").forward(req, resp);
		}

	}

}
