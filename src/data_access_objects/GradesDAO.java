package data_access_objects;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GradesDAO {
	private Connection conn = MySQLConnection.getConnection();
	
	// set relationship between existing class and student
	public void createGradeRecord(int studentID, int courseID, double grade, String semester) {
		String sql = String.format("INSERT INTO Grades "
				+ "(studentID, courseID, grade, semester) "
				+ "VALUES (%d, %d, %d, '%s');", studentID, courseID, grade, semester);
		
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Grade record created.");
			stmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteGradeRecord(int id) {
		String sql = String.format("DELETE FROM Grades "
				+ " WHERE gradeID = %d;", id);
		
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Grades record deleted.");
			stmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateGradeRecord(int id, int studentID, int courseID, double grade, String semester) {
		String sql = String.format("UPDATE Grades "
				+ " SET studentID = %d, courseID = %d, grade = %d, semester = '%s'"
				+ " WHERE gradeID = %d;", id, studentID, courseID, grade, semester);
		
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Grade record updated.");
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
	public void searchGradeRecord(String cols, String cond) {
		String sql = String.format("SELECT %s FROM Grades", cols);
		if (cond.length() > 0) {
			sql += String.format(" WHERE %s", cond);
		}
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString("gradeID"));
			}
			
			System.out.println("Grade records returned.");
			rs.close();
			stmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}