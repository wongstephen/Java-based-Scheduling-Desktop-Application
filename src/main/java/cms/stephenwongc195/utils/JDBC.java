package cms.stephenwongc195.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class JDBC {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    public static Connection connection;  // Connection Interface
    private static final String password = "Passw0rd!"; // Password

    /**
     * Open Connection method to open MySQL DB Connection
     */
    public static void openConnection() {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
        } catch (Exception e) {
            System.out.println("JDBC openConnection Error: " + e.getMessage());
            System.out.println(e.getCause());
        }
    }

    /**
     * Close Connection method to close MySQL DB Connection
     */
    public static void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("JDBC closeConnection Error: " + e.getMessage());
        }
    }
}
