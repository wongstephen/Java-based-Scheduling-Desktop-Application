package cms.stephenwongc195.model;


import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * This class is as a model for appointments
 */
public class Appointment {
    private int appointmentId;
    private String appointmentTitle;
    private String appointmentDescription;
    private String appointmentLocation;
    private String appointmentType;
    private LocalDateTime appointmentStart;
    private LocalDateTime appointmentEnd;
    private int customerId;
    private int userId;
    private int contactId;

    public Appointment(int appointmentId, String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, LocalDateTime appointmentStart, LocalDateTime appointmentEnd, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDescription = appointmentDescription;
        this.appointmentLocation = appointmentLocation;
        this.appointmentType = appointmentType;
        this.appointmentStart = appointmentStart;
        this.appointmentEnd = appointmentEnd;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**
     * Returns appointment id as an int
     * @return appointment id
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * Sets appointment id as an int
     * @return void
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Returns appointment title as a String
     * @return appointment title
     */
    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    /**
     * Sets appointment title as a String
     * @return void
     */
    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    /**
     * Returns appointment description as a String
     * @return appointment description
     */
    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    /**
     * Sets appointment description as a String
     * @return void
     */
    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    /**
     * Returns appointment location as a String
     * @return appointment location
     */
    public String getAppointmentLocation() {
        return appointmentLocation;
    }

    /**
     * Sets appointment location as a String
     * @return void
     */
    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    /**
     * Returns appointment type as a String
     * @return appointment type
     */
    public String getAppointmentType() {
        return appointmentType;
    }

    /**
     * Sets appointment type as a String
     * @return void
     */
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    /**
     * Returns appointment start time
     * @return appointment start
     */
    public LocalDateTime getAppointmentStart() { return appointmentStart; }

    /**
     * Returns appointment start time formatted
     * @return appointment start formatted
     */
    public String getAppointmentStartFormatted() {
        ZonedDateTime zdt = appointmentStart.atZone(ZoneId.systemDefault());
        return zdt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm zzz"));
    }

    /**
     * Sets appointment start time
     * @return void
     */
    public void setAppointmentStart (LocalDateTime appointmentStart) {
        this.appointmentStart = appointmentStart;
    }

    /**
     * Returns appointment end time
     * @return appointment end
     */
    public LocalDateTime getAppointmentEnd() {
        return appointmentEnd;
    }

    /**
     * Sets appointment end time formatted
     * @return string of end date time formatted
     */
    public String getAppointmentEndFormatted() {
        ZonedDateTime zdt = appointmentEnd.atZone(ZoneId.systemDefault());
        return zdt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm zzz"));
    }


    /**
     * Sets appointment end time
     * @return void
     */
    public void Timestamp (LocalDateTime appointmentEnd) {
        this.appointmentEnd = appointmentEnd;
    }

    /**
     * Returns customer id as an int
     * @return customer id
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets customer id as an int
     * @return void
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Returns user id as an int
     * @return user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets user id as an int
     * @return void
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Returns contact id as an int
     * @return contact id
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Sets contact id as an int
     * @return void
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Override appointment title and id as a String
     * @return appointment title and id
     */
    @Override
    public String toString() {
        return appointmentTitle + " " + appointmentId;
    }
}
