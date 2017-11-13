package servlets;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.Project;

@WebServlet("/ViewProjects")
public class ViewProjects extends HttpServlet {
	public static final long serialVersionUID = 1;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Connection conn = null;
	    Statement st = null;
	    ResultSet rs = null;
	    HttpSession session = request.getSession();
	    try {
	    		Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?user=root&password=root&useSSL=false");
	        st = conn.createStatement();
	        
	        //get all students from database and make map of id to student name
	        	rs = st.executeQuery("SELECT * from Student");
	        HashMap<Integer, String> students = new HashMap<Integer, String>();
	        while (rs.next()) {
		        	String fname = rs.getString("Fname");
		        	String lname = rs.getString("Lname");
		        	String name = fname + " " + lname;
		        	Integer studentID = rs.getInt("StudentID");
//		        	System.out.println ("Name = " + name);
//		        	System.out.println ("studentID = " + studentID);
//		        	System.out.println("");
		        	students.put(studentID, name);
	        }
	        session.setAttribute("students", students);
	        
	        if (rs != null) {
    				rs.close();
    			}
	        
	        //get all projects from database
	        rs = st.executeQuery("SELECT * from Projects");
	        HashMap<Integer, Project> projects = new HashMap<Integer, Project>();
	        ArrayList<Project> projectList = new ArrayList<Project>();
	        while (rs.next()) {
	        		String name = rs.getString("Name");
	        		String description = rs.getString("Description");
	        		String category = rs.getString("category");
	        		Integer studentID = rs.getInt("StudentID");
	        		Integer projectID = rs.getInt("projectID");
	        		Project project = new Project(name, category, description, studentID, projectID);
	        		projects.put(projectID, project);
//	        		System.out.println("Project title:" + name);
//	        		System.out.println("Project category:" + category);
//	        		System.out.println("Project description:" + description);
//	        		System.out.println("Project ID:" + projectID);
//	        		System.out.println("Project studentID:" + studentID);
//	        		System.out.println("");
	        }
	 	        
	        if (rs != null) {
				rs.close();
			}
	        
	        //get all project images from databases
	        rs = st.executeQuery("SELECT * from Image");
	        while (rs.next()) {
	        		String image = rs.getString("ImageURL");
	        		Integer projectID = rs.getInt("fk_Image_Project_ID");
	        		projects.get(projectID).setImage(image);
	        		projectList.add(projects.get(projectID));
//	        		System.out.println("image: " + image);
//	        		System.out.println("image projectID:" + projectID);
//	        		System.out.println("");
	        }
	     
	        session.setAttribute("projects", projects);
	        session.setAttribute("projectList", projectList);
	    } 
	    catch (SQLException sqle) {
	    		System.out.println (sqle.getMessage());
	    } 
	    catch (ClassNotFoundException cnfe) {
	    		System.out.println (cnfe.getMessage());
	    } 
	    finally {
		    	try {
		    		if (rs != null) {
		    			rs.close();
		    		}
		    		if (st != null) {
		    			st.close();
		    		}
		    		if (conn != null) {
		    			conn.close();
		    		}
		    	} 
		    	catch (SQLException sqle) {
		    		System.out.println(sqle.getMessage());
		    	}
	    }  

	    String nextJSP = "/WEB-INF/projectlist.jsp";
		request.getRequestDispatcher(nextJSP).forward(request, response);
	}
}