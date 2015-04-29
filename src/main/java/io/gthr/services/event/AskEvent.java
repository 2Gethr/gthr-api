package io.gthr.services.event;

import io.gthr.entities.*;

import javax.servlet.http.* ;
import java.io.* ;
import java.util.*;

public class AskEvent extends HttpServlet
{
  private String[] listWords = {"la télévision", "les chevals (la faute est voulue)", "que la Terre tourne", "la vie", "la couleur bleu-azure d'un ciel suédois sans nuage"};
  
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().println("<h2>La Question</h2>");
		
		Random rn = new Random();
		String word = listWords[rn.nextInt(listWords.length)];
		
	  resp.getWriter().println("<p>Demandez à votre voisin si il aime " + word + ".");

	}
}
