package cms.stephenwongc195.dao;

import cms.stephenwongc195.utils.Context;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class contains methods for querying the database.
 */
public class Query {
    /**
     * This method queries the database for a user with the given username and password.
     * @param userName
     * @param password
     * @return
     * @throws SQLException
     */
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

    /**
     * This method queries the database for a table indicated by the param
     * @param tableName
     * @return ResultSet of table
     * @throws SQLException
     */
    public static ResultSet tableQuery(String tableName) throws SQLException {
        JDBC.openConnection();
        String sql = "select * from " + tableName;
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        return rs;
    }


    /**
     * This method inserts customer to the database.
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param divisionId
     * @return int of rows affected
     */
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

    /**
     * This method updates customer in the database.
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param divisionId
     * @return int of rows affected
     */
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

    /**
     * This method inserts appointment to the database.
     * @param customerId
     * @return int of rows affected
     */
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

    /**
     * THis method deletes appointment from the database.
     * @param appointmentId
     * @return
     */
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

    /**
     * THis method checks if there are appointments associated with the customer.
     * @param customerId
     * @return boolean if there are appointments associated with the customer
     */
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

    /**
     * This method inserts appointment to the database.
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param customerId
     * @param userId
     * @param contactId
     * @return int of rows affected
     */
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
    }


    /**
     * This method updates appointment in the database.
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param customerId
     * @param userId
     * @param contactId
     * @param appointmentId
     * @return returns int of rows affected
     */
    public static int updateAppointment(String title, String description, String location, String type, String start, String end, int customerId, int userId, int contactId, int appointmentId) {
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

    /**
     * This method querys the db for customers by month/year
     */
    public static ResultSet queryTypeCountByMonth() {
        ResultSet rs = null;
        try {
            JDBC.openConnection();
            String sql = "SELECT DATE_FORMAT(start, '%Y-%m') AS month, type, COUNT(*) AS num_appointments FROM appointments GROUP BY month, type";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
             rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet appointmentsByCustomer() {
        ResultSet rs = null;
        try {
            JDBC.openConnection();
            String sql = "SELECT customers.Customer_Name, count(*) FROM appointments JOIN customers ON customers.Customer_ID = appointments.Customer_ID WHERE appointments.start > CURDATE() group by customers.customer_name;";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace() ;
        }
        return rs;
    }
}
