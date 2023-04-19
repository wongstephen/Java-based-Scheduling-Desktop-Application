package cms.stephenwongc195;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

/**
 *
 * @author Stephen Wong
 * @version 1.0
 *
 * studentid: 011031716
 * 4/18/2023
 *
 */
public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 768);
        stage.setTitle("Customer Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        Locale.setDefault(new Locale("en"));
        System.out.println(Locale.getDefault().toString());
        launch();
    }
}