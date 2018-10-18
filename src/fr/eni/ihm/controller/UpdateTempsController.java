package fr.eni.ihm.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import fr.eni.bll.manager.EpreuveManager;
import fr.eni.bll.manager.TestManager;
import fr.eni.bll.manager.factory.ManagerFactory;
import fr.eni.bo.Epreuve;
import fr.eni.tp.web.common.bll.exception.ManagerException;


public class UpdateTempsController extends HttpServlet{
private static final long serialVersionUID = -6970891575378677464L;
	EpreuveManager epreuveManager = ManagerFactory.epreuveManager();
	TestManager testManager = ManagerFactory.TestManager();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	/* REDIRECTION SUR L'AJOUT D'UN TEST */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idepreuve;
		String time;
		int debut;
		int fin;
		String temp;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
        //Prend l'epreuve id
        debut = json.indexOf(',');
        fin = json.indexOf('}');
        temp = json.substring(debut+1, fin);
        temp = temp.replace("\"", "");
        debut = temp.indexOf(':');
        fin = temp.length();
        if(temp.substring(debut+1,fin) != "" && isNumeric(temp.substring(debut+1,fin))){
	    	idepreuve = Integer.parseInt(temp.substring(debut+1,fin));
	        //Prend le temps
	        debut = json.indexOf(':');
	        fin = json.indexOf(',');
	        time = json.substring(debut+1, fin);
	       
				try {
					Epreuve epr = epreuveManager.selectById(idepreuve);
					epr.setTempsEcoule(new Time((epr.getTest().getDuree().getSeconds() +  epr.getTest().getDuree().getHours()*3600 + epr.getTest().getDuree().getMinutes()*60)*1000 - Integer.parseInt(time)*1000-3600000));
					epreuveManager.updateTime(epr);
				} catch (ManagerException e) {
					e.printStackTrace();
				}
        }
      }
	
	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}

}

