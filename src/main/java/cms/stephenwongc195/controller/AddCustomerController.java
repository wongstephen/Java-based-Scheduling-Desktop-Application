package cms.stephenwongc195.controller;

import cms.stephenwongc195.model.Country;
import cms.stephenwongc195.model.Division;
import cms.stephenwongc195.utils.DB;
import cms.stephenwongc195.utils.Navigate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class AddCustomerController implements Initializable {
    @FXML
    private ComboBox<Country> countryCombo;
    @FXML
    private ComboBox<Division> divisionCombo;

    ObservableList<Country> countryList = FXCollections.observableArrayList();
    ObservableList<Division> divisionList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        divisionCombo.disableProperty().bind(countryCombo.valueProperty().isNull()); //disables division combo box until country is selected
        getCountryDivisionList();
    }

    @FXML
    private void getCountryDivisionList() {
        try {
            ResultSet crs = DB.countryQuery();
            while (crs.next()) {
                Country country = new Country(crs.getInt("Country_ID"), crs.getString("Country"));
                countryList.add(country);
            }
            ResultSet drs = DB.divisionQuery();
            while (drs.next()) {
                Division division = new Division(drs.getInt("Division_ID"), drs.getString("Division"), drs.getInt("Country_ID"));
                divisionList.add(division);
            }
            countryCombo.setItems(countryList.sorted());
          } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleSetDivision(ActionEvent actionEvent) {
        ObservableList<Division> divisionListFiltered = FXCollections.observableArrayList();
        divisionListFiltered.setAll(divisionList);
        int countryId = countryCombo.getValue().getCountryId();
        divisionListFiltered.removeIf(division -> division.getCountryId() != countryId);
        divisionCombo.setItems(divisionListFiltered.sorted());
    }
    @FXML
    private void handleDivisionCombo (ActionEvent actionEvent) throws IOException {


    }
    @FXML
    private void onSave(ActionEvent actionEvent) throws IOException {
    }

    /**
     * Cancel button handler and returns to home screen
     * @param actionEvent
     */
    @FXML
    private void onCancel(ActionEvent actionEvent) throws IOException {
        Navigate.navigate(actionEvent, "home");
    }
}
