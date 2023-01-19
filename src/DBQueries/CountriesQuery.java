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

public class CountriesQuery {

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



    public static ObservableList<Countries> pullCountryDivision(int divisionID) throws SQLException {

        ObservableList<Countries> countryDivIDList = FXCollections.observableArrayList();

        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM countries AS country INNER JOIN first_level_divisions AS division ON country.Country_ID WHERE division.Division_ID = ?;");
        ps.setInt(1, divisionID);

        try{
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int countryID = rs.getInt("Country_ID");
                String country = rs.getString("Country");

                Countries newCountryList = new Countries(countryID, country);
                countryDivIDList.add(newCountryList);
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return countryDivIDList;
    }








}
