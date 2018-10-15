package fr.eni.ihm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PreResultatsController extends HttpServlet{
	private static final long serialVersionUID = -6970893575378675464L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			req.getRequestDispatcher("preResultats").forward(req, resp);
		} catch (Exception e) {
			resp.sendError(500);
		}
	}
}
