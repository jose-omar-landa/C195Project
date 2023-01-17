package DBQueries;

import DBConnection.JDBC;
import Objects.Contacts;
import Objects.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsQuery {

    public static ObservableList<Contacts> allContactsList() throws SQLException {

        ObservableList<Contacts> contactList = FXCollections.observableArrayList();

        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM contacts;");

        try{

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Contacts contact = new Contacts(
                        rs.getInt("Contact_ID"),
                        rs.getString("Contact_Name"),
                        rs.getString("Email"));
                contactList.add(contact);

            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return contactList;
    }


    public static Contacts pullContactID(String contactName) throws SQLException {
         PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM contacts WHERE Contact_Name = ?;");
         ps.setString(1, contactName);

        try{
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Contacts contactID = new Contacts(
                        rs.getInt("Contact_ID"),
                        rs.getString("Contact_Name"),
                        rs.getString("Email"));
                return contactID;
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
