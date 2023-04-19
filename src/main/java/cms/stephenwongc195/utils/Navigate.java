package cms.stephenwongc195.utils;

import cms.stephenwongc195.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Navigate {
    static Stage stage;
    static Parent scene;

    public static void navigate (ActionEvent actionEvent, String location) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(App.class.getResource("view/" + location + ".fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
