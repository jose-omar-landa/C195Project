package DBQueries;

import DBConnection.JDBC;
import Objects.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentQuery {

    public static ObservableList<Appointments> allAppointmentsList() throws SQLException {

        ObservableList<Appointments> allAptList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments";

        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM appointments");

        try{

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Appointments apt = new Appointments(
                rs.getInt("Appointment_ID"),
                rs.getString("Title"),
                rs.getString("Description"),
                rs.getString("Location"),
                rs.getString("Type"),
                rs.getDate("Start"),
                rs.getDate("End"),
                rs.getDate("Create_Date"),
                rs.getString("Created_By"),
                rs.getDate("Last_Update"),
                rs.getString("Last_Updated_By"),
                rs.getInt("Customer_ID"),
                rs.getInt("User_ID"),
                rs.getInt("Contact_ID"));
                allAptList.add(apt);

            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return allAptList;

    }



}
