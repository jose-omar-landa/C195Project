package DBQueries;

import DBConnection.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginQuery {

    public LoginQuery() {

        try{
            String sql = "SELECT User_Name FROM users";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }






}
