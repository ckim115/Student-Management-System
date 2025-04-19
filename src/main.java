import java.sql.*;
import java.time.LocalDate;

import data_access_objects.CourseDAO;
import data_access_objects.StudentDAO;

class MySQLConnection {
  public static void main(String[] args) {
	  // testing with student
	  // StudentDAO sd = new StudentDAO();
	  CourseDAO cd = new CourseDAO();
	  
	  // cd.createCourseRecord("a", "b", "c");
	  cd.deleteCourseRecord(1);
	  // sd.createStudentRecord("Christina", "Kim", "2020-01-09", 2020);
	  //sd.deleteStudentRecord(3);
  }
}