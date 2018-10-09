package fr.eni.ihm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.tp.web.common.util.ValidationUtil;

public class AuthentificationPostController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String email= req.getParameter("email");
			String password= req.getParameter("password");
			
			ValidationUtil.checkNotBlank(email);
			ValidationUtil.checkNotBlank(password);
			/** ici verif avec la bdd **/
			
			req.getRequestDispatcher("Welcome").forward(req, resp);
		} catch (Exception e) {
			resp.sendError(500);
		}
		
	}


	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("Welcome").forward(req, resp);
	}
	
	

}
