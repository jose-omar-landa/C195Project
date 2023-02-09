package DBQueries;

import DBConnection.JDBC;
import Objects.Contacts;
import Objects.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class provides methods that allow the user to access the contacts database table. */
public class ContactsQuery {

    /** This method provides a database query that allows the user to create a list of all the contacts
     * within the contacts database table.
     * @return returns a list of all contacts within the contacts database table. */
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

}
