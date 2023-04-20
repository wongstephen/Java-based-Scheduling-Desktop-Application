package cms.stephenwongc195.controller;

import cms.stephenwongc195.model.Appointment;
import cms.stephenwongc195.model.Customer;
import cms.stephenwongc195.utils.Navigate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;

import static cms.stephenwongc195.controller.LoginController.globalLocale;
import static cms.stephenwongc195.utils.DB.tableQueryById;

public class HomeController implements Initializable {
    public Label home___title;

    public TableView appointmentTable;
    public TableColumn appointmentIdCol;
    public TableColumn appointmentTitleCol;
    public TableColumn appointmentDescriptionCol;
    public TableColumn appointmentLocationCol;
    public TableColumn appointmentTypeCol;
    public TableColumn appointmentStartCol;
    public TableColumn appointmentEndCol;
    public TableColumn appointmentCustomerIdCol;
    public TableColumn appointmentContactCol;
    public TableColumn appointmentUserIdCol;
    public Label welcomeUserLabel;
    ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    ObservableList<Appointment> filteredAppointments = FXCollections.observableArrayList();

    public TableView customerTable;
    public TableColumn customerIdCol;
    public TableColumn customerNameCol;
    public TableColumn customerAddressCol;
    public TableColumn customerPostalCodeCol;
    public TableColumn customerPhoneCol;
    public TableColumn customerDivisionIdCol;
    ObservableList<Customer> customers = FXCollections.observableArrayList();

    public RadioButton apptMonthRadio;
    public RadioButton apptWeekRadio;
    public RadioButton apptAllRadio;

    @FXML
    private Label homeZoneIdLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (globalLocale.contains("fr")) home___title.setText("Système de gestion de la clientèle"); // French Locale custom title
        getAppointments(); // Get appointments from DB and populates appointments table
        getCustomers(); // Get customers from DB and populates customers table
        welcomeUserLabel.setText("Welcome, " + LoginController.username); // Welcome username on home screen
        homeZoneIdLabel.setText(ZonedDateTime.now().getZone().toString()); // Set Zone ID label
    }

    /**
     * On load, populates the customer table with all appointments from the DB
     */
    public void getAppointments() {
        appointments.clear();
        try {
            ResultSet rs = tableQueryById("appointments");
            while(rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        rs.getTimestamp("Start").toLocalDateTime(),
                        rs.getTimestamp("End").toLocalDateTime(),
                        rs.getInt("Customer_Id"),
                        rs.getInt("User_Id"),
                        rs.getInt("Contact_Id")
                );
                appointments.add(appointment);
            }
            appointmentTable.setItems(appointments);
            appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            appointmentTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
            appointmentDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
            appointmentLocationCol.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
            appointmentContactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
            appointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
            appointmentStartCol.setCellValueFactory(new PropertyValueFactory<>("appointmentStartFormatted"));
            appointmentEndCol.setCellValueFactory(new PropertyValueFactory<>("appointmentEndFormatted"));
            appointmentCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            appointmentUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * On load, populates the customer table with all customers from the DB
     */
    public void getCustomers() {
        customers.clear();
        try {
            ResultSet rs = tableQueryById("customers");
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"),
                        rs.getString("Address"),
                        rs.getString("Postal_Code"),
                        rs.getString("Phone"),
                        rs.getInt("Division_ID")
                );
                customers.add(customer);
            }
            customerTable.setItems(customers);
            customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
            customerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
            customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
            customerDivisionIdCol.setCellValueFactory(new PropertyValueFactory<>("customerDivisionId"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles the appointment month radio button click event and updates the customer table
     *
     * @param actionEvent
     */
    public void handleMonthApptView(ActionEvent actionEvent) {
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        filteredAppointments.clear();
        appointments.forEach(appointment -> {
            if (appointment.getAppointmentStart().getMonthValue() == month && appointment.getAppointmentStart().getYear() == year) {
                filteredAppointments.add(appointment);
                System.out.println("Appointment added to filtered list");
            }
        });
        appointmentTable.setItems(filteredAppointments);
    }

    /**
     * Handles the appointment week radio button click event and updates the customer table
     *
     * @param actionEvent
     */
    public void handleWeekApptView(ActionEvent actionEvent) {
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int week = (now.getDayOfYear() / 7) + 1;
        System.out.println("Week: " + week);
        filteredAppointments.clear();
        appointments.forEach(appointment -> {
            if (appointment.getAppointmentStart().getDayOfYear() / 7 + 1 == week && appointment.getAppointmentStart().getYear() == year) {
                filteredAppointments.add(appointment);
            }
        });
        appointmentTable.setItems(filteredAppointments);
    }

    /**
     * Handles the appointment all radio button click event and updates the customer table
     *
     * @param actionEvent
     */
    public void handleAllApptView(ActionEvent actionEvent) {
        appointmentTable.setItems(appointments);
    }

    public void handleAddCustomerBtn(ActionEvent actionEvent) throws IOException {
        Navigate.navigate(actionEvent, "addCustomer");
    }

    public void handleUpdateCustomerBtn(ActionEvent actionEvent) {
    }

    public void handleDeleteCustomerBtn(ActionEvent actionEvent) {
    }

    public void handleAddAppointmentBtn(ActionEvent actionEvent) throws IOException {
        Navigate.navigate(actionEvent, "addAppointment");
    }
}
