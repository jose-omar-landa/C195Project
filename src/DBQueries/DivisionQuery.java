package DBQueries;

import DBConnection.JDBC;
import Objects.Contacts;
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
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return divisionsList;
    }

    public static Divisions pullDivisionID(String divisionName) throws SQLException {
        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM first_level_divisions WHERE Division = ?;");
        ps.setString(1, divisionName);

        try{
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Divisions divisionID = new Divisions(
                        rs.getInt("Division_ID"),
                        rs.getString("Division"),
                        rs.getInt("Country_ID"));
                return divisionID;
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




    }




