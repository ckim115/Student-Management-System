package data_structures;

public class Enrollment {
	private int enrollID;
	private int studentID;
	private int courseID;
	private int year;
	
	public Enrollment(int eID, int sID, int cID, int y) {
		enrollID = eID;
		studentID = sID;
		courseID = cID;
		year = y;
	}
	
	public Enrollment(int id) {
		enrollID = id;
	}
	
	public int getStudentID() {
		return studentID;
	}
	
	public int getCourseID() {
		return courseID;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getEnrollmentID() {
		return enrollID;
	}
}
