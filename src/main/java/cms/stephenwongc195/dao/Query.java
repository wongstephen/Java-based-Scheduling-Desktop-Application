package cms.stephenwongc195.dao;

import cms.stephenwongc195.controller.LoginController;
import cms.stephenwongc195.dao.JDBC;
import cms.stephenwongc195.utils.Context;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Query {
    public static ResultSet login(String userName, String password) throws SQLException {
        Boolean login = false;
        JDBC.openConnection();
        String sql = "select * from users where User_Name = ? and Password = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, userName);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        return rs;
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
            ps.setString(5, Context.getUserName());
            ps.setInt(6, divisionId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBC.closeConnection();
        }
        return 0;
    }

    public static int updateCustomer(String customerName, String address, String postalCode, String phone, int divisionId, int customerId)  {
        try {
            JDBC.openConnection();
            String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setInt(5, divisionId);
            ps.setInt(6, customerId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBC.closeConnection();
        }
        return 0;
    }

    public static int deleteCustomer (int customerId) {
        try {
            JDBC.openConnection();
            String sql = "DELETE FROM customers WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBC.closeConnection();
        }
        return 0;
    }

    public static int deleteAppointment (int appointmentId) {
        try {
            JDBC.openConnection();
            System.out.println("Deleteing apppointment...");
            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, appointmentId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBC.closeConnection();
        }
        return 0;
    }

    public static boolean associatedAppointmentsByCustomer(int customerId) {
        try {
            JDBC.openConnection();
            String sql = "SELECT * FROM customers JOIN appointments ON customers.customer_id = appointments.customer_id where customers.customer_id = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBC.closeConnection();
        }
        return false;
    }

    public static int insertAppointment(String title, String description, String location, String type, String start, String end, int customerId, int userId, int contactId) {
        try {
            JDBC.openConnection();
            String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, NOW(), ?, NOW(), ?, ?, ?, ?)";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setString(5, start);
            ps.setString(6, end);
            ps.setString(7, Context.getUserName());
            ps.setString(8, Context.getUserName());
            ps.setInt(9, customerId);
            ps.setInt(10, userId);
            ps.setInt(11, contactId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBC.closeConnection();
        }
        return 0;
    }public static int updateAppointment(String title, String description, String location, String type, String start, String end, int customerId, int userId, int contactId, int appointmentId) {
        try {
            JDBC.openConnection();
            String sql = " UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = NOW(), Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ?  WHERE appointment_id = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setString(5, start);
            ps.setString(6, end);
            ps.setString(7, Context.getUserName());
            ps.setInt(8, customerId);
            ps.setInt(9, userId);
            ps.setInt(10, contactId);
            ps.setInt(11, appointmentId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBC.closeConnection();
        }
        return 0;
    }
}
