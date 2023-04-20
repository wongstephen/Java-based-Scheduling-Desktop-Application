package cms.stephenwongc195.controller;

import cms.stephenwongc195.model.Country;
import cms.stephenwongc195.model.Division;
import cms.stephenwongc195.utils.AlertUtils;
import cms.stephenwongc195.utils.DBUtils;
import cms.stephenwongc195.utils.Navigate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class AddCustomerController implements Initializable {
    ObservableList<Country> countryList = FXCollections.observableArrayList();
    ObservableList<Division> divisionList = FXCollections.observableArrayList();
    @FXML
    private TextField customerNameTF;
    @FXML
    private TextField addressTF;
    @FXML
    private TextField postalTF;
    @FXML
    private TextField phoneTF;
    @FXML
    private ComboBox<Country> countryCombo;
    @FXML
    private ComboBox<Division> divisionCombo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        divisionCombo.disableProperty().bind(countryCombo.valueProperty().isNull()); //disables division combo box until country is selected
        getCountryDivisionList();
    }

    @FXML
    private void getCountryDivisionList() {
        try {
            ResultSet crs = DBUtils.countryQuery();
            while (crs.next()) {
                Country country = new Country(crs.getInt("Country_ID"), crs.getString("Country"));
                countryList.add(country);
            }
            ResultSet drs = DBUtils.divisionQuery();
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
    private void handleDivisionCombo(ActionEvent actionEvent) throws IOException {


    }

    @FXML
    private void onSave(ActionEvent actionEvent) throws IOException {
        List<String> exception = new ArrayList<String>();
        Boolean hasException = false;
        if (customerNameTF.getText().isBlank()) {
            exception.add("Customer name is required");
            hasException = true;
        } else {
            hasException = false;
        }
        if (addressTF.getText().isBlank()) {
            exception.add("Address is required");
            hasException = true;
        } else {
            hasException = false;
        }
        if (postalTF.getText().isBlank()) {
            exception.add("Address is required");
            hasException = true;
        } else {
            hasException = false;
        }
        if (phoneTF.getText().isBlank()) {
            exception.add("Phone Number is required");
            hasException = true;
        } else {
            hasException = false;
        }
        if (countryCombo.getValue() == null) {
            exception.add("Country is required");
            hasException = true;
        } else {
            hasException = false;
        }
        if (divisionCombo.getValue() == null) {
            exception.add("Division is required");
            hasException = true;
        } else {
            hasException = false;
        }

        if (hasException) {
            String exceptionString = String.join("\n", exception);
            AlertUtils.alertError("Please fill out all required fields before submitting the form.", exceptionString);
        } else {
            int recordsAdded = DBUtils.insertCustomer(customerNameTF.getText(), addressTF.getText(), postalTF.getText(), phoneTF.getText(), divisionCombo.getValue().getDivisionId());
            System.out.println("Records added: " + recordsAdded);
            AlertUtils.alertInformation("Customer added successfully", "Customer " + customerNameTF.getText() + " has been added successfully.");
            Navigate.changeScene(actionEvent, "home");
        }
    }

    /**
     * Cancel button handler and returns to home screen
     *
     * @param actionEvent
     */
    @FXML
    private void onCancel(ActionEvent actionEvent) throws IOException {
        Navigate.changeScene(actionEvent, "home");
    }
}
