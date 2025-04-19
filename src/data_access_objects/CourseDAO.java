package data_access_objects;
import java.sql.*;

public class CourseDAO {
	// An instance of Connection for accessing the database
	private Connection conn = MySQLConnection.getConnection();
	
	public void createCourseRecord(String course_name, String instructor, String semester) {
		String sql = String.format("INSERT INTO Courses "
				+ "(course_name, instructor, semester) "
				+ "VALUES ('%s', '%s', '%s');", course_name, instructor, semester);
		
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Course record created.");
			stmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteCourseRecord(int id) {
		String sql = String.format("DELETE FROM Courses "
				+ " WHERE courseID = %d;", id);
		
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Course record deleted.");
			stmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
