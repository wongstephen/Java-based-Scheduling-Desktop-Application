package cms.stephenwongc195.controller;

import cms.stephenwongc195.model.Appointment;
import cms.stephenwongc195.model.Customer;
import cms.stephenwongc195.utils.AlertUtils;
import cms.stephenwongc195.dao.Query;
import cms.stephenwongc195.utils.Context;
import cms.stephenwongc195.utils.Navigate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.ResourceBundle;


import static cms.stephenwongc195.controller.LoginController.globalLocale;
import static cms.stephenwongc195.dao.AppointmentDao.getAllAppointments;
import static cms.stephenwongc195.dao.CustomerDao.getAllCustomers;
import static cms.stephenwongc195.dao.CustomerDao.updateAllCustomers;
import static cms.stephenwongc195.dao.Query.tableQuery;

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
        setAppointmentTable(); // Get appointments from DB and populates appointments table
        setCustomerTable(); // Get customers from DB and populates customers table
        welcomeUserLabel.setText("Welcome, " + Context.getUserName()); // Welcome username on home screen
        homeZoneIdLabel.setText(ZonedDateTime.now().getZone().toString()); // Set Zone ID label
    }

    /**
     * On load, populates the customer table with all appointments from the DB
     */
    private void setAppointmentTable() {
        appointmentTable.setItems(getAllAppointments());
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
    }

    /**
     * On load, populates the customer table with all customers from the DB
     */
    private void setCustomerTable() {
            updateAllCustomers(); // Update all customers
            customerTable.setItems(getAllCustomers());
            customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
            customerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
            customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
            customerDivisionIdCol.setCellValueFactory(new PropertyValueFactory<>("customerDivisionId"));
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
        getAllAppointments().forEach(appointment -> {
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
        int currentWeek = (int) Math.ceil(now.getDayOfYear() / 7) + 1;
        filteredAppointments.clear();
       getAllAppointments().forEach(appointment -> {
            if ((Math.ceil(appointment.getAppointmentStart().getDayOfYear()/7)+1) == currentWeek && appointment.getAppointmentStart().getYear() == year) {
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
        appointmentTable.setItems(getAllAppointments());
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
        Customer selectedCustomer = (Customer) customerTable.getSelectionModel().getSelectedItem();
        if(selectedCustomer != null) {
            Optional<ButtonType> result = AlertUtils.alertConfirmation("Delete Customer", "Are you sure you want to delete this customer?");
            if (result.get() == ButtonType.OK) {
                Query.deleteCustomer(selectedCustomer.getCustomerId());
                setCustomerTable();
            }

        } else {
            AlertUtils.alertError("No customer selected", "Please select a customer to delete");
        }
    }

    @FXML
    private void handleAddAppointmentBtn(ActionEvent actionEvent) throws IOException {
        Navigate.changeScene(actionEvent, "addAppointment");
    }
}
