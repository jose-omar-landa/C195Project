package DBQueries;

import DBConnection.JDBC;
import Objects.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
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
                rs.getTimestamp("Start").toLocalDateTime(),
                rs.getTimestamp("End").toLocalDateTime(),
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

        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM appointments WHERE Start <= ? AND Start >= ?");

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
                        rs.getTimestamp("Start").toLocalDateTime(),
                        rs.getTimestamp("End").toLocalDateTime(),
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

        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM appointments WHERE Start <= ? AND Start >= ?");

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
                        rs.getTimestamp("Start").toLocalDateTime(),
                        rs.getTimestamp("End").toLocalDateTime(),
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





    public static void createNewAppointment(String aptID, String aptTitle, String aptDescription, String aptLocation, String aptType,
                                            Timestamp aptStart, Timestamp aptEnd, int customerID, int userID, int contactID) throws SQLException {

        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement("INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

            ps.setString(1, aptID);
            ps.setString(2, aptTitle);
            ps.setString(3, aptDescription);
            ps.setString(4, aptLocation);
            ps.setString(5, aptType);
            ps.setTimestamp(6, aptStart);
            ps.setTimestamp(7, aptEnd);
            ps.setInt(8, customerID);
            ps.setInt(9, userID);
            ps.setInt(10, contactID);

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static boolean deleteAppointmentRecord(int appointmentIDNum) throws SQLException {
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement("DELETE from appointments WHERE Appointment_ID = ?;");

            ps.setInt(1,appointmentIDNum);

            ps.executeUpdate();
            if (ps.getUpdateCount() > 0 ) {
                System.out.println(ps.getUpdateCount() + " rows affected by change.");
            } else {
                System.out.println("No changes to rows have occurred");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



}
