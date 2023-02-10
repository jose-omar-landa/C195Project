package DBQueries;

import DBConnection.JDBC;
import Objects.Countries;
import Objects.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class contains the methods for database queries into the first_level_divisions table of the
 * database. */
public class DivisionQuery {

    /** This method provides a database query that allows the user to create a list of all divisions within
     * the first_level_divisions table of the database.
     *
     *@return returns a list of all divisions within the first_level_divisions table. */
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

    /**This method provides a database query that allows the user to pull divisions from the
     * first_level_divisions table of the database by the provided country name.
     * @param country provides the country name.
     * @return returns a list of divisions from the first_level_divisions table by country name. */
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

    /** This method provides a database query that allows the user to pull a specific division from the
     * first_level_divisions table by a specific division ID number.
     * @param division provides the division.
     * @return returns a division from the first_level_division table by the specific division ID. */
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




