package DBQueries;

import DBConnection.JDBC;
import Objects.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class contains the methods for database queries into the users database table. */
public class LoginQuery {

    /** This method provides a database query that verifies if a provided username matches with
     * a provided password within the users database table.
     * @param userName provides the username.
     * @param password provides the password.
     * @return returns false if the username and password do not match a user within the database. */
    public static boolean existingUserAndPassword(String userName, String password) throws SQLException {
        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM users WHERE User_Name = ? AND Password = ?;");
        ps.setString(1, userName);
        ps.setString(2, password);
        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();
            return (rs.next());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error" + e.getMessage());
            return false;
        }
    }

    /** This method provides a database query that allows the user to create a list of all users within the
     * users database table.
     * @return returns a list of all users within the users database table. */
    public static ObservableList<Users> allUsers() throws SQLException {
        ObservableList<Users> allUsersList = FXCollections.observableArrayList();
        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM users;");
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Users allUsers = new Users(
                        rs.getInt("User_ID"),
                        rs.getString("User_Name"),
                        rs.getString("Password"));
                allUsersList.add(allUsers);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return allUsersList;
    }

}
