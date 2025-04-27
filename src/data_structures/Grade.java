package data_structures;

/**
 * Creates and modifies a Grade object used to communicate with the database
 */
public class Grade {
	/** Unique grade ID */
	private int gradeID;;
	/** Student ID associated with the student (foreign key, Student) */
	private int studentID;
	/** Course ID associated with the student (foreign key, Course) */
	private int courseID;
	/** Grade score from 0-100 */
	private double grade;
	/** Semester of the grade */
	private String semester;
	
	/**
	 * Create a new Grade
	 * @param gradeID	unique grade ID
	 * @param sID		student ID
	 * @param cID		course ID
	 * @param grade		grade score from 0-100
	 * @param semester	semester of the grade
	 */
	public Grade(int gradeID, int sID, int cID, double grade, String semester) {
		this.gradeID = gradeID;
		studentID = sID;
		courseID = cID;
		this.grade = grade;
		this.semester = semester;
	}

	/**
	 * Create a new Grade
	 * @param id	unique grade ID
	 */
	public Grade(int id) {
		// TODO Auto-generated constructor stub
		gradeID = id;
	}
	
	/**
	 * Return student ID
	 * @return	student ID
	 */
	public int getId() {
		return studentID;
	}
	
	/**
	 * Return course ID
	 * @return	course ID
	 */
	public int getCourseid() {
		return courseID;
	}
	
	/**
	 * Return grade score
	 * @return	grade
	 */
	public double getGrade() {
		return grade;
	}
	
	/**
	 * Return semester
	 * @return	semester
	 */
	public String getSemesteryear() {
		return semester;
	}
	
	/**
	 * Return unique grade ID
	 * @return	grade ID
	 */
	public int getGradeid() {
		return gradeID;
	}
}
