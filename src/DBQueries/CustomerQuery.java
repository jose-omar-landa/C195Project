package DBQueries;

import DBConnection.JDBC;
import Objects.Customers;
import Objects.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class CustomerQuery {


    public static ObservableList<Customers> allCustomersList() throws SQLException {
        ObservableList<Customers> allCustList = FXCollections.observableArrayList();
        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM customers AS cust INNER JOIN first_level_divisions AS " +
                "divisions ON cust.Division_ID = divisions.Division_ID INNER JOIN countries AS country ON country.Country_ID = divisions.Country_ID;");

        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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

    public static void createNewCustomer(String custName, String custAddress, String postalCode, String phoneNum, int division) throws SQLException {

        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement("INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?);");

            ps.setString(1, custName);
            ps.setString(2, custAddress);
            ps.setString(3, postalCode);
            ps.setString(4, phoneNum);
            ps.setInt(5, division);

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
