import java.sql.*;

class MySQLConnection {
  public static void main(String[] args) {
	  String url = "jdbc:mysql://localhost:3306/sys";
	  String username = "root";
	  String password = "iloveangelina";
	  
	  try {
		  Connection conn = DriverManager.getConnection(url, username, password);
		  System.out.println("Database connected!");
		  
		  // Insert entry
		  Statement stmt = conn.createStatement();
		  stmt.executeUpdate("INSERT INTO Students (idStudent) VALUES (1)");
		  
		  // Execute query
		  ResultSet rs = stmt.executeQuery("SELECT idStudent FROM Students");
		  
		  // Display the SQL query results
		  while ( rs.next() ) {
			  System.out.println(rs.getInt("idStudent"));
		  }
		  System.out.println("Worked");
		  
		  rs.close();
		  stmt.close();
		  conn.close();
	  }
	  catch (SQLException se) {
		  throw new IllegalStateException("Cannot connect the database!", se);
      }
  }
}