package data_structures;

public class Course {
	private String course;
	private String teacher;
	private String semester;
	private int id;

	public Course(int id, String course, String teacher, String semester) {
		this.id = id;
		this.course = course;
		this.teacher = teacher;
		this.semester = semester;
	}

	public Course(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}
	
	public int getId() {
	    return id;
	}

	public String getCourseName() {
	    return course;
	}

	public String getInstructor() {
	    return teacher;
	}

	public String getSemester() {
	    return semester;
	}
}
