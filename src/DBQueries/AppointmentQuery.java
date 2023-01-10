package DBQueries;

import DBConnection.JDBC;
import Objects.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentQuery {

    public static ObservableList<Appointments> allAppointmentsList() {

        ObservableList<Appointments> aptList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM appointments";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int aptID = rs.getInt("Appointment_ID");
                String aptTitle = rs.getString("Title");
                String aptDescription = rs.getString("Description");
                String aptLocation = rs.getString("Location");
                String aptType = rs.getString("Type");
                java.sql.Date aptStart = rs.getDate("Start");
                java.sql.Date aptEnd = rs.getDate("End");
                java.sql.Date createDate = rs.getDate("Create_Date");
                String createdBy = rs.getString("Created_By");
                java.sql.Date lastUpdated = rs.getDate("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerId = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                Appointments apt = new Appointments(aptID, aptTitle, aptDescription, aptLocation, aptType, aptStart,
                        aptEnd, createDate, createdBy, lastUpdated, lastUpdatedBy, customerId, userID, contactId);
                aptList.add(apt);

            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return aptList;

    }



}
