package cms.stephenwongc195.utils;

import cms.stephenwongc195.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class is used to change scenes.
 */
public class Navigate {
    public static void changeScene(ActionEvent actionEvent, String location) {
        Parent scene = null;
        try {
            FXMLLoader loader =  new FXMLLoader(App.class.getResource("view/" + location + ".fxml"));
            scene = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(scene));
    }
}
