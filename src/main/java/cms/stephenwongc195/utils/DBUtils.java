package cms.stephenwongc195.utils;

import cms.stephenwongc195.controller.LoginController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBUtils {
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
        String sql = "select User_ID from users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            LoginController.userId = rs.getInt("User_ID");
        }
    }

    public static ResultSet tableQuery(String tableName) throws SQLException {
        JDBC.openConnection();
        String sql = "select * from " + tableName;
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    public static ResultSet divisionQuery() throws SQLException {
        JDBC.openConnection();
        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    public static ResultSet countryQuery() throws SQLException {
        JDBC.openConnection();
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    public static int insertCustomer(String customerName, String address, String postalCode, String phone, int divisionId)  {
       try {
            JDBC.openConnection();
            String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Created_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setString(5, LoginController.username);
            ps.setInt(6, divisionId);
            return ps.executeUpdate();
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           JDBC.closeConnection();
       }
       return 0;
    }



}
