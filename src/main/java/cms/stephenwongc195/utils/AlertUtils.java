package cms.stephenwongc195.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * This class is used to display alerts to the user.
 */
public class AlertUtils {
    /**
     * Displays an error alert to the user depending on parameters
     * @param title
     * @param message
     */
    public static void alertError(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    /**
     * Displays an info alert to the user depending on parameters
     * @param title
     * @param message
     */
    public static void alertInformation(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    /**
     * Displays an confirmation alert to the user depending on parameters
     * @param title
     * @param message
     */
    public static Optional<ButtonType> alertConfirmation(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }
}
