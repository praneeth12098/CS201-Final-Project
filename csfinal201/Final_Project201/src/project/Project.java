package project;

public class Project {
	private String name;
	private String category;
	private String description;
	private Integer studentID;
	private Integer projectID;
	private String image;
	
	public Project(String name, String category, String description, Integer studentID, Integer projectID) {
		this.name = name;
		this.category = category;
		this.description = description;
		this.studentID = studentID;
		this.projectID = projectID;
	}
	
	public Integer getStudentID() {
		return studentID;
	}
	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}
	public Integer getProjectID() {
		return projectID;
	}
	public void setProjectID(Integer projectID) {
		this.projectID = projectID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
