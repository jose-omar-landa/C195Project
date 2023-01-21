package DBQueries;

import DBConnection.JDBC;
import Objects.Contacts;
import Objects.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersQuery {


    public static ObservableList<Users> allUsersList() throws SQLException {

        ObservableList<Users> userList = FXCollections.observableArrayList();

        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM users;");

        try{

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Users user = new Users(
                        rs.getInt("User_ID"),
                        rs.getString("User_Name"),
                        rs.getString("Password"));
                userList.add(user);

            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }



}
