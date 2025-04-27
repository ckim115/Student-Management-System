package data_structures;

/**
 * Creates and modifies a Student object used to communicate with the database
 */
public class Student {
	/** Student first name */
	private String firstname;
	/** Student last name */
	private String lastname;
	/** Student birthdate */
	private String birth;
	/** Unique student ID */
	private int id;
	
	/**
	 * Create a new Student
	 * @param id		unique student ID
	 * @param first		first name
	 * @param last		last name
	 * @param birth		birthdate
	 */
	public Student(int id, String first, String last, String birth) {
		// TODO Auto-generated constructor stub
		firstname = first;
		lastname = last;
		this.birth = birth;
		this.id = id;
	}

	/**
	 * Create a new Student
	 * @param id	unique student ID
	 */
	public Student(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}
	
	/**
	 * Return student ID
	 * @return	unique student ID
	 */
	public int getId() {
	    return id;
	}

	/**
	 * Return first name
	 * @return	first name
	 */
	public String getFirstName() {
	    return firstname;
	}

	/**
	 * Return last name
	 * @return	last name
	 */
	public String getLastName() {
	    return lastname;
	}

	/**
	 * Return birth date
	 * @return	birth date
	 */
	public String getBirthDate() {
	    return birth;
	}
}
