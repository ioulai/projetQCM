package fr.eni.ihm.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.manager.SectionTestManager;
import fr.eni.bll.manager.factory.ManagerFactory;
import fr.eni.bo.Question;
import fr.eni.bo.SectionTest;
import fr.eni.robot.Robot;

public class QuestionController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			int idTest = Integer.parseInt(req.getParameter("idTest"));
			SectionTestManager stm = ManagerFactory.sectionTestManager();
			
			ArrayList<Question> questions = new ArrayList<Question>();
			ArrayList<SectionTest> sectionTests = stm.selectByIdTest(idTest);
			
			for (SectionTest st : sectionTests) {
				questions.addAll(Robot.tirageAuSort(st));
			}
			
			req.setAttribute("questions", questions);
			
			req.getRequestDispatcher("question").forward(req, resp);
		} catch (Exception e) {
			resp.sendError(500);
		}
	}
}
