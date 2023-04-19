package cms.stephenwongc195.controller;

import cms.stephenwongc195.model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static cms.stephenwongc195.controller.LoginController.globalLocale;
import static cms.stephenwongc195.utils.DB.tableQuery;

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
    ObservableList<Appointment> appointments = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (globalLocale.contains("fr")) home___title.setText("Système de gestion de la clientèle"); // French Locale
        getAppointments();
        System.out.println("Username: " + LoginController.username);
        System.out.println("User ID: " + LoginController.userId);
    }

    public void getAppointments() {
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
                        rs.getDate("Start"),
                        rs.getDate("End"),
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
            appointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
            appointmentStartCol.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
            appointmentEndCol.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
            appointmentCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }
