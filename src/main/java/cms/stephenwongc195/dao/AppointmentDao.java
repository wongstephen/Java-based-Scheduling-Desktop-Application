package cms.stephenwongc195.dao;

import cms.stephenwongc195.model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AppointmentDao {
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment> currentWeekAppointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment> currentMonthAppointments = FXCollections.observableArrayList();

    public static ObservableList<Appointment> getCurrentWeekAppointments(){
        LocalDateTime now = LocalDateTime.now();
        int currentYear = now.getYear();
        double currentWeek = Math.ceil((double)now.getDayOfYear() / 7);
        currentWeekAppointments.clear();
        getAllAppointments().forEach(appointment -> {
            double appointmentWeek = (Math.ceil( (double) appointment.getAppointmentStart().getDayOfYear() / 7));
            double appointmentYear = appointment.getAppointmentStart().getYear();
            if (appointmentWeek == currentWeek && appointmentYear == currentYear) {
                currentWeekAppointments.add(appointment);
            }
        });
        return currentWeekAppointments;
    }

    public static ObservableList<Appointment> getCurrentMonthAppointments(){
        LocalDateTime now = LocalDateTime.now();
        int currentYear = now.getYear();
        int currentMonth = now.getMonthValue();
        currentMonthAppointments.clear();
        getAllAppointments().forEach(appointment -> {
            if (appointment.getAppointmentStart().getMonthValue() == currentMonth && appointment.getAppointmentStart().getYear() == currentYear) {
                currentMonthAppointments.add(appointment);
                  }
        });
        return currentMonthAppointments;
    }

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
