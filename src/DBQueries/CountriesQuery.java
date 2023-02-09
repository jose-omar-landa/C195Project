package DBQueries;

import DBConnection.JDBC;
import Objects.Contacts;
import Objects.Countries;
import Objects.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class provides methods that allow the user to access the countries database table. */
public class CountriesQuery {

    /** This method provides a database query that allows the user to create a list of all countries
     * that are provided within the countries database table.
     * @return returns a list of all countries within the countries database table. */
    public static ObservableList<Countries> allCountriesList() throws SQLException {
        ObservableList<Countries> countryList = FXCollections.observableArrayList();
        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM countries;");
        try{
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Countries country = new Countries(
                        rs.getInt("Country_ID"),
                        rs.getString("Country"));
                countryList.add(country);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return countryList;
    }

    /** This method provides a database query that allows the user to create a list of countries by the associated
     * country ID number within the countries database.
     * @param country provides the country name.
     * @return returns a list of countries based on an associated country ID number from the countries
     * database table. */
    public static Countries pullCountryID(String country) throws SQLException {
        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM countries WHERE Country = ?;");
        ps.setString(1, country);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Countries newCountry = new Countries(
                        rs.getInt("Country_ID"),
                        rs.getString("Country"));
                return newCountry;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
