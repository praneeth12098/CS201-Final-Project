package tulingzh_CSCI201L_FP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class SendEmailServlet
 */
@WebServlet("/SendEmailServlet")
public class SendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		String receiver=request.getParameter("username");
		String realReceiver=new String();
		
		
		
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost/mydb?user=root&password=CS201&useSSL=false");
			st=conn.createStatement();
		
			if (receiver!=null && receiver.length()>0) {
				
				ps=conn.prepareStatement("SELECT * FROM Student WHERE Username=?");
				ps.setString(1, receiver);
				rs=ps.executeQuery();
				
			}
			else {
			
				ps=conn.prepareStatement("SELECT * FROM Student");
				rs=ps.executeQuery();
				
			}
			
			
			
			if (rs.next()) {
				realReceiver=rs.getString("Email");
			}
			
			int index=realReceiver.indexOf("@");
			realReceiver=realReceiver.substring(0, index-1);
			
			
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
		String content=request.getParameter("content");
		System.out.println("I get here");
		SendEmail.Mailer(realReceiver,content);
	}


}
