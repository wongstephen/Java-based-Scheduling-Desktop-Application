package cms.stephenwongc195.controller;

import cms.stephenwongc195.dao.ContactDao;
import cms.stephenwongc195.dao.CustomerDao;
import cms.stephenwongc195.model.Appointment;
import cms.stephenwongc195.model.Contact;
import cms.stephenwongc195.model.Customer;
import cms.stephenwongc195.utils.AlertUtils;
import cms.stephenwongc195.utils.Context;
import cms.stephenwongc195.utils.Navigate;
import cms.stephenwongc195.utils.TimeUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static cms.stephenwongc195.dao.ContactDao.getAllContacts;
import static cms.stephenwongc195.dao.CustomerDao.getAllCustomers;
import static cms.stephenwongc195.dao.Query.insertAppointment;
import static cms.stephenwongc195.dao.Query.updateAppointment;
import static cms.stephenwongc195.utils.TimeUtil.hasAppointmentOverlap;
import static cms.stephenwongc195.utils.TimeUtil.modifyAppointmentOverlap;

public class ModAppointmentController implements Initializable {
    @FXML
    private DatePicker startDateDp;
    @FXML
    private DatePicker endDateDp;
    @FXML
    private TextField userIdTF;
    @FXML
    private TextField descriptionTF;
    @FXML
    private TextField titleTF;
    @FXML
    private TextField locationTF;
    @FXML
    private TextField appointmentIdTF;
    public ComboBox appointmentTypeCombo;
    public ComboBox startHourCombo;
    public ComboBox startMinCombo;
    public ComboBox startSecondCombo;
    public ComboBox endHourCombo;
    public ComboBox endMinuteCombo;
    public ComboBox endSecondCombo;
    public ComboBox<Customer> customerIdCombo;
    public ComboBox<Contact> contactCombo;

    public static Appointment selectedAppointment;
    String[] appointmentTypes = {"Planning", "Debrief", "Consultation", "Follow-up",  "Support", "Training", "Meeting", "Presentation", "Interview", "Feedback", "Other"};

    /**
     * Context for selected appointment to edit.
     *
     */
    public static void setSelectedAppointment(Appointment selectedItem) {
        selectedAppointment = selectedItem;
    }

    /**
     * Populates the text fields, combo boxes, and date pickers with the selected appointment data.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (String type : appointmentTypes) {
            appointmentTypeCombo.getItems().add(type);
        }
        userIdTF.setText(String.valueOf(Context.getUserId()));
        populateHourCombo();
        populateMinuteSecondCombo();
        populateCustomerCombo();
        populateContactCombo();
        userIdTF.setText(String.valueOf(Context.getUserId()));
        setAppointmentData();
    }

    /**
     * Set the selected appointment data to text fields
     *
     */
    public void setAppointmentData() {
        titleTF.setText(selectedAppointment.getAppointmentTitle());
        descriptionTF.setText(selectedAppointment.getAppointmentDescription());
        locationTF.setText(selectedAppointment.getAppointmentLocation());
        appointmentTypeCombo.setValue(selectedAppointment.getAppointmentType());
        startDateDp.setValue(selectedAppointment.getAppointmentStart().toLocalDate());
        endDateDp.setValue(selectedAppointment.getAppointmentEnd().toLocalDate());
        startHourCombo.setValue(selectedAppointment.getAppointmentStart().getHour());
        startMinCombo.setValue(selectedAppointment.getAppointmentStart().getMinute());
        startSecondCombo.setValue(selectedAppointment.getAppointmentStart().getSecond());
        endHourCombo.setValue(selectedAppointment.getAppointmentEnd().getHour());
        endMinuteCombo.setValue(selectedAppointment.getAppointmentEnd().getMinute());
        endSecondCombo.setValue(selectedAppointment.getAppointmentEnd().getSecond());
        customerIdCombo.setValue(CustomerDao.getCustomerById(selectedAppointment.getCustomerId()));
        contactCombo.setValue(ContactDao.getContactById(selectedAppointment.getContactId()));
        appointmentIdTF.setText(String.valueOf(selectedAppointment.getAppointmentId()));
    }


