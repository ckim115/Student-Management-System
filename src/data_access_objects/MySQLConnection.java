package data_access_objects;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Creates a connection with the database
 */
public class MySQLConnection {
	/** database URL */
    private static final String URL = "jdbc:mysql://localhost:3306/sys";
    /** username */
    private static final String USER = "root";
    /** password */
    private static final String PASSWORD = "password";

    /**
     * Establishes the connection to the database
     * @return	successful connection or null if failed
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
            return null;
        }
    }
}