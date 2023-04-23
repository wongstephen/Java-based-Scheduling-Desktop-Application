package cms.stephenwongc195.controller;

import cms.stephenwongc195.dao.Query;
import cms.stephenwongc195.model.Country;
import cms.stephenwongc195.model.Division;
import cms.stephenwongc195.utils.AlertUtils;
import cms.stephenwongc195.utils.Navigate;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static cms.stephenwongc195.dao.LocationDao.allCountries;
import static cms.stephenwongc195.dao.LocationDao.lookupDivisionByCountry;

/**
 * AddCustomerController class is used to add a customer.
 */
public class AddCustomerController implements Initializable {
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        divisionCombo.disableProperty().bind(countryCombo.valueProperty().isNull()); //disables division combo box until country is selected
        countryCombo.setItems(allCountries);
    }

    /**
     * Sets division combo box to only show divisions in selected country
     *
     * @param actionEvent
     */
    @FXML
    private void handleSetDivision(ActionEvent actionEvent) {
        ObservableList<Division> result = lookupDivisionByCountry(countryCombo.getValue().getCountryId());
        divisionCombo.setItems(result.sorted());
    }

    /**
     * Saves customer to database
     *
     * @param actionEvent
     */
    @FXML
    private void onSave(ActionEvent actionEvent) throws IOException {
        List<String> exception = new ArrayList<>();
        if (customerNameTF.getText().isBlank()) exception.add("Customer name is required");
        if (addressTF.getText().isBlank())  exception.add("Address is required");
        if (postalTF.getText().isBlank()) exception.add("Postal is required");
        if (phoneTF.getText().isBlank()) exception.add("Phone Number is required");
        if (countryCombo.getValue() == null) exception.add("Country is required");
        if (divisionCombo.getValue() == null) exception.add("Division is required");
        if (exception.size() > 0) {
            String exceptionString = String.join("\n", exception);
            AlertUtils.alertError("Please fill out all required fields before submitting the form.", exceptionString);
        } else {
            int recordsAdded = Query.insertCustomer(customerNameTF.getText(), addressTF.getText(), postalTF.getText(), phoneTF.getText(), divisionCombo.getValue().getDivisionId());
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
