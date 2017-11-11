package tulingzh_CSCI201L_FP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

public class SendDiffEmail {
	public static void Mailer (String receiver, String student, String type) {
		
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		
		String school=new String();
		String fullname=new String();
		String email=new String();
		String studentschool=new String();
		String major=new String();
		String standing=new String();
		
		String to=receiver;
		String from="tulingzh@gmail.com";
		
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
	    	  		Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost/mydb?user=root&password=CS201&useSSL=false");
				st=conn.createStatement();
			
				if (receiver!=null && receiver.length()>0) {
					
					ps=conn.prepareStatement("SELECT * FROM Student WHERE Username=?");
					ps.setString(1, student);
					rs=ps.executeQuery();
					
				}
				
				if (rs.next()) {
					school=rs.getString("School");
					fullname=rs.getString("Fullname");
					email=rs.getString("Email");
					
					major=rs.getString("Major");
					standing=rs.getString("Standing");
					
				}
				
	      }catch (SQLException sqle) {
				System.out.println("sqle: "+sqle.getMessage());
			}catch (ClassNotFoundException cnfe) { //if you mistype string in line 31
				System.out.println("cnfe: "+cnfe.getMessage());
			}finally {
				try {
					if (rs!=null) {
						rs.close();
					}
					if (st!=null) {
						st.close();
					}
					if (conn!=null) {
						conn.close();
					}
				}catch(SQLException sqle) {
					System.out.println("sqle closing stuff: "+sqle.getMessage());
				}
			}

	      try {
		   // Create a default MimeMessage object.
		   Message message = new MimeMessage(session);
		
		   // Set From: header field of the header.
		   message.setFrom(new InternetAddress(from));
		
		   // Set To: header field of the header.
		   message.setRecipients(Message.RecipientType.TO,
	               InternetAddress.parse(to));
		
		   // Set Subject: header field
		   message.setSubject("Testing Subject");
		   
		   Multipart multipart=new MimeMultipart("alternative");
		   MimeBodyPart textPart=new MimeBodyPart();
		  
		   MimeBodyPart htmlPart=new MimeBodyPart();
		
		   // Now set the actual message
		   if (type.equals("congrats")) {
			   htmlPart.setContent("<html><h1>Congratulations!</h1><h3>The student is now connected to you</h3><br/>Name: "+fullname+"<br/>School: "+school+"<br/>email: "+email+"<br/>major: "+major+"<br/>standing: "+standing+"</html>","text/html");
			   multipart.addBodyPart(htmlPart);
		   }
		   
		   else {
			   System.out.println("should be here");
			   textPart.setText("We are sorry to inform you the student would like to remain unexposed to you.");
			   multipart.addBodyPart(textPart);	
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
