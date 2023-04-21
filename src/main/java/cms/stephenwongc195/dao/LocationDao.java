package cms.stephenwongc195.dao;

import cms.stephenwongc195.model.Country;
import cms.stephenwongc195.model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationDao {
    public static ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    public static ObservableList<Country> allCountries = FXCollections.observableArrayList();

    public static void addDivision(Division division) {
        allDivisions.add(division);
    }

    public static void addCountry(Country country) {
        allCountries.add(country);
    }

    public static ObservableList<Division> lookupDivisionByCountry (int countryId) {
        ObservableList<Division> divisionListFilteredByCountry = FXCollections.observableArrayList();
        for (Division division : allDivisions) {
            if (division.getCountryId() == countryId) {
                divisionListFilteredByCountry.add(division);
            }
        }
        return divisionListFilteredByCountry;
    }

    public static ObservableList<Division> getAllDivisions() {
        return allDivisions;
    }

    public static ObservableList<Country> getAllCountries() {
        return allCountries;
    }

    /**
     * Populates countries and divisions from database
     *
     */
    static {
        // populate countries
        try {
            ResultSet rs = Query.tableQuery("countries");
            while (rs.next()) {
                Country country = new Country(rs.getInt("Country_ID"), rs.getString("Country"));
                addCountry(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //populate divisions
        try {
            ResultSet rs = Query.tableQuery("first_level_divisions");
            while (rs.next()) {
                Division division = new Division(rs.getInt("Division_ID"), rs.getString("Division"), rs.getInt("COUNTRY_ID"));
                addDivision(division);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
