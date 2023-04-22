package cms.stephenwongc195.dao;

import cms.stephenwongc195.model.Country;
import cms.stephenwongc195.model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is a data access object for locations
 */
public class LocationDao {
    public static ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    public static ObservableList<Country> allCountries = FXCollections.observableArrayList();

    /**
     * Adds a division to the allDivisions ObservableList
     * @param division
     */
    public static void addDivision(Division division) {
        allDivisions.add(division);
    }

    /**
     * Adds a country to the allCountries ObservableList
     * @param country
     */
    public static void addCountry(Country country) {
        allCountries.add(country);
    }

    /**
     * Thie method returns a division based on the division id
     * @param countryId
     * @return
     */
    public static ObservableList<Division> lookupDivisionByCountry (int countryId) {
        ObservableList<Division> divisionListFilteredByCountry = FXCollections.observableArrayList();
        for (Division division : allDivisions) {
            if (division.getCountryId() == countryId) {
                divisionListFilteredByCountry.add(division);
            }
        }
        return divisionListFilteredByCountry;
    }

    /**
     * This method returns all divisions
     * @return allDivisions List
     */
    public static ObservableList<Division> getAllDivisions() {
        return allDivisions;
    }

    /**
     * This method returns all countries
     * @return allDivisions List
     */
    public static ObservableList<Country> getAllCountries() {
        return allCountries;
    }

    /**
     * Populates countries and divisions from database on load
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
