package tulingzh_CSCI201L_FP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendCongratsServlet
 */
@WebServlet("/SendCongratsServlet")
public class SendCongratsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		
		String email=new String();
		
		
		String type=request.getParameter("type");
		String receiver=request.getParameter("recruiter");
		 try {
 	  		Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost/mydb?user=root&password=CS201&useSSL=false");
			st=conn.createStatement();
		
			if (receiver!=null && receiver.length()>0) {
				
				ps=conn.prepareStatement("SELECT * FROM Recruiter WHERE Username=?");
				ps.setString(1, receiver);
				rs=ps.executeQuery();
				
			}
			
			if (rs.next()) {
				
				email=rs.getString("Email");
				
				
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

		//will get from current recruiter login session variable
		String student=(String) request.getSession().getAttribute("Username");
		//will get from user session variable
		
		SendDiffEmail.Mailer(email, student, type);
		
	}



}
