package data_structures;

public class Grade {
	private int gradeID;
	private int studentID;
	private int courseID;
	private double grade;
	private String semester;
	
	public Grade(int gradeID, int sID, int cID, double grade, String semester) {
		this.gradeID = gradeID;
		studentID = sID;
		courseID = cID;
		this.grade = grade;
		this.semester = semester;
	}

	public Grade(int id) {
		// TODO Auto-generated constructor stub
		gradeID = id;
	}
	
	public int getId() {
		return studentID;
	}
	
	public int getCourseid() {
		return courseID;
	}
	
	public double getGrade() {
		return grade;
	}
	
	public String getSemesteryear() {
		return semester;
	}
	
	public int getGradeid() {
		return gradeID;
	}
}
