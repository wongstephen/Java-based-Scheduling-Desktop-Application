package cms.stephenwongc195.utils;

import cms.stephenwongc195.controller.LoginController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * RUNTIME ERROR: Exception in thread "JavaFX Application Thread" java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
 *
 */
public class DB {
    public static boolean login(String userName, String password) throws SQLException {
        Boolean login = false;
        JDBC.openConnection();
        String sql = "select * from users where User_Name = ? and Password = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, userName);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public static void getUserId() throws SQLException {
        JDBC.openConnection();
        String sql = "select User_ID from users where User_Name = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, LoginController.username);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            LoginController.userId = rs.getInt("User_ID");
        }
    }

    public static ResultSet tableQuery(String tableName) throws SQLException {
        JDBC.openConnection();
        String sql = "select * from " + tableName + " where User_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, String.valueOf(LoginController.userId));
        ResultSet rs = ps.executeQuery();
        return rs;
    }
}
