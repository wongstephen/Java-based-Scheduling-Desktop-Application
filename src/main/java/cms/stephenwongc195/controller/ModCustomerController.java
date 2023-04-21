package cms.stephenwongc195.controller;

import cms.stephenwongc195.model.Country;
import cms.stephenwongc195.model.Customer;
import cms.stephenwongc195.model.Division;
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
import java.util.ResourceBundle;

public class ModCustomerController implements Initializable {
    public static Customer selectedCustomer = null;
    public static void setSelectedCustomer(Customer customer) {
        selectedCustomer = customer;
    }

    ObservableList<Country> countryList = FXCollections.observableArrayList();
    ObservableList<Division> divisionList = FXCollections.observableArrayList();

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
        getCountryDivisionList();
        setCustomerData();

    }

    private void setCustomerData() {
        customerIdTF.setText(String.valueOf(selectedCustomer.getCustomerId()));
        customerNameTF.setText(selectedCustomer.getCustomerName());
        addressTF.setText(selectedCustomer.getCustomerAddress());
        postalTF.setText(selectedCustomer.getCustomerPostalCode());
        phoneTF.setText(selectedCustomer.getCustomerPhone());
        System.out.println(divisionList.indexOf(selectedCustomer.getCustomerDivisionId()));
        System.out.println(divisionList);
    }

    /**
     * Gets list of countries and divisions from database
     */
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

    /**
     * Sets division combo box to only show divisions in selected country
     *
     * @param actionEvent
     */
    @FXML
    private void handleSetDivision(ActionEvent actionEvent) {
        ObservableList<Division> divisionListFiltered = FXCollections.observableArrayList();
        divisionListFiltered.setAll(divisionList);
        int countryId = countryCombo.getValue().getCountryId();
        divisionListFiltered.removeIf(division -> division.getCountryId() != countryId);
        divisionCombo.setItems(divisionListFiltered.sorted());
    }

    public void onSave(ActionEvent actionEvent) {
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
