package data_structures;

/**
 * Creates and modifies a Course object used to communicate with the database
 */
public class Course {
	/** Course name */
	private String course;
	/** Instructor name */
	private String teacher;
	/** Semester of the course */
	private String semester;
	/** Unique course id */
	private int id;

	/**
	 * Creates a new Course
	 * @param id			Unique course id
	 * @param course		Course name
	 * @param teacher		Instructor name
	 * @param semester		Semester of the course
	 */
	public Course(int id, String course, String teacher, String semester) {
		this.id = id;
		this.course = course;
		this.teacher = teacher;
		this.semester = semester;
	}

	/**
	 * Creates a new Course
	 * @param id	Unique course id
	 */
	public Course(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}
	
	/**
	 * Returns course id
	 * @return	id associated with the course
	 */
	public int getId() {
	    return id;
	}

	/**
	 * Returns course name
	 * @return	course name
	 */
	public String getCourseName() {
	    return course;
	}

	/**
	 * Returns instructor name
	 * @return	instructor name
	 */
	public String getInstructor() {
	    return teacher;
	}

	/**
	 * Returns semester
	 * @return	semester
	 */
	public String getSemester() {
	    return semester;
	}
}
