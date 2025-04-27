package data_access_objects;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GradesDAO {
	private Connection conn = MySQLConnection.getConnection();
	

	private int gradeID;
	private int studentID;
	private int courseID;
	private double grade;
	private String semester;
	
	public GradesDAO(int gradeID, int sID, int cID, double grade, String semester) {
		this.gradeID = gradeID;
		studentID = sID;
		courseID = cID;
		this.grade = grade;
		this.semester = semester;
	}

	public GradesDAO(int id) {
		// TODO Auto-generated constructor stub
		gradeID = id;
	}

	// set relationship between existing class and student
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
	public List<GradesDAO> searchGradeRecord(String cols, String cond) {
        List<GradesDAO> results = new ArrayList<>();
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

                results.add(new GradesDAO(gradeID, studentID, courseID, grade, semester));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }
	
	public static ObservableList<GradesDAO> fetchAllGrades() {
	    ObservableList<GradesDAO> gradeList = FXCollections.observableArrayList();
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

	    		GradesDAO grades = new GradesDAO(gID, sID, cID, gradeVal, semesterGrade);
	    		gradeList.add(grades);
	    	}


	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return gradeList;
	}
	
	public int getId() {
		return studentID;
	}
	
	public int getCourseid() {
		return courseID;
	}
	
	public double getGrade() {
		return grade;
	}
	
	public String getSemesteryear() {
		return semester;
	}
	
	public int getGradeid() {
		return gradeID;
	}
}