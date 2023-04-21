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
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static cms.stephenwongc195.dao.ContactDao.getAllContacts;
import static cms.stephenwongc195.dao.CustomerDao.getAllCustomers;

public class AddAppointmentController implements Initializable {
    @FXML
    private TextField userIdTF;
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
    }

    private void populateHourCombo() {
        for (int i = 8; i < 16; i++) {
            startHourCombo.getItems().add(TimeUtil.convertEstToLocal(i).getHour());
            endHourCombo.getItems().add(TimeUtil.convertEstToLocal(i).getHour());
        }
    }

    private void populateMinuteSecondCombo() {
        for (int i = 0; i < 60; i++) {
            startMinCombo.getItems().add(i);
            endMinuteCombo.getItems().add(i);
            startSecondCombo.getItems().add(i);
            endSecondCombo.getItems().add(i);
        }
    }


    public void handleCancelBtn(ActionEvent actionEvent) throws IOException {
    Navigate.changeScene(actionEvent, "home");
    }

    private void populateCustomerCombo() {
        customerIdCombo.setItems(getAllCustomers().sorted());
    }

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



//        divisionCombo.disableProperty().bind(countryCombo.valueProperty().isNull()); //disables division combo box until country is selected
//        endMinuteCombo.disableProperty().bind(endHourCombo.getValue() == startHourCombo.getItems().get(shclen));
//        endSecondCombo.disableProperty().bind(endMinuteCombo.valueProperty().isNull());
//        System.out.println(endHourCombo.getValue());
//        System.out.println(startHourCombo.getItems().get(shclen));
    }
}
