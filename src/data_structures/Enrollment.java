package data_structures;

/**
 * Creates and modifies a Enrollment object used to communicate with the database
 */
public class Enrollment {
	/** Unique enrollment ID */
	private int enrollID;
	/** Student ID associated with the enrollment (foreign key, Student) */
	private int studentID;
	/** Course ID associated with the enrollment (foreign key, Course) */
	private int courseID;
	/** Year of the enrollment */
	private int year;
	
	/**
	 * Creates a new Enrollment
	 * @param eID	unique enrollment ID
	 * @param sID	student ID
	 * @param cID	course ID
	 * @param y		year
	 */
	public Enrollment(int eID, int sID, int cID, int y) {
		enrollID = eID;
		studentID = sID;
		courseID = cID;
		year = y;
	}
	
	/**
	 * Creates a new Enrollment
	 * @param id	unique enrollment ID
	 */
	public Enrollment(int id) {
		enrollID = id;
	}
	
	/**
	 * Returns student id
	 * @return	student ID associated with the enrollment
	 */
	public int getStudentID() {
		return studentID;
	}

	/**
	 * Returns course id
	 * @return	course ID associated with the enrollment
	 */
	public int getCourseID() {
		return courseID;
	}
	

	/**
	 * Returns year of enrollment
	 * @return	year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Returns enrollment id
	 * @return	enrollment id
	 */
	public int getEnrollmentID() {
		return enrollID;
	}
}
