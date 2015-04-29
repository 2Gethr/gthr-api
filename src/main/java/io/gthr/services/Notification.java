package io.gthr.services;

import io.gthr.api.*;
import io.gthr.entities.*;
import io.gthr.repositories.*;

import javax.servlet.http.* ;
import java.io.* ;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.google.appengine.api.users.User;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Notification extends HttpServlet
{
  private Properties properties = new Properties();
  private Session session = Session.getDefaultInstance(properties, null);
    
  public void sendNotification(UserGthr user, Event event) 
  {
    String location = LocationRepository.instance().get(event.getLocation()).getName();
    
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
		
		String date = dateFormat.format(event.getDate());

    String text = "Rendez-vous à la location '" + location + " le " + date + " !\n\nLet's play 2Gethr !";
    
    try 
    {
      Message msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress("brice.p.thomas@gmail.com", "2Gethr"));
      msg.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getUser().getEmail(), user.getUser().getNickname()));
      msg.setSubject("Let's play 2Gethr !");
      msg.setText(text);
      Transport.send(msg);

    } 
    catch (AddressException e) 
    {
       // ...
    }
    catch (MessagingException e) 
    {
      // ...
    }
    catch (Exception e)
    {
    
    }
        
  }
  
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		
		Calendar cal = Calendar.getInstance();
		Date currentDate = cal.getTime();
		cal.add(Calendar.HOUR_OF_DAY, 1);
		Date borderDate = cal.getTime();
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
		
		resp.getWriter().println(dateFormat.format(currentDate));
		resp.getWriter().println(dateFormat.format(borderDate));
	
	  List<Event> events = EventRepository.instance().list();
	 
	  for(Event event: events)
	  {
	    Date eventDate = event.getDate();
      if(currentDate.compareTo(eventDate) < 0 && borderDate.compareTo(eventDate) >= 0)
      {
        List<UserGthr> users = UserRepository.instance().list();
        
        for(UserGthr user : users)
        {
          ArrayList<Long> locations = user.getSubscriptions();
          
          for(Long location : locations)
          {
            if(location == event.getLocation())
            {
              Notification not = new Notification();
		          not.sendNotification(user, event);
		          
		          resp.getWriter().println("Notification envoyée à " + user.getUser().getNickname());
            }
            else
            {
              resp.getWriter().println(dateFormat.format(event.getDate()));
            }
           
          }
        }
      }
	  }
	}

}
