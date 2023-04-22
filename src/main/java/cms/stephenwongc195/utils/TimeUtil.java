package cms.stephenwongc195.utils;

import cms.stephenwongc195.model.Appointment;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

import static cms.stephenwongc195.dao.AppointmentDao.getAllAppointments;

/**
 * This class is used to for time related functions.
 */
public class TimeUtil {
    /**
     * Converts an hour in EST to Local Time
     *
     * @param estHour int EST Hour
     */
    public static ZonedDateTime convertEstToLocal(int estHour) {
        // instantiate est time
        ZoneId estZone = ZoneId.of("America/New_York");
        ZonedDateTime estTime = ZonedDateTime.now(estZone);
        // set est time to param time
        estTime = estTime.withHour(estHour);
        estTime = estTime.withMinute(0);
        estTime = estTime.withSecond(0);
        // convert est time to system default time
        ZonedDateTime localTime = estTime.withZoneSameInstant(ZoneId.systemDefault());
        return localTime;
    }

    /**
     * Checks if a given time overlaps with any appointments.
     * Lambda expression used to iterate through all appointments and check if the given time overlaps.
     *
     * @param checkDateTime
     * @return boolean
     */
    public static boolean hasAppointmentOverlap(LocalDateTime checkDateTime) {
        AtomicBoolean overlap = new AtomicBoolean(false);
        getAllAppointments().forEach(appointment -> {
            LocalDateTime appointmentStart = appointment.getAppointmentStart();
            LocalDateTime appointmentEnd = appointment.getAppointmentEnd();
            if (checkDateTime.isAfter(appointmentStart) && checkDateTime.isBefore(appointmentEnd)) {
                overlap.set(true);
            } else if (checkDateTime.isEqual(appointmentStart) || checkDateTime.isEqual(appointmentEnd)) {
                overlap.set(true);
            }
        });
        return overlap.get();
    }

    /**
     * Checks if a given time overlaps with any appointments.
     * Lambda filter expression to remove selected appointment then foreach lambda used to iterate through all appointments.
     *
     * @param checkDateTime
     * @return boolean
     */
    public static boolean modifyAppointmentOverlap(LocalDateTime checkDateTime, Appointment selectedAppointment) {
        AtomicBoolean overlap = new AtomicBoolean(false);
        ObservableList<Appointment> filtered = getAllAppointments().filtered(appointment -> appointment != selectedAppointment);
        filtered.forEach(appointment -> {
            LocalDateTime appointmentStart = appointment.getAppointmentStart();
            LocalDateTime appointmentEnd = appointment.getAppointmentEnd();
            if (checkDateTime.isAfter(appointmentStart) && checkDateTime.isBefore(appointmentEnd)) {
                overlap.set(true);
            } else if (checkDateTime.isEqual(appointmentStart) || checkDateTime.isEqual(appointmentEnd)) {
                overlap.set(true);
            }
        });
        return overlap.get();
    }
}
