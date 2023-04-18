package cms.stephenwongc195.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static cms.stephenwongc195.controller.LoginController.globalLocale;

public class HomeController implements Initializable {
    public Label home___title;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (globalLocale.contains("fr")) {
            handleFrLocale();
        }
    }

    public void handleFrLocale() {
        home___title.setText("Système de gestion de la clientèle");
    }


}
