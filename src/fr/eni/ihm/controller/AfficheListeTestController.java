package fr.eni.ihm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.manager.QuestionManager;
import fr.eni.bll.manager.TestManager;
import fr.eni.bll.manager.factory.ManagerFactory;
import fr.eni.bo.Question;
import fr.eni.bo.Test;
import fr.eni.tp.web.common.bll.exception.ManagerException;



public class AfficheListeTestController extends HttpServlet{
	
	private static final long serialVersionUID = -6970893575378677464L;
	
	private TestManager testManager = ManagerFactory.TestManager();
	private QuestionManager questionManager = ManagerFactory.questionManager();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Test> tests =null;
		
		try {
			tests = testManager.selectAll();
			req.setAttribute("test", tests);
			
			req.getRequestDispatcher("AfficheListe").forward(req, resp);
		} catch (ManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}

	/* REDIRECTION SUR L'AJOUT D'UN TEST */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Test> tests =null;
		ArrayList<Question> questions = null;
		try {
			tests = testManager.selectAll();
			req.setAttribute("test", tests);
			
			int idTest = Integer.parseInt(req.getParameter("testbox"));
			req.setAttribute("selectionid", idTest);
			questions = questionManager.selectByIdTest(idTest);
			req.setAttribute("question", questions);
			req.getRequestDispatcher("AfficheListe").forward(req, resp);
		} catch (ManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
