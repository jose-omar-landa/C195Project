package DBQueries;

import DBConnection.JDBC;
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

    public static ObservableList<Divisions> pullDivisionByCountry(int countryID) throws SQLException {

        ObservableList<Divisions> divCountryIDList = FXCollections.observableArrayList();

        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM first_level_divisions WHERE Country_ID = ?;");
        ps.setInt(1, countryID);

        try{
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int divID = rs.getInt("Division_ID");
                String div = rs.getString("Division");
                rs.getInt("Country_ID");

                Divisions newDivList = new Divisions(divID, div, countryID);
                divCountryIDList.add(newDivList);
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return divCountryIDList;
    }




    }




