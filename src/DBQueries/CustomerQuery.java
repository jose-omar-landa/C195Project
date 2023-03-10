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

/** This class contains all the methods for database quieries into the customers table of
 * the database.  */
public class CustomerQuery {

    /** This method provides a database query that allows the user to create a list of all the customers within
     * the customers database table.
     *
     * @return returns a list of all customers within the customers database table. */
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
                        rs.getString("Country"),
                        rs.getString("Division"));
                allCustList.add(cust);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return allCustList;
    }

    public static void createNewCustomer(String id, String custName, String custAddress, String postalCode, String phoneNum, String division) throws SQLException {

        Divisions newDivision = DivisionQuery.pullDivisionID(division);

        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement("INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?);");

            ps.setString(1, custName);
            ps.setString(2, custAddress);
            ps.setString(3, postalCode);
            ps.setString(4, phoneNum);
            ps.setInt(5, newDivision.getDivisionID());

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** This method provides a database query that allows the user to delete a customer from the customer
     * database table.
     *@param customerIDNum provides the customer ID number.
     * @return returns true if deletion is successful or returns false if unsuccessful. */
    public static boolean deleteCustomerRecord(int customerIDNum) throws SQLException {
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement("DELETE from customers WHERE Customer_ID = ?;");
            ps.setInt(1,customerIDNum);
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

    /** This method provides a database query that allows the user to update the date of an existing
     * customer within the customer database table.
     * @param custID provides the customer ID.
     * @param custName provides the customer name.
     * @param custAddress provides the customer address.
     * @param postalCode provides the customer postal code.
     * @param phoneNum provides the customer phone number.
     * @param division provides the customer division.
     * @return returns true if the update is successful or false if unsuccessful. */
    public static boolean updateCustomerAccount(String custID, String custName, String custAddress, String postalCode, String phoneNum, String division) throws SQLException {
        Divisions newDivision = DivisionQuery.pullDivisionID(division);
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement("UPDATE customers SET Customer_Name=?, Address=?, Postal_Code=?, Phone=?, Division_ID=? WHERE Customer_ID = ?;");
            ps.setString(1, custName);
            ps.setString(2, custAddress);
            ps.setString(3, postalCode);
            ps.setString(4, phoneNum);
            ps.setInt(5,newDivision.getDivisionID());
            ps.setString(6, custID);
            ps.execute();
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
