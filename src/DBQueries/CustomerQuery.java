package DBQueries;

import DBConnection.JDBC;
import Objects.Appointments;
import Objects.Customers;
import Objects.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static javafx.collections.FXCollections.*;

public class CustomerQuery {


    public static ObservableList<Customers> allCustomersList() throws SQLException {

        ObservableList<Customers> allCustList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customers JOIN countries";

        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM customers AS cust INNER JOIN first_level_divisions AS " +
                "divisions ON cust.Division_ID = divisions.Division_ID INNER JOIN countries AS country ON country.Country_ID = divisions.Country_ID;");

        try{

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Customers cust = new Customers(
                        rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"),
                        rs.getString("Address"),
                        rs.getString("Postal_Code"),
                        rs.getString("Phone"),
                        rs.getDate("Create_Date"),
                        rs.getString("Created_By"),
                        rs.getDate("Last_Update"),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Division_ID"),
                        rs.getString("Country"));
                allCustList.add(cust);

            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return allCustList;

    }




}
