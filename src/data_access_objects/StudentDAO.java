package data_access_objects;
import java.sql.*;

public class StudentDAO {
	// An instance of Connection for accessing the database
	private Connection conn = MySQLConnection.getConnection();
	
	public void createStudentRecord(String fname, String lname, String date) {
		String sql = String.format("INSERT INTO Students "
				+ "(first_name, last_name, birthdate) "
				+ "VALUES ('%s', '%s', '%s');", fname, lname, date);
		
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
	
	public void updateStudentRecord(int id, String fname, String lname, String date) {
		String sql = String.format("UPDATE Students "
				+ " SET first_name = '%s', last_name = '%s', birthdate = '%s'"
				+ " WHERE studentID = %d;", fname, lname, date, id);
		
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Student record updated.");
			stmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
