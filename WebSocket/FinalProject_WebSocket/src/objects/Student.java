package objects;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Student extends Thread {
	private int StudentID;
	private String username; 
	private String password;
	private String fullName; 
	private String schoolEmail;
	private String school;
	private String major;
	private String standing;
	private List<Integer> connectedRecruiterIDs;
	private List<Integer> uploadedProjectIDs;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	public Student() {

	}
	
	public void run() {
		
		
	}
	public void setId(int id) {
		this.StudentID = id; 
	}
	
	public long getId() {
		return this.StudentID; 
	}
	
	public void main(String [] args) {
		Student s = new Student();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getStudentID() {
		return StudentID;
	}
	public void setStudentID(int studentID) {
		StudentID = studentID;
	}
	public String getSchoolEmail() {
		return schoolEmail;
	}
	public void setSchoolEmail(String schoolEmail) {
		this.schoolEmail = schoolEmail;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getStanding() {
		return standing;
	}
	public void setStanding(String standing) {
		this.standing = standing;
	}
	public List<Integer> getConnectedRecruiterIDs() {
		return connectedRecruiterIDs;
	}
	public void setConnectedRecruiterIDs(List<Integer> connectedRecruiterIDs) {
		this.connectedRecruiterIDs = connectedRecruiterIDs;
	}
	public List<Integer> getUploadedProjectIDs() {
		return uploadedProjectIDs;
	}
	public void setUploadedProjectIDs(List<Integer> uploadedProjectIDs) {
		this.uploadedProjectIDs = uploadedProjectIDs;
	}
	
}
