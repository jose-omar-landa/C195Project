package Controllers;

import DBQueries.AppointmentQuery;
import DBQueries.CustomerQuery;
import Objects.Appointments;
import Objects.Countries;
import Objects.Customers;
import Objects.Divisions;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.print.attribute.standard.ColorSupported;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**This class allows the user to view the Customer Directory and use contains the
 * methods for the functionality of all the buttons on the screen. */
public class CustomerDirectoryController implements Initializable {
    public TableView<Customers> customerDirectoryTable;
    public TableColumn<Customers, Integer> tableCustID;
    public TableColumn<Customers, String> tableCustName;
    public TableColumn<Customers, String> tableCustAddress;
    public TableColumn<Customers, String> tablePostalCode;
    public TableColumn<Customers, String> tablePhoneNumber;
    public TableColumn<Customers, Integer> tableDivision;
    public TableColumn<Customers, String> tableCountry;
    public Button addCustButton;
    public Button updateCustButton;
    public Button removeCustButton;
    public Button aptScheduleButton;
    public static Customers getCustomerData;

    /** This method allows the functionality for the Add Customer button.
     * When the button is clicked, the user is taken to the Add Customer screen. */
    public void onAddCustButtonClicked(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("../FXML_Files/AddCustomerScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Add New Customer");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** This method allows the functionality for the Update Customer button.
     * When clicked, the user is taken to the Update Customer screen. An error is
     * generated if a customer is not selected prior to clicking the button. When a
     * customer is selected, the customer's current data is obtained in order to
     * pre-populate the textfields on the Update Customer screen. */
    public void onUpdateCustButtonClicked(ActionEvent actionEvent) {
        getCustomerData = customerDirectoryTable.getSelectionModel().getSelectedItem();
        try {
            if (getCustomerData == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No Customer Selected!");
                alert.setContentText("An existing customer must be selected!");
                alert.showAndWait();
            } else {
                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("../FXML_Files/UpdateCustomerScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.setTitle("Update Customer Information");
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** This method allows the functionality for the Remove Customer button. When clicked, this button
     * will remove a customer from the database and from the customer directory view table. An error will
     * generate if a customer is not selected prior to clicking the button. An error will also generate if
     * the selected customer currently has an appointment scheduled. */
    public void onRemoveCustButtonClicked(ActionEvent actionEvent) throws SQLException {
        Customers currentSelectedCustomer = customerDirectoryTable.getSelectionModel().getSelectedItem();;
        ObservableList<Appointments> customerAppointments = AppointmentQuery.allAppointmentsList();
        ObservableList<Customers> allCustomers = CustomerQuery.allCustomersList();

        if (currentSelectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setContentText("A customer must be selected first!");
            alert.showAndWait();
        }
        else {
            for (Appointments selectedCustomersAppointments : customerAppointments) {
                if (selectedCustomersAppointments.getCustomerID() == currentSelectedCustomer.getCustomerID()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Appointments Present");
                    alert.setContentText("The selected customer currently has scheduled appointments! Please delete current appointments prior to deleting customer record!");
                    alert.showAndWait();
                    return;
                }
            }
        }

        try {
            if (currentSelectedCustomer != null) {
                int custIDNum = currentSelectedCustomer.getCustomerID();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Are You Sure?");
                alert.setContentText("Are you sure you want to delete the customer record?");
                Optional<ButtonType> deleteCustomerConfirmation = alert.showAndWait();

                if (deleteCustomerConfirmation.isPresent() && deleteCustomerConfirmation.get() == ButtonType.OK) {
                    CustomerQuery.deleteCustomerRecord(custIDNum);

                    customerDirectoryTable.setItems(allCustomers);
                    try {
                        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                        Parent scene = FXMLLoader.load(getClass().getResource("../FXML_Files/CustomerDirectory.fxml"));
                        stage.setScene(new Scene(scene));
                        stage.setTitle("Update Customer Information");
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** This method allows the functionality for the View Appointment Screen button. When the button
     * is clicked, the user is taken to the Appointment View Screen. */
    public void onAptScheduleClick(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("../FXML_Files/AppointmentViewScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Customer Directory");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** This method pulls the existing data of the currently selected customer.
     * @return returns the selected customer's existing data from the database. */
    public static Customers getCustomerData() {
        return getCustomerData;
    }



    /** This method initializes the Customer Directory Controller and populates the Customer
     * Directory table with the customers that exist within the database table. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            customerDirectoryTable.setItems(CustomerQuery.allCustomersList());
            tableCustID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            tableCustName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            tableCustAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
            tablePostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
            tablePhoneNumber.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
            tableDivision.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
            tableCountry.setCellValueFactory(new PropertyValueFactory<>("country"));


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
