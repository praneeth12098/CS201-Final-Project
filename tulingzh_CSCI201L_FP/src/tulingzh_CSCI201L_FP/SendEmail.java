package tulingzh_CSCI201L_FP;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {
	public static void Mailer (String receiver, String content) {

		String from="tulingzh@gmail.com";
		String to=receiver;
		
		
		final String username="tulingzh";
		final String password="Sakura520!!!";
		
		
		String host="smtp.gmail.com";
		
		Properties props=new Properties();
		 props.put("mail.smtp.auth", "true");
	     props.put("mail.smtp.starttls.enable", "true");
	     props.put("mail.smtp.host", host);
	     props.put("mail.smtp.port", "587");
	     
	     // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
		   }
	         });

	      try {
		   // Create a default MimeMessage object.
		   Message message = new MimeMessage(session);
		
		   // Set From: header field of the header.
		   message.setFrom(new InternetAddress(from));
		
		   // Set To: header field of the header.
		   message.setRecipients(Message.RecipientType.TO,
	               InternetAddress.parse(to));
		
		   // Set Subject: header field
		   message.setSubject("You received a new connection invitation!");
		   
		   Multipart multipart=new MimeMultipart("alternative");
		  
		   MimeBodyPart htmlPart=new MimeBodyPart();
		
		   // Now set the actual message
		   if (content!=null &&content.length()>0) {
			  
			   htmlPart.setContent("<html>"+content+"<br/><br/><a href='http://localhost:8080/tulingzh_CSCI201L_FP/Congrats.html'</a><button>Connect!</button><br/><br/><br/><a href='http://localhost:8080/tulingzh_CSCI201L_FP/Sorry.html'</a><button>Refuse</button></html>","text/html");
			   multipart.addBodyPart(htmlPart);
		   }
		   else {
			  
			   htmlPart.setContent("<html>A Recruiter from XXX Company is interested in your projects and would like to connect with you!<br/><br/><a href='http://localhost:8080/tulingzh_CSCI201L_FP/Congrats.html'</a><button>Connect!</button><br/><br/><br/><a href='http://localhost:8080/tulingzh_CSCI201L_FP/Sorry.html'</a><button>Refuse</button></html>","text/html");
			   multipart.addBodyPart(htmlPart);
		   }
		  
		   message.setContent(multipart);
		   
		   // Send message
		   Transport.send(message);

		   System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	         throw new RuntimeException(e);
	      }

		
	}

}
