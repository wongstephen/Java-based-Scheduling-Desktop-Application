package cms.stephenwongc195.controller;

import cms.stephenwongc195.model.Appointment;
import cms.stephenwongc195.model.Customer;
import cms.stephenwongc195.utils.AlertUtils;
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
import java.util.ResourceBundle;


import static cms.stephenwongc195.controller.LoginController.globalLocale;
import static cms.stephenwongc195.utils.DBUtils.tableQuery;

public class HomeController implements Initializable {
    @FXML
    private Label home___title;
    @FXML
    private TableView appointmentTable;
    @FXML
    private TableColumn appointmentIdCol;
    @FXML
    private TableColumn appointmentTitleCol;
    @FXML
    private TableColumn appointmentDescriptionCol;
    @FXML
    private TableColumn appointmentLocationCol;
    @FXML
    private TableColumn appointmentTypeCol;
    @FXML
    private TableColumn appointmentStartCol;
    @FXML
    private TableColumn appointmentEndCol;
    @FXML
    private TableColumn appointmentCustomerIdCol;
    @FXML
    private TableColumn appointmentContactCol;
    @FXML
    private TableColumn appointmentUserIdCol;
    @FXML
    private Label welcomeUserLabel;
    ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    ObservableList<Appointment> filteredAppointments = FXCollections.observableArrayList();

    @FXML
    private TableView customerTable;
    @FXML
    private TableColumn customerIdCol;
    @FXML
    private TableColumn customerNameCol;
    @FXML
    private TableColumn customerAddressCol;
    @FXML
    private TableColumn customerPostalCodeCol;
    @FXML
    private TableColumn customerPhoneCol;
    @FXML
    private TableColumn customerDivisionIdCol;
    ObservableList<Customer> customers = FXCollections.observableArrayList();

    @FXML
    private RadioButton apptMonthRadio;
    @FXML
    private RadioButton apptWeekRadio;
    @FXML
    private RadioButton apptAllRadio;

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
    private void getAppointments() {
        appointments.clear();
        try {
            ResultSet rs = tableQuery("appointments");
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
    private void getCustomers() {
        customers.clear();
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
    @FXML
    private void handleMonthApptView(ActionEvent actionEvent) {
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
    @FXML
    private void handleWeekApptView(ActionEvent actionEvent) {
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int week = (now.getDayOfYear() / 7 + 1) ;

        System.out.println("Week: " + week);
        filteredAppointments.clear();
        appointments.forEach(appointment -> {
            System.out.println("appointment date of year " + appointment.getAppointmentStart().getDayOfYear());
            System.out.println("appointment week " + (appointment.getAppointmentStart().getDayOfYear() / 7 + 1));
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
    @FXML
    private void handleAllApptView(ActionEvent actionEvent) {
        appointmentTable.setItems(appointments);
    }

    @FXML
    private void handleAddCustomerBtn(ActionEvent actionEvent) throws IOException {
        Navigate.changeScene(actionEvent, "addCustomer");
    }

    @FXML
    private void handleUpdateCustomerBtn(ActionEvent actionEvent) {
        ModCustomerController.setSelectedCustomer((Customer) customerTable.getSelectionModel().getSelectedItem());
        if(ModCustomerController.selectedCustomer != null) {
            Navigate.changeScene(actionEvent, "modCustomer");
        } else {
            AlertUtils.alertError("No customer selected", "Please select a customer to modify");
        }
    }

    @FXML
    private void handleDeleteCustomerBtn(ActionEvent actionEvent) {
    }

    @FXML
    private void handleAddAppointmentBtn(ActionEvent actionEvent) throws IOException {
        Navigate.changeScene(actionEvent, "addAppointment");
    }
}
