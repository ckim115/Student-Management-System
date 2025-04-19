package data_access_objects;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EnrollmentDAO {
	private Connection conn = MySQLConnection.getConnection();
	
	// set relationship between existing class and student
	public void createEnrollmentRecord(int studentID, int courseID, int year) {
		String sql = String.format("INSERT INTO Enrollments "
				+ "(studentID, courseID, year) "
				+ "VALUES (%d, %d, %d);", studentID, courseID, year);
		
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Enrollment record created.");
			stmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteEnrollmentRecord(int id) {
		String sql = String.format("DELETE FROM Enrollment "
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
}
