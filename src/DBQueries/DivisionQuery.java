package DBQueries;

import DBConnection.JDBC;
import Objects.Countries;
import Objects.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DivisionQuery {



    public static ObservableList<Divisions> allDivisionsList() throws SQLException {
        ObservableList<Divisions> divisionsList = FXCollections.observableArrayList();
        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM first_level_divisions;");

        try{
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Divisions division = new Divisions(
                        rs.getInt("Division_ID"),
                        rs.getString("Division"),
                        rs.getInt("Country_ID"));
                divisionsList.add(division);
            }
            return divisionsList;
        } catch (
                SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ObservableList<Divisions> pullDivisionByCountry(String country) throws SQLException {

        Countries newCountry = CountriesQuery.pullCountryID(country);

        ObservableList<Divisions> divisions = FXCollections.observableArrayList();

        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM first_level_divisions WHERE Country_ID = ?;");
        ps.setInt(1, newCountry != null ? newCountry.getCountryID() : 0);

        try{
            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                Divisions newDivision = new Divisions(
                rs.getInt("Division_ID"),
                rs.getString("Division"),
                rs.getInt("Country_ID"));

                divisions.add(newDivision);

            }
            return divisions;

        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public static Divisions pullDivisionID(String division) throws SQLException {

        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM first_level_divisions WHERE Division = ?;");
        ps.setString(1, division);

            try{
                ResultSet rs = ps.executeQuery();

                while(rs.next()){

                    Divisions newDivision = new Divisions(
                            rs.getInt("Division_ID"),
                            rs.getString("Division"),
                            rs.getInt("Country_ID"));

                    return newDivision;
                }
                } catch (Exception e) {
                     e.printStackTrace();
                }
            return null;
    }


    }




