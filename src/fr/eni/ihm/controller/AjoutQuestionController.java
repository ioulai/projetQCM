package fr.eni.ihm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjoutQuestionController extends HttpServlet{
private static final long serialVersionUID = -6970891575378677464L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			req.getRequestDispatcher("").forward(req, resp);
	}

	/* REDIRECTION SUR L'AJOUT D'UN TEST */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
