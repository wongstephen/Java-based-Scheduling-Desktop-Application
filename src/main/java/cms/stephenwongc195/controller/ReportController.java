package cms.stephenwongc195.controller;

import cms.stephenwongc195.dao.AppointmentDao;
import cms.stephenwongc195.dao.ContactDao;
import cms.stephenwongc195.model.Appointment;
import cms.stephenwongc195.model.AppointmentsByCustomer;
import cms.stephenwongc195.model.AppointmentsMonthType;
import cms.stephenwongc195.model.Contact;
import cms.stephenwongc195.utils.Navigate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static cms.stephenwongc195.dao.Query.appointmentsByCustomer;
import static cms.stephenwongc195.dao.Query.queryTypeCountByMonth;

public class ReportController  implements Initializable {
    static ObservableList<AppointmentsMonthType> appointmentsMonthTypes = FXCollections.observableArrayList();
    static ObservableList<AppointmentsByCustomer> appointmentsByCustomers = FXCollections.observableArrayList();

    public TableView chart1__tableView;
    public TableColumn chart1__monthCol;
    public TableColumn chart1__countCol;
    public TableColumn chart1__typeCol;

    public TableView chart2__tableView;
    public TableColumn chart2__custCol;
    public TableColumn chart2__apptCol;

    public ComboBox<Contact> contactCombo;
    public TableView chart3__tableView;
    public TableColumn chart3__appointmentCol;
    public TableColumn chart3__titleCol;
    public TableColumn chart3__typeCol;
    public TableColumn chart3__descCol;
    public TableColumn chart3__startCol;
    public TableColumn chart3__endCol;
    public TableColumn chart3__custIdCol;

    /**
     * Initialize report screen with data
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            populateContactCombo();
            setChart1();
            setChart2();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Cancel button handler and returns to home screen
     *
     * @param actionEvent
     */
    @FXML
    private void onCancel(ActionEvent actionEvent) throws IOException {
        Navigate.changeScene(actionEvent, "home");
    }

    /**
     * Set table view for appointments by type and month
     */
    public void setChart1() throws SQLException {
        appointmentsMonthTypes.clear();
        ResultSet rs = queryTypeCountByMonth();
        while (rs.next()) {
            AppointmentsMonthType appointment = new AppointmentsMonthType(rs.getString("month"), rs.getString("type"), rs.getInt("num_appointments") );
            appointmentsMonthTypes.add(appointment);
        }
        chart1__tableView.getItems().clear();
        chart1__tableView.setItems(appointmentsMonthTypes.sorted());
        chart1__monthCol.setCellValueFactory(new PropertyValueFactory<>("monthValue"));
        chart1__typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        chart1__countCol.setCellValueFactory(new PropertyValueFactory<>("count"));
    }

    /**
     * Set table view for appointments by customer chart2
     */
    public void setChart2() throws SQLException {
        appointmentsByCustomers.clear();
        ResultSet rs = appointmentsByCustomer();
        while(rs.next()) {
            AppointmentsByCustomer appointmentByCustomer = new AppointmentsByCustomer(rs.getString("Customer_Name"), rs.getInt("count(*)"));
            appointmentsByCustomers.add(appointmentByCustomer);
        }
        chart2__tableView.setItems(appointmentsByCustomers);
        chart2__custCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        chart2__apptCol.setCellValueFactory(new PropertyValueFactory<>("count"));
    }

    /**
     * Populate contact combo box
     */
    public void populateContactCombo() {
        contactCombo.setItems(ContactDao.getAllContacts());
    }

    /**
     * Handle contact combo box selection
     *
     * @param actionEvent
     */
    public void handleSelectedContactCombo(ActionEvent actionEvent) {
        setChart3(contactCombo.getSelectionModel().getSelectedItem().getContactId());
    }

    /**
     * Set table view for appointments by contact
     */
    public void setChart3(int selectedContact) {
        ObservableList<Appointment> filteredByContact = AppointmentDao.getAllAppointments().filtered(appointment -> appointment.getContactId() == selectedContact);
        chart3__tableView.setItems(filteredByContact);
        chart3__appointmentCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        chart3__titleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        chart3__typeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        chart3__descCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        chart3__startCol.setCellValueFactory(new PropertyValueFactory<>("appointmentStartFormatted"));
        chart3__endCol.setCellValueFactory(new PropertyValueFactory<>("appointmentEndFormatted"));
        chart3__custIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
    }
}
