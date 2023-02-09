package DBQueries;

import DBConnection.JDBC;
import Objects.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/** This class contains all the methods that allow database queries into the appointments table. */
public class AppointmentQuery {

    /** This method contains a database query that pulls all the appointments from within the
     * appointments table.
     * @return returns a list of all the appointments within the appointments table. */
    public static ObservableList<Appointments> allAppointmentsList() throws SQLException {
        ObservableList<Appointments> allAptList = FXCollections.observableArrayList();
        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM appointments");
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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

    /** This method provides a database query to create a new appointment within the appointments table.
     * @param aptID provides the appointment ID.
     * @param aptTitle provides the appointment title.
     * @param aptDescription provides the appointment description.
     * @param aptLocation provides the appointment location.
     * @param aptType provides the appointment type.
     * @param aptStart provides the appointment start date and time.
     * @param aptEnd provides the appointment end date and time.
     * @param customerID provides the customer ID.
     * @param userID provides the user ID.
     * @param contactID provides the contact ID. */
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

    /** This method provides a database query that allows the user to delete an appointment from the
     * appointments table.
     * @param appointmentIDNum provides the appointment ID number.
     * @return returns true if the deletion is successful or returns false if the deletion is unsuccessful. */
    public static boolean deleteAppointmentRecord(int appointmentIDNum) throws SQLException {
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement("DELETE from appointments WHERE Appointment_ID = ?;");
            ps.setInt(1, appointmentIDNum);
            ps.executeUpdate();
            if (ps.getUpdateCount() > 0) {
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

    /** This method provides a database query that allows the user to update an appointment within the
     * appointments table.
     * @param aptID provides the appointment ID.
     * @param aptTitle provides the appointment title.
     * @param aptDescription provides the appointment description.
     * @param aptLocation provides the appointment location.
     * @param aptType provides the appointment type.
     * @param aptStart provides the appointment start date and time.
     * @param aptEnd provides the appointment end date and time.
     * @param customerID provides the customer ID.
     * @param userID provides the user ID.
     * @param contactID provides the contact ID.
     * @return returns true if the update is successful and false if the update is unsuccessful. */
    public static boolean updateAppointmentRecord(String aptID, String aptTitle, String aptDescription, String aptLocation, String aptType,
                                                  Timestamp aptStart, Timestamp aptEnd, int customerID, int userID, int contactID) throws SQLException {
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement("UPDATE appointments SET Title=?, Description=?, Location=?, Type=?, Start=?, End=?, Customer_ID=?, Contact_ID=?, User_ID=? WHERE Appointment_ID = ?;");
            ps.setString(1, aptTitle);
            ps.setString(2, aptDescription);
            ps.setString(3, aptLocation);
            ps.setString(4, aptType);
            ps.setTimestamp(5, aptStart);
            ps.setTimestamp(6, aptEnd);
            ps.setInt(7, customerID);
            ps.setInt(8, contactID);
            ps.setInt(9, userID);
            ps.setString(10, aptID);
            ps.execute();
            if (ps.getUpdateCount() > 0) {
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

    /** This method provides a database query that allows the user to pull appointments by the associated
     * customer ID from the appointments table.
     * @param customerID provides the customer ID.
     * @return returns a list of appointments for the associated customer ID. */
    public static ObservableList<Appointments> pullAppointmentsByCustomerID(int customerID) throws SQLException {
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();
        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID = c.Contact_ID WHERE Customer_ID = ? ");
        ps.setInt(1, customerID);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
                appointments.add(apt);
            }
            return appointments;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** This method provides a database query that allows the user to generate a report of appointments
     * sorted by type and by month. The report is generated into a string using StringBuilder in order
     * to populate a provided text area on the Reports View Screen.
     * @return returns a report of appointments sorted by type and by month. */
    public static String reportForAppointmentsByTypeAndMonth() {
        try {
            StringBuilder reportAptTypeMonth = new StringBuilder("REPORT GENERATED BELOW: \n");
            reportAptTypeMonth.append("\n");
            PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT MONTHNAME(start) as Month, Type, COUNT(*) as Amount FROM appointments GROUP BY MONTH(start), type");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String month = rs.getString("Month");
                String type = rs.getString("Type");
                String amount = rs.getString("Amount");
                reportAptTypeMonth.append("Month: " + month + "\t Type: " + type + "\t Amount: " + amount + "\n" + "\n");
            }
            return reportAptTypeMonth.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Report Unable To Generate";
        }
    }

    /** This method provides a database query that allows the user to generate a report with a schedule
     * for each contact in your organization that includes appointment ID, title, type and description, start date
     * and time, end date and time, and customer ID. The report is generated into a string using StringBuilder in
     * order to populate a provided text area on the Reports View Screen.
     * @return returns a report of with a schedule for each contact in your organization that includes appointment ID,
     * title, type and description, start date and time, end date and time, and customer ID. */
    public static String reportOfScheduleByContact() {
        try {
            StringBuilder reportScheduleByContact = new StringBuilder("Report Generated Below:  \n");
            reportScheduleByContact.append("\n");
            PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT Contact_ID, Appointment_ID, Customer_ID, Title, Type, Description, Start, End FROM appointments ORDER BY Contact_ID");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int contactID = rs.getInt("Contact_ID");
                int aptID = rs.getInt("Appointment_ID");
                int custID = rs.getInt("Customer_ID");
                String title = rs.getString("Title");
                String type = rs.getString("Type");
                String description = rs.getString("Description");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                reportScheduleByContact.append("Contact ID: " + contactID + "\t Appointment ID: " + aptID + "\t Customer ID: " + custID + "\t Title: " + title + "\t Type: " + type + "\t Description: " + description + "\t Start: " + start + "\t End: " + end + "\n" + "\n");
            }
            return reportScheduleByContact.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Report Unable To Generate";
        }
    }

    /** This method allows the user to generate a report that sorts all appointments that have been scheduled by each User ID.
     * The report is generated into a string using StringBuilder in order to populate a text area provided in the
     * Reports View Screen.
     * @return returns a report that sorts all appointments that have been scheduled by each User ID. */
    public static String reportAppointmentsByUser() {
        try {
            StringBuilder reportAptByUser = new StringBuilder("Report Generated Below:  \n");
            reportAptByUser.append("\n");
            PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT User_ID, Contact_ID, Appointment_ID, Customer_ID, Title, Type, Description, Start, End FROM appointments ORDER BY User_ID");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                int aptID = rs.getInt("Appointment_ID");
                int custID = rs.getInt("Customer_ID");
                String title = rs.getString("Title");
                String type = rs.getString("Type");
                String description = rs.getString("Description");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                reportAptByUser.append("User ID: " + userID +"\t Contact ID: " + contactID + "\t Appointment ID: " + aptID + "\t Customer ID: " + custID + "\t Title: " + title + "\t Type: " + type + "\t Description: " + description + "\t Start: " + start + "\t End: " + end + "\n" + "\n");
            }
            return reportAptByUser.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Report Unable To Generate";
        }
    }

}
