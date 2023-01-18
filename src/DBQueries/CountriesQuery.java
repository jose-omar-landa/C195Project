package DBQueries;

import DBConnection.JDBC;
import Objects.Contacts;
import Objects.Countries;
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



}
