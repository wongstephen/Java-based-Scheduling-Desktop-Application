package cms.stephenwongc195.dao;

import cms.stephenwongc195.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

import static cms.stephenwongc195.dao.Query.tableQuery;

public class CustomerDao {
    static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    public static void addCustomer(Customer customer) {
        allCustomers.add(customer);
    }

    public static ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    }

    public static void deleteCustomer(Customer customer) {
        allCustomers.remove(customer);
    }

    public static void updateAllCustomers() {
        allCustomers.clear();
        try {
            ResultSet rs = tableQuery("customers");
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"),
                        rs.getString("Address"),
                        rs.getString("Postal_Code"),
                        rs.getString("Phone"),
                        rs.getInt("Division_ID")
                );
                allCustomers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static {
        updateAllCustomers();
    }
}

