package cms.stephenwongc195.controller;

import cms.stephenwongc195.model.Country;
import cms.stephenwongc195.utils.DB;
import cms.stephenwongc195.utils.Navigate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import static cms.stephenwongc195.utils.DB.countryQuery;

public class AddCustomerController implements Initializable {
    public ComboBox countryCombo;
    public ComboBox divisionCombo;

    ObservableList<Country> countryList = FXCollections.observableArrayList();
    Set<String> countrySet = new HashSet<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        divisionCombo.disableProperty().bind(countryCombo.valueProperty().isNull());

          getCountryList();



    }

    public void getCountryList() {
        System.out.println("Getting country list...");
        try {
            ResultSet rs = DB.countryQuery();
            while (rs.next()) {

                countryList.add(new Country(rs.getInt("Country_ID"), rs.getString("Country")));
            }
            countryList.forEach((country)->{
                countryCombo.getItems().add(country.getCountryName());
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void handleSetDivision(ActionEvent actionEvent) {
        String country = countryCombo.getValue().toString();
        Set<String> divisionSet = new HashSet<>();
        divisionCombo.getItems().clear();
        try {
            ResultSet rs = DB.divisionQuery(country);
            while (rs.next()) {
                divisionSet.add(rs.getString("Division"));
            }
            divisionSet.stream().sorted().forEach((division)->{
                divisionCombo.getItems().add(division);
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void onSave(ActionEvent actionEvent) {
    }

    /**
     * Cancel button handler
     * @param actionEvent
     */
    public void onCancel(ActionEvent actionEvent) throws IOException {
        Navigate.navigate(actionEvent, "home");
    }
}
