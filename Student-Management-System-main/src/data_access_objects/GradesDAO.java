package data_access_objects;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data_structures.Grade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Data Access Object for the GradesController class
 */
public class GradesDAO {
	/** An instance of Connection for accessing the database*/
	private Connection conn = MySQLConnection.getConnection();
	
	/**
	 * Creates a new grade record in the database
	 * @param studentID		the foreign key from the Student table
	 * @param courseID		the foreign key from the Course table
	 * @param grade			the grade between 0-100 for the specific student-course grade record
	 * @param semester		the semester (spring, fall, summer, winter) the course takes place in
	 */
	public void createGradeRecord(int studentID, int courseID, double grade, String semester) {
		String sql = String.format("INSERT INTO Grades "
				+ "(studentID, courseID, grade, semester) "
				+ "VALUES (%d, %d, %.2f, '%s');", studentID, courseID, grade, semester);
		
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
	
	/**
	 * Deletes the grade record by a specified id
	 * @param id	unique id of the grade record
	 */
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
	
	/**
	 * Update an existing grade record by a specified id
	 * @param id			unique id of the grade record
	 * @param studentID		the foreign key from the Student table
	 * @param courseID		the foreign key from the Course table
	 * @param grade			the grade between 0-100 for the specific student-course grade record
	 * @param semester		the semester (spring, fall, summer, winter) the course takes place in
	 */
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
	 * Returns a list of grade records meeting a given criteria
	 * @param cols		the columns we are searching for in the database (such as "courseID, course_name")
	 * @param cond		the conditions (such as "course_name = 'Math', instructor = 'Smith'")
	 * @return			the list of Grade objects meeting the criteria
	 */
	public List<Grade> searchGradeRecord(String cols, String cond) {
        List<Grade> results = new ArrayList<>();
        String sql = String.format("SELECT %s FROM Grades", cols);
        if (!cond.isEmpty()) {
            sql += " WHERE " + cond;
        }

        String lowerCols = cols.toLowerCase().replaceAll("\\s+", "");
        boolean hasGradeID = lowerCols.contains("gradeid") || lowerCols.equals("*");
        boolean hasStudentID = lowerCols.contains("studentid") || lowerCols.equals("*");
        boolean hasCourseID = lowerCols.contains("courseid") || lowerCols.equals("*");
        boolean hasGrade = lowerCols.contains("grade") || lowerCols.equals("*");
        boolean hasSemester = lowerCols.contains("semester") || lowerCols.equals("*");

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int gradeID = hasGradeID ? rs.getInt("gradeID") : -1;
                int studentID = hasStudentID ? rs.getInt("studentID") : -1;
                int courseID = hasCourseID ? rs.getInt("courseID") : -1;
                double grade = hasGrade ? rs.getDouble("grade") : -1.0;
                String semester = hasSemester ? rs.getString("semester") : null;

                results.add(new Grade(gradeID, studentID, courseID, grade, semester));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

	/**
	 * Fetch all the grades in the Grade table
	 * @return	the list of Grade objects
	 */
	public static ObservableList<Grade> fetchAllGrades() {
	    ObservableList<Grade> gradeList = FXCollections.observableArrayList();
	    String sql = "SELECT * FROM Grades"; // Update this table name if needed

	    try (Connection conn = MySQLConnection.getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	    	while (rs.next()) {
	    		int gID = rs.getInt("gradeID");
	    		int sID = rs.getInt("studentID");
	    		int cID = rs.getInt("courseID");
	    		double gradeVal = rs.getDouble("grade");
	    		String semesterGrade = rs.getString("semester");

	    		Grade grades = new Grade(gID, sID, cID, gradeVal, semesterGrade);
	    		gradeList.add(grades);
	    	}


	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return gradeList;
	}
}