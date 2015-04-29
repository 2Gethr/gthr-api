package io.gthr.services;

import io.gthr.api.*;
import io.gthr.entities.*;
import io.gthr.repositories.UserRepository;

import javax.servlet.http.* ;
import java.io.* ;

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
    
  public void sendNotification(UserGthr user) 
  {
    String text = "Notified";

    try 
    {
      Message msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress("brice.p.thomas@gmail.com", "2Gethr"));
      msg.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getUser().getEmail(), user.getUser().getNickname()));
      msg.setSubject("Notification");
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
	
	  UserGthr user = UserRepository.instance().get(5086100271923200L);	
	  
	  if(user.getSubscriptions().contains(5720147234914304L))
	  {
		  Notification not = new Notification();
		  not.sendNotification(user);
	  }
	}

}
