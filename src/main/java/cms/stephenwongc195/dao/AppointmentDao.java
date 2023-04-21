package cms.stephenwongc195.dao;

import cms.stephenwongc195.model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentDao {
    static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    public static void addAppointment(Appointment appointment) {
        allAppointments.add(appointment);
    }

    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }

    public static void deleteAppointment(Appointment appointment) {
        allAppointments.remove(appointment);
    }

    public static void updateAllAppointments() {
        try {
            ResultSet rs = Query.tableQuery("appointments");
            while (rs.next()) {
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
                addAppointment(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static {
        updateAllAppointments(); // populate appointments

    }

}
