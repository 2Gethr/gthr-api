package io.gthr.services;

import javax.servlet.* ;
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

public class Notification extends GenericServlet
{
  private Properties properties = new Properties();
  private Session session = Session.getDefaultInstance(properties, null);
  
  public static void main(String [ ] args)
  {
    Notification not = new Notification();
    not.sendNotification();
  }
  
  public void sendNotification()
  {
    String text = "Notified";

    try 
    {
      Message msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress("brice.p.thomas@gmail.com", "2Gethr"));
      msg.addRecipient(Message.RecipientType.TO, new InternetAddress("appMatty44@gmail.com", "Toi")); //user.getUser().getEmail(), user.getUser().getNickname()));
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
  
  public void service (ServletRequest request, ServletResponse response)
  {
    Notification not = new Notification();
    not.sendNotification();
    
    try
    {
      PrintWriter out = response.getWriter() ;
      out.println ("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">") ;
      out.println ("<title>Bonjour tout le monde&amp;nbsp;!</title>") ;
      out.println ("<p>Hello world!</p>") ;
    }
    catch (IOException e)
    {
      e.printStackTrace() ;
    }
  }
}
