package fr.eni.ihm.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConditionsController implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		Cookie[] cookies = req.getCookies();
		Cookie cookie = null;
		
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("conditionsAccepted")) {
					cookie = c;
				}
			}
			
			if (cookie == null) {
				req.getRequestDispatcher("Conditions").forward(req, resp);
			}
		}
		else {
			req.getRequestDispatcher("Conditions").forward(req, resp);
		}
		
		if (cookie != null)
			chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
