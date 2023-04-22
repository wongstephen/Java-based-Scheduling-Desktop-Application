package cms.stephenwongc195.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class is as a model for countries
 */
public class Country {
    private int countryId;
    private String countryName;
    private ObservableList<Division> divisions= FXCollections.observableArrayList();

    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /**
     * Returns country id as an int
     * @return country id
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Sets country id as an int
     * @return void
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Returns country name as a String
     * @return country name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets country name as a String
     * @return void
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Returns divisions as an ObservableList
     * @return divisions
     */
    public ObservableList<Division> getDivisions() {
        return divisions;
    }

    /**
     * Sets divisions as an ObservableList
     * @return void
     */
    public void setDivisions(ObservableList<Division> divisions) {
        this.divisions = divisions;
    }

    /**
     * Overrides toString method to return country name and id
     * @return country name and id as a string
     */
    @Override
    public String toString(){
        return countryName + " [" + countryId + "]";
    }
}
