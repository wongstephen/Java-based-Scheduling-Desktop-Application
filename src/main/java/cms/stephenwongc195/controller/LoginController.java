package cms.stephenwongc195.controller;

import cms.stephenwongc195.dao.Query;
import cms.stephenwongc195.utils.Context;
import cms.stephenwongc195.utils.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

import static cms.stephenwongc195.utils.AlertUtils.alertError;

/**
 * LoginController class is used to handle login.
 */
public class LoginController implements Initializable {
    public static String globalLocale = "en_US";

    @FXML
    private TextField login__username;
    @FXML
    private TextField login__password;
    @FXML
    private Label login__title;
    @FXML
    private Label login__localeLbl;
    @FXML
    private Label login__usernameLbl;
    @FXML
    private Label login__passwordLbl;
    @FXML
    private Button login__cancelBtn;

    @FXML
    private Button login__btn;
    @FXML
    private String loginTitle = "Login";
    @FXML
    private String usernameLabel = "username";
    @FXML
    private String passwordLabel = "password";
    @FXML
    private String submitLabel = "Submit";
    @FXML
    private String localeLabel = "Locale";
    @FXML
    private String cancelText = "Cancel";
    @FXML
    private String alertFr1 = "Nom d’utilisateur ou mot de passe non valide";
    @FXML
    private String alertFr2 = "Veuillez saisir un nom d’utilisateur et un mot de passe valides";


/**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        globalLocale = Locale.getDefault().toString();
        if (globalLocale.contains("fr")) handleFrLocale();
        login__title.setText(loginTitle);
        login__usernameLbl.setText(usernameLabel);
        login__passwordLbl.setText(passwordLabel);
        login__btn.setText(submitLabel);
        login__cancelBtn.setText(cancelText);
        login__localeLbl.setText(localeLabel + ": " + ZoneId.systemDefault());
    }

    /**
     * Handles French locale
     */
    private void handleFrLocale() {
        loginTitle = "Connectez-vous";
        usernameLabel = "nom d'utilisateur";
        passwordLabel = "mot de passe";
        submitLabel = "Envoyer";
        localeLabel = "paramètres régionaux";
        cancelText = "Annuler";
    }

    /**
     * Writes to login activity to login_activity.txt
     *
     * @param text
     */
    private void writeLog(String text) throws IOException {
        FileWriter fileWriter = new FileWriter("src/main/java/cms/stephenwongc195/login_activity.txt", true);
        fileWriter.write(text + "\n");
        fileWriter.close();
    }

    /**
     * Handles login button click
     *
     * @param actionEvent
     */
    @FXML
    private void handleLogin(ActionEvent actionEvent) throws IOException, SQLException {
        ResultSet rs = Query.login(login__username.getText(), login__password.getText());
        boolean login;
        if (rs.next()) {
            login = true;
            String username = rs.getString("User_Name");
            int userId = rs.getInt("User_Id");
            Context.setUserName(username);
            Context.setUserId(userId);
            } else {
            login = false;
            if (globalLocale.contains("fr")) {
                alertError(alertFr1, alertFr2);
            } else {
                alertError("Invalid username or password", "Please enter a valid username and password");
            }
        }
        writeLog((login ? "Successful" : "Unsuccessful") + " login attempt at " + LocalDateTime.now() + " login " + login__username.getText() + " pw " + login__password.getText());
        if (login) {
            Navigate.changeScene(actionEvent, "home");
        }
    }

    /**
     * Handles cancel button click
     *
     * @param actionEvent
     */
    @FXML
    private void handleCancelBtn(ActionEvent actionEvent) {
        System.exit(0);
    }
}
