package cms.stephenwongc195.dao;

import cms.stephenwongc195.model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * AppointmentDao class is used to add, delete, and get all appointments.
 */
public class AppointmentDao {
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment> currentWeekAppointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment> currentMonthAppointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment> upcoming15minAppointments = FXCollections.observableArrayList();

    /**
     * Gets all appointments
     *
     * @return all appointments
     */
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

    /**
     * Gets current month appointments
     *
     * @return current month appointments
     */
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

    /**
     * Adds appointment
     *
     * @param appointment - appointment to be added
     */
    public static void addAppointment(Appointment appointment) {
        allAppointments.add(appointment);
    }

    /**
     * Gets all appointments
     *
     * @return all appointments
     */
    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }

    /**
     * Deletes appointment
     *
     * @param appointment - appointment to be deleted
     */
    public static void deleteAppointment(Appointment appointment) {
        allAppointments.remove(appointment);
    }

    /**
     * Updates all appointments
     */
    public static void updateAllAppointments() {
        allAppointments.clear();
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

    /**
     * Gets appointments in 15 minutes
     */
    public static ObservableList<Appointment> getUpcoming15minAppointments() {
        upcoming15minAppointments.clear();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime fifteenMinutesFromNow = now.plusMinutes(15);
        getAllAppointments().forEach(appointment -> {
            if (appointment.getAppointmentStart().isAfter(now) && appointment.getAppointmentStart().isBefore(fifteenMinutesFromNow)) {
                upcoming15minAppointments.add(appointment);
            }
        });
        return upcoming15minAppointments;
    }

    /**
     * Updates and populates appointments on load
     */
    static {
        updateAllAppointments(); // populate appointments
    }

}
