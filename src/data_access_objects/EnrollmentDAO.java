package data_access_objects;

import java.sql.Connection;
import java.sql.ResultSet;
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
	
	public void updateEnrollmentRecord(int id, int studentID, int courseID, int year) {
		String sql = String.format("UPDATE Enrollments "
				+ " SET studentID = %d, courseID = %d, year = %d"
				+ " WHERE enrollmentID = %d;", id, studentID, courseID, year);
		
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
	 * @param cols		The columns we are searching for in the database (such as "courseID, course_name")
	 * @param cond		The conditions (such as "course_name = 'Math', instructor = 'Smith'")
	 */
	public void searchEnrollmentRecord(String cols, String cond) {
		String sql = String.format("SELECT %s FROM Enrollments", cols);
		if (cond.length() > 0) {
			sql += String.format(" WHERE %s", cond);
		}
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString("enrollmentID"));
			}
			
			System.out.println("Enrollment records returned.");
			rs.close();
			stmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
