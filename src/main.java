import java.sql.*;
import java.time.LocalDate;

import data_access_objects.CourseDAO;
import data_access_objects.StudentDAO;
import data_access_objects.EnrollmentDAO;

class MySQLConnection {
  public static void main(String[] args) {
	  // testing with student
	  StudentDAO sd = new StudentDAO();
	  CourseDAO cd = new CourseDAO();
	  EnrollmentDAO ed = new EnrollmentDAO();
	  sd.updateStudentRecord(4, "Angelina", "Kim", "2020-01-10");
	  // ed.createEnrollmentRecord(6, 2, 0);
	  // cd.createCourseRecord("a", "b", "c");
	  // cd.deleteCourseRecord(2);
	  // sd.createStudentRecord("Christina", "Kim", "2020-01-09");
	  // sd.deleteStudentRecord(3);
  }
}