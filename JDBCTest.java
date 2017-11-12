import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {

	public static void main (String[] args) {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");			
			try {
				conn = DriverManager.getConnection("jdbc:mysql://mydbinstance.cwkjzjcxdcml.us-east-2.rds.amazonaws.com:3306/mydb?user=cs201&password=finalproject&useSSL=false");
				st = conn.createStatement();
			} catch(Exception e) {
				e.printStackTrace();
			}
			String name = "Praneeth";
			// rs = st.executeQuery("SELECT * from Student where fname='" + name + "'");
			ps = conn.prepareStatement("SELECT * FROM Student WHERE fname=?");
			ps.setString(1, name); // set first variable in prepared statement
			rs = ps.executeQuery();
			while (rs.next()) {
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				int studentID = rs.getInt("StudentID");
				System.out.println ("fname = " + fname);
				System.out.println ("lname = " + lname);
				System.out.println ("studentID = " + studentID);
			}
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println ("ClassNotFoundException: " + cnfe.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
	}
}