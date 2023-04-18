package cms.stephenwongc195.controller;

import cms.stephenwongc195.utils.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

import static cms.stephenwongc195.utils.Alert.alert;

public class LoginController implements Initializable {
    public static String globalLocale = "en_US";
    public AnchorPane login__body;
    public TextField login__username;
    public TextField login__password;
    public Label login__title;
    public Label login__localeLbl;
    public Label login__usernameLbl;
    public Label login__passwordLbl;
    public Button login__btn;
    public String loginTitle = "Login";
    public String usernameLabel = "username";
    public String passwordLabel = "password";
    public String submitLabel = "Submit";
    public String localeLabel = "Locale";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        globalLocale = Locale.getDefault().toString();
        if (globalLocale.contains("fr")) {
            handleFrLocale();
        }
        login__title.setText(loginTitle);
        login__usernameLbl.setText(usernameLabel);
        login__passwordLbl.setText(passwordLabel);
        login__btn.setText(submitLabel);
        login__localeLbl.setText(localeLabel + ": " + Locale.getDefault().getDisplayName());
    }

    private void handleFrLocale() {
        loginTitle = "Connectez-vous";
        usernameLabel = "nom d'utilisateur";
        passwordLabel = "mot de passe";
        submitLabel = "Envoyer";
        localeLabel = "paramètres régionaux";
    }

    public void writeLog(String text) throws IOException {
        FileWriter fileWriter = new FileWriter("src/main/java/cms/stephenwongc195/login_activity.txt", true);
        fileWriter.write(text + "\n");
        fileWriter.close();

    }

    public void handleLogin(ActionEvent actionEvent) throws IOException {
        boolean login = false;

        // DELETE THIS LATER: this is just for testing
        if (login__username.getText().equals("test") && login__password.getText().equals("test")) {
            login = true;
        } else {
            alert("Invalid username or password", "Please enter a valid username and password");
            return;
        }

        writeLog((login ? "Successful" : "Unsuccessful") + " login attempt at " + LocalDateTime.now() + " login " + login__username.getText() + " pw " + login__password.getText());
        if (login) {
            Navigate.navigate(actionEvent, "home");
        }


    }


    // appends to login_activity.txt tracking log-in attempts, dates, and time stamps and whether each attempt was successful
    // if successful, user is directed to the main screen
    // if unsuccessful, display error message
    // displays login in english or french based on computer language. Updates all text, tabels, buttons, and erros
    // translate error messages to french
}
