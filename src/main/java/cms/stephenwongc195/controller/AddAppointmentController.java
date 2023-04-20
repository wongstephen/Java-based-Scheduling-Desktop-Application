package cms.stephenwongc195.controller;

import cms.stephenwongc195.utils.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {
    public ComboBox appointmentTypeCombo;
    String[] appointmentTypes = {"Planning", "Debrief", "Consultation", "Follow-up",  "Support", "Training", "Meeting", "Presentation", "Interview", "Feedback", "Other"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (String type : appointmentTypes) {
            appointmentTypeCombo.getItems().add(type);
        }
    }

    public void handleCancelBtn(ActionEvent actionEvent) throws IOException {
    Navigate.navigate(actionEvent, "home");
    }

}
