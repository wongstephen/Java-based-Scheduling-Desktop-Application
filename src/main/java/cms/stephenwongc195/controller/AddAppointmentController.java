package cms.stephenwongc195.controller;

import cms.stephenwongc195.model.Contact;
import cms.stephenwongc195.model.Customer;
import cms.stephenwongc195.utils.Context;
import cms.stephenwongc195.utils.Navigate;
import cms.stephenwongc195.utils.TimeUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static cms.stephenwongc195.dao.ContactDao.getAllContacts;
import static cms.stephenwongc195.dao.CustomerDao.getAllCustomers;

public class AddAppointmentController implements Initializable {
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
    public ComboBox appointmentTypeCombo;
    public ComboBox startHourCombo;
    public ComboBox startMinCombo;
    public ComboBox startSecondCombo;
    public ComboBox endHourCombo;
    public ComboBox endMinuteCombo;
    public ComboBox endSecondCombo;
    public ComboBox<Customer> customerIdCombo;
    public ComboBox<Contact> contactCombo;
    String[] appointmentTypes = {"Planning", "Debrief", "Consultation", "Follow-up",  "Support", "Training", "Meeting", "Presentation", "Interview", "Feedback", "Other"};

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

    public void handleSaveBtn(ActionEvent actionEvent) {
        int userId = Integer.parseInt(userIdTF.getText());
        String type = appointmentTypeCombo.getValue().toString();
        String description = descriptionTF.getText();
        String title = titleTF.getText();
        String location = locationTF.getText();
        String contact = contactCombo.getValue().getContactName();
        String start = startDateDp.getValue().toString() + " " + startHourCombo.getValue() + ":" + startMinCombo.getValue() + ":" + startSecondCombo.getValue();
        String end = endDateDp.getValue().toString() + " " + endHourCombo.getValue() + ":" + endMinuteCombo.getValue() + ":" + endSecondCombo.getValue();
        boolean hasException;
        String exception;
        if (customerIdCombo.getValue() == null) {
            hasException = true;
            exception = "User ID cannot be empty.";
            System.out.println(exception);
        }

    }
}
