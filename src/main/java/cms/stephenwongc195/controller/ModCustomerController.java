package cms.stephenwongc195.controller;

import cms.stephenwongc195.dao.Query;
import cms.stephenwongc195.model.Country;
import cms.stephenwongc195.model.Customer;
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

import static cms.stephenwongc195.dao.LocationDao.*;

public class ModCustomerController implements Initializable {
    public static Customer selectedCustomer = null;
    public static void setSelectedCustomer(Customer customer) {
        selectedCustomer = customer;
    }

    @FXML
    private TextField customerIdTF;
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
        countryCombo.setItems(allCountries);
        setCustomerData();
    }

    private void setCustomerData() {
        customerIdTF.setText(String.valueOf(selectedCustomer.getCustomerId()));
        customerNameTF.setText(selectedCustomer.getCustomerName());
        addressTF.setText(selectedCustomer.getCustomerAddress());
        postalTF.setText(selectedCustomer.getCustomerPostalCode());
        phoneTF.setText(selectedCustomer.getCustomerPhone());

        int divisionId = selectedCustomer.getCustomerDivisionId();
        Division currentDivision = allDivisions.filtered(division -> division.getDivisionId() == divisionId).get(0);

        int countryId = currentDivision.getCountryId();
        Country currentCountry = allCountries.filtered(country -> country.getCountryId() == countryId).get(0);
        countryCombo.setValue(currentCountry);

        ObservableList<Division> result = lookupDivisionByCountry(countryCombo.getValue().getCountryId());
        divisionCombo.setItems(result.sorted());

        divisionCombo.setValue(currentDivision);
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
        List<String> exception = new ArrayList<String>();
        Boolean hasException;
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
            int recordsUpdated = Query.updateCustomer(customerNameTF.getText(), addressTF.getText(), postalTF.getText(), phoneTF.getText(), divisionCombo.getValue().getDivisionId(), Integer.parseInt(customerIdTF.getText()));
            if(recordsUpdated > 0){
                AlertUtils.alertInformation("Customer updated successfully", "Customer " + customerNameTF.getText() + " has been updated successfully.");
            } else {
                AlertUtils.alertError("Customer update failed", "Customer " + customerNameTF.getText() + " was not updated successfully. Try again later");
                return;
            }
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
