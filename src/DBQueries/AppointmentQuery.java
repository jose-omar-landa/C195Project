package DBQueries;

import DBConnection.JDBC;
import Objects.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppointmentQuery {


    //All Appointments
    public static ObservableList<Appointments> allAppointmentsList() throws SQLException {

        ObservableList<Appointments> allAptList = FXCollections.observableArrayList();

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
                rs.getDate("Start").toLocalDate(),
                rs.getDate("End").toLocalDate(),
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





    //WeeklyAppointments
    public static ObservableList<Appointments> weeklyAppointmentsList() throws SQLException {

        ObservableList<Appointments> weeklyAptList = FXCollections.observableArrayList();

        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime oneWeekAgo = currentDate.minusDays(7);

        String sql = "SELECT * FROM appointments WHERE Start < ? AND Start > ?";

        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM appointments WHERE Start < ? AND Start > ?");

        ps.setDate(1, java.sql.Date.valueOf(currentDate.toLocalDate()));
        ps.setDate(2, java.sql.Date.valueOf(oneWeekAgo.toLocalDate()));

        try{

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Appointments apt = new Appointments(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        rs.getDate("Start").toLocalDate(),
                        rs.getDate("End").toLocalDate(),
                        rs.getDate("Create_Date"),
                        rs.getString("Created_By"),
                        rs.getDate("Last_Update"),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID"));
                weeklyAptList.add(apt);

            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return weeklyAptList;

    }


    //Monthly Appointments

    public static ObservableList<Appointments> monthlyAppointmentsList() throws SQLException {

        ObservableList<Appointments> monthlyAptList = FXCollections.observableArrayList();

        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime oneMonthAgo = currentDate.minusDays(30);

        String sql = "SELECT * FROM appointments WHERE Start < ? AND Start > ?";

        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM appointments WHERE Start < ? AND Start > ?");

        ps.setDate(1, java.sql.Date.valueOf(currentDate.toLocalDate()));
        ps.setDate(2, java.sql.Date.valueOf(oneMonthAgo.toLocalDate()));

        try{

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Appointments apt = new Appointments(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        rs.getDate("Start").toLocalDate(),
                        rs.getDate("End").toLocalDate(),
                        rs.getDate("Create_Date"),
                        rs.getString("Created_By"),
                        rs.getDate("Last_Update"),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID"));
                monthlyAptList.add(apt);

            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return monthlyAptList;

    }





    public static void createNewAppointment(int aptID, String aptTitle, String aptDescription, String aptLocation, String aptType,
                                            LocalDate aptStart, LocalDate aptEnd, int customerID, int userID, int contactID) throws SQLException {

        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement("INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

            ps.setInt(1, aptID);
            ps.setString(2, aptTitle);
            ps.setString(3, aptDescription);
            ps.setString(4, aptLocation);
            ps.setString(5, aptType);
            ps.setDate(6, Date.valueOf(aptStart));
            ps.setDate(7, Date.valueOf(aptEnd));
            ps.setInt(8, customerID);
            ps.setInt(9, userID);
            ps.setInt(10, contactID);

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
