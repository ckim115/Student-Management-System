package data_structures;

public class Student {
	private String firstname;
	private String lastname;
	private String birth;
	private int id;
	
	public Student(int id, String first, String last, String birth) {
		// TODO Auto-generated constructor stub
		firstname = first;
		lastname = last;
		this.birth = birth;
		this.id = id;
	}

	public Student(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}
	
	
	public int getId() {
	    return id;
	}

	public String getFirstName() {
	    return firstname;
	}

	public String getLastName() {
	    return lastname;
	}

	public String getBirthDate() {
	    return birth;
	}
}
