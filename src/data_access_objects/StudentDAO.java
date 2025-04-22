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

	/**
	 * @param cols		The columns we are searching for in the database (such as "courseID, course_name")
	 * @param cond		The conditions (such as "course_name = 'Math', instructor = 'Smith'")
	 */
	public void searchStudentRecord(String cols, String cond) {
		String sql = String.format("SELECT %s FROM Students", cols);
		if (cond.length() > 0) {
			sql += String.format(" WHERE %s", cond);
		}
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString("studentID"));
			}
			
			System.out.println("Student records returned.");
			rs.close();
			stmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
