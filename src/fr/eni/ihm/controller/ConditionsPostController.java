package fr.eni.ihm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConditionsPostController extends HttpServlet {

	private static final long serialVersionUID = -4396510382624616918L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		Cookie cookie = null;
		
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("conditionsAccepted")) {
					cookie = c;
				}
			}
			
			if (cookie == null) {
				cookie = new Cookie("conditionsAccepted", "true");
				cookie.setMaxAge(999999999);
				resp.addCookie(cookie);
			}
		}
		
		req.getRequestDispatcher("ListeArticleController").forward(req, resp);
	}
}
