package data_access_objects;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data_structures.Enrollment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Data Accessing Object for our Enrollment table. Handling our create, update, delete operations.
 */
public class EnrollmentDAO {
	/** An instance of Connection for accessing the database*/
	private Connection conn = MySQLConnection.getConnection();
	
	/**
	 * Creates a new enrollment record in the database.
	 * @param studentID		the foreign key from the Student table
	 * @param courseID		the foreign key from the Course table
	 * @param year			the year of the enrollment
	 * @return
	 */
	public Enrollment createEnrollmentRecord(int studentID, int courseID, int year) {
		int enrollID = -1;
		String sql = String.format("INSERT INTO Enrollment "
				+ "(studentID, courseID, year) "
				+ "VALUES (%d, %d, %d);", studentID, courseID, year);
		
		try {
			Statement stmt = conn.createStatement();
			int rowsAffected = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            // If a record was inserted, retrieve the autogenerated courseID
            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    enrollID = generatedKeys.getInt(1);  // Set the id to the autogenerated courseID
                    System.out.println("Course record created with ID: " + enrollID);
                }
            }
			stmt.close();
			return new Enrollment(enrollID, studentID, courseID, year);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Deletes the enrollment record by a specified id
	 * @param id	unique enrollment id
	 */
	public void deleteEnrollmentRecord(int id) {
		String sql = String.format("DELETE FROM Enrollments "
				+ " WHERE enrollmentID = %d;", id);
		
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Enrollment record deleted.");
			stmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Updates an existing entry in the Enrollment table by the id
	 * @param id			unique enrollment id
	 * @param studentID		student id
	 * @param courseID		course id
	 * @param year			year of enrollment
	 */
	public void updateEnrollmentRecord(int id, int studentID, int courseID, int year) {
		String sql = String.format(
			    "UPDATE Enrollment SET studentID = %d, courseID = %d, year = %d WHERE enrollmentID = %d;",
			    studentID, courseID, year, id);  // Correct order

		
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Enrollment record updated.");
			stmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns a list of Enrollments containing records meeting the criteria
	 * @param cols		The columns we are searching for in the database (such as "courseID, course_name")
	 * @param cond		The conditions (such as "course_name = 'Math', instructor = 'Smith'")
	 * @return 
	 */
	public List<Enrollment> searchEnrollmentRecord(String cols, String cond) {
	    List<Enrollment> results = new ArrayList<>();
	    String sql = String.format("SELECT %s FROM Enrollment", cols);
	    if (!cond.isEmpty()) {
	        sql += " WHERE " + cond;
	    }

	    // Check what columns were requested
	    System.out.println(sql);
	    String lowerCols = cols.toLowerCase().replaceAll("\\s+", "");
	    boolean hasEnrollmentID = lowerCols.contains("enrollmentid") || lowerCols.equals("*");
	    boolean hasCourseID = lowerCols.contains("courseid") || lowerCols.equals("*");
	    boolean hasStudentID = lowerCols.contains("studentid") || lowerCols.equals("*");
	    boolean hasYear = lowerCols.contains("year") || lowerCols.equals("*");


	    try {
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);

	        while (rs.next()) {
	            int id = hasEnrollmentID ? rs.getInt("enrollmentID") : -1;
	            int courseID = hasCourseID ? rs.getInt("courseID") : -1;
	            int studentID = hasStudentID ? rs.getInt("studentID") : -1;
	            int year = hasYear ? rs.getInt("year") : -1;

	            results.add(new Enrollment(id, courseID, studentID, year));
	        }

	        rs.close();
	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return results;
	}
	
	/**
	 * Fetch all the enrollments in the Enrollment table
	 * @return	the list of Enrollment objects
	 */
	public static ObservableList<Enrollment> fetchAllEnrollments() {
		// TODO Auto-generated method stub
		ObservableList<Enrollment> enrollmentList = FXCollections.observableArrayList();
	    String sql = "SELECT * FROM Enrollment"; // Update this table name if needed

	    try (Connection conn = MySQLConnection.getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	        	int eID = rs.getInt("enrollmentID");
	            
	            int sID = rs.getInt("studentID");
	        	int cID = rs.getInt("courseID");
	        	int year = rs.getInt("year");

	            Enrollment enrollment = new Enrollment(eID, sID, cID, year);
	            enrollmentList.add(enrollment);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return enrollmentList;
	}
}
