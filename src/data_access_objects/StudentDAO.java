package data_access_objects;
import java.sql.*;

public class StudentDAO {
	// An instance of Connection for accessing the database
	private Connection conn = MySQLConnection.getConnection();
	
	public void createStudentRecord(String fname, String lname, String date, int year) {
		String sql = String.format("INSERT INTO Students "
				+ "(first_name, last_name, birthdate, year) "
				+ "VALUES ('%s', '%s', '%s', %d);", fname, lname, date, year);
		
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Student record created.");
			stmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteStudentRecord(int id) {
		String sql = String.format("DELETE FROM Students "
				+ " WHERE studentID = %d;", id);
		
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Student record deleted.");
			stmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