    /**
     * Populates the hour combo boxes with values 8-16 EST and will be translated to local time.
     * */
    private void populateHourCombo() {
        for (int i = 8; i < 16; i++) {
            startHourCombo.getItems().add(TimeUtil.convertEstToLocal(i).getHour());
            endHourCombo.getItems().add(TimeUtil.convertEstToLocal(i).getHour());
        }
    }

    /**
     * Populates the minute and second combo boxes with values 0-59.
     * */
    private void populateMinuteSecondCombo() {
        for (int i = 0; i < 60; i++) {
            startMinCombo.getItems().add(i);
            endMinuteCombo.getItems().add(i);
            startSecondCombo.getItems().add(i);
            endSecondCombo.getItems().add(i);
        }
    }

    /**
     * Handles the cancel button. Returns to the home screen.
     * @param actionEvent Cancel button click on home screen
     */
    public void handleCancelBtn(ActionEvent actionEvent) throws IOException {
    Navigate.changeScene(actionEvent, "home");
    }

    /**
     * Populates the customer combo box with all customers in the database.
     */
    private void populateCustomerCombo() {
        customerIdCombo.setItems(getAllCustomers().sorted());
    }

    /**
     * Populates the contact combo box with all contacts in the database.
     */
    private void populateContactCombo() {
        contactCombo.setItems(getAllContacts().sorted());
    }

    public void handleEndHourCombo(ActionEvent actionEvent) {
        int shclen = endHourCombo.getItems().size()-1;
        int endComboValue = (int)endHourCombo.getValue();
        if (endComboValue == (int)endHourCombo.getItems().get(shclen)) {
            endMinuteCombo.setDisable(true);
            endSecondCombo.setDisable(true);
            endMinuteCombo.setValue(0);
            endSecondCombo.setValue(0);
        } else {
            endMinuteCombo.setDisable(false);
            endSecondCombo.setDisable(false);
        }
    }


    /**
     * Validates the comboboxes values and adds to exception array if null.
     * @param comboBox on the add appointment screen
     * @param type label of combobox
     * @param exceptionArray array of exceptions
     */
    public void validateCombo(ComboBox comboBox, String type, ArrayList<String> exceptionArray) {
        if (comboBox.getValue() == null) {
            exceptionArray.add(type + " cannot be empty.");
        } else {
            exceptionArray.remove(type + " cannot be empty.");
        }
    }

    /**
     * Validates the textField values and adds to exception array if null.
     * @param textField on the add appointment screen
     * @param type label of combobox
     * @param exceptionArray array of exceptions
     */
    public void validateTF(TextField textField, String type, ArrayList<String> exceptionArray) {
        if (textField.getText().isEmpty()) {
            exceptionArray.add(type + " cannot be empty.");
        } else {
            exceptionArray.remove(type + " cannot be empty.");
        }
    }

    /**
     * Validates the date values and adds to exception array if null.
     * @param datePicker on the add appointment screen
     * @param type label of combobox
     * @param exceptionArray array of exceptions
     */
    public void validateDate(DatePicker datePicker, String type, ArrayList<String> exceptionArray) {
        if (datePicker.getValue() == null) {
            exceptionArray.add(type + " cannot be empty.");
        } else {
            exceptionArray.remove(type + " cannot be empty.");
        }
    }

