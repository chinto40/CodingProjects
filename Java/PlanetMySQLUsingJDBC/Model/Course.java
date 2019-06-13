package Model;

public class Course {

	private int courseID;
	private String facultyID;
	private String courseName;
	private String semester;
	private int section;
	
	// might need to keep an arraylist of student.
	
	public Course(int cId, String fID, String cName, String sem, int sec) {
		this.courseID = cId;
		this.facultyID = fID;
		this.courseName = cName;
		this.semester = sem;
		this.section = sec;
	}
	
	public Course() {
		this.courseID = 0;
		this.facultyID = "xx";
		this.courseName = "Default";
		this.semester = "Default";
		this.section = 0;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getFacultyID() {
		return facultyID;
	}

	public void setFacultyID(String facultyID) {
		this.facultyID = facultyID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}
	
	
}
