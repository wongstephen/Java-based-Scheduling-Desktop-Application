package cms.stephenwongc195.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Country {
    private int countryId;
    private String countryName;
    private ObservableList<Division> divisions= FXCollections.observableArrayList();

    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public ObservableList<Division> getDivisions() {
        return divisions;
    }

    public void setDivisions(ObservableList<Division> divisions) {
        this.divisions = divisions;
    }

    @Override
    public String toString(){
        return countryName + " [" + countryId + "]";
    }
}