    /**
     * Handles the save button. Validates the data and saves the appointment to the database.
     * @param actionEvent Save button click on home screen
     */
    public void handleSaveBtn(ActionEvent actionEvent) {
        ArrayList<String> exceptionArray = new ArrayList<>();

        validateCombo(customerIdCombo, "Customer ID", exceptionArray);
        validateCombo(contactCombo, "Contact", exceptionArray);
        validateTF(titleTF, "Title", exceptionArray);
        validateTF(descriptionTF, "Description", exceptionArray);
        validateTF(locationTF, "Location", exceptionArray);
        validateCombo(appointmentTypeCombo, "Type", exceptionArray);
        validateTF(locationTF, "Location", exceptionArray);

        // Time validation
        validateDate(startDateDp, "Start date", exceptionArray);
        validateCombo(startHourCombo, "Start hour", exceptionArray);
        validateCombo(startMinCombo, "Start minute", exceptionArray);
        validateCombo(startSecondCombo, "Start second", exceptionArray);
        validateDate(endDateDp, "End date", exceptionArray);
        validateCombo(endHourCombo, "End hour", exceptionArray);
        validateCombo(endMinuteCombo, "End minute", exceptionArray);
        validateCombo(endSecondCombo, "End second", exceptionArray);

        // If all fields are filled out, check if the start date is before the end date and if the appointment conflicts with another appointment.
        if (startDateDp.getValue() != null && startHourCombo.getValue() != null && startMinCombo.getValue() != null && startSecondCombo.getValue() != null && endDateDp.getValue() != null && endHourCombo.getValue() != null && endMinuteCombo.getValue() != null && endSecondCombo.getValue() != null) {

            LocalDateTime startDateTime = LocalDateTime.of(startDateDp.getValue().getYear(), startDateDp.getValue().getMonthValue(), startDateDp.getValue().getDayOfMonth(), Integer.parseInt(startHourCombo.getValue().toString()), Integer.parseInt(startMinCombo.getValue().toString()), Integer.parseInt(startSecondCombo.getValue().toString()));
            LocalDateTime endDateTime = LocalDateTime.of(endDateDp.getValue().getYear(), endDateDp.getValue().getMonthValue(), endDateDp.getValue().getDayOfMonth(), Integer.parseInt(endHourCombo.getValue().toString()), Integer.parseInt(endMinuteCombo.getValue().toString()), Integer.parseInt(endSecondCombo.getValue().toString()));
            if(startDateTime.isAfter(endDateTime)) {
                exceptionArray.add("Start date cannot be after end date.");
            } else {
                exceptionArray.remove("Start date cannot be after end date.");
            }

            if(modifyAppointmentOverlap(startDateTime, selectedAppointment) || modifyAppointmentOverlap(endDateTime, selectedAppointment)) {
                exceptionArray.add("Appointment overlaps with another appointment.");
            } else {
                exceptionArray.remove("Appointment overlaps with another appointment.");
            }
        }

        if (exceptionArray.size() > 0) {
            String exceptionString = String.join("\n", exceptionArray);
            AlertUtils.alertError("Please fill out all required fields before submitting the form.", exceptionString);
        } else {
            ZonedDateTime startDate = ZonedDateTime.of(startDateDp.getValue().getYear(), startDateDp.getValue().getMonthValue(), startDateDp.getValue().getDayOfMonth(), Integer.parseInt(startHourCombo.getValue().toString()), Integer.parseInt(startMinCombo.getValue().toString()),Integer.parseInt(startSecondCombo.getValue().toString()), 0, ZoneId.systemDefault());
            ZonedDateTime endDate = ZonedDateTime.of(endDateDp.getValue().getYear(), endDateDp.getValue().getMonthValue(), endDateDp.getValue().getDayOfMonth(), Integer.parseInt(endHourCombo.getValue().toString()), Integer.parseInt(endMinuteCombo.getValue().toString()),Integer.parseInt(endSecondCombo.getValue().toString()), 0, ZoneId.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            ZonedDateTime utcStartDateTime = startDate.withZoneSameInstant(ZoneId.of("UTC"));
            String startDateFormatted = formatter.format(utcStartDateTime);

            ZonedDateTime utcEndDateTime = endDate.withZoneSameInstant(ZoneId.of("UTC"));
            String endDateFormatted = formatter.format(utcEndDateTime);

            updateAppointment(titleTF.getText(), descriptionTF.getText(), locationTF.getText(), appointmentTypeCombo.getValue().toString(), startDateFormatted, endDateFormatted, customerIdCombo.getValue().getCustomerId(), Context.getUserId(), contactCombo.getValue().getContactId(), selectedAppointment.getAppointmentId());
            AlertUtils.alertInformation("Appointment updated successfully", "Appointment has been updated successfully.");
            Navigate.changeScene(actionEvent, "home");
        }
    }
}
