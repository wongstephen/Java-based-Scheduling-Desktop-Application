package cms.stephenwongc195.controller;

import cms.stephenwongc195.utils.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static cms.stephenwongc195.dao.Query.queryTypeCountByMonth;

public class ReportController  implements Initializable {

    public ComboBox monthComboBox;
    public ComboBox typeComboBox;
    public Button searchBtn;




    /**
     * Cancel button handler and returns to home screen
     *
     * @param actionEvent
     */
    @FXML
    private void onCancel(ActionEvent actionEvent) throws IOException {
        Navigate.changeScene(actionEvent, "home");
    }

    public void setMonthComboBox() {
        for (int i = 1; i <= 12; i++) {
            LocalDateTime month = LocalDateTime.of(2021, i, 1, 0, 0);
            monthComboBox.getItems().add(month.getMonth());
        }
    }

    public void queryApptByTypeMonth() throws SQLException {
        ResultSet rs = queryTypeCountByMonth();
        while (rs.next()) {
            System.out.println(rs.getString("month"));
            System.out.println(rs.getString("type"));
            System.out.println(rs.getInt("num_appointments"));
            System.out.println(rs.next());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO: Implement month and type combo boxes
        setMonthComboBox();
        typeComboBox.disableProperty().bind(monthComboBox.valueProperty().isNull());
        searchBtn.disableProperty().bind(typeComboBox.valueProperty().isNull());
        try {
            queryApptByTypeMonth();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
