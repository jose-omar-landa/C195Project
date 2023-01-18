package Controllers;

import DBQueries.AppointmentQuery;
import DBQueries.CustomerQuery;
import Objects.Appointments;
import Objects.Countries;
import Objects.Customers;
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

    public void onUpdateCustButtonClicked(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("../FXML_Files/UpdateCustomerScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Update Customer Information");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        try {
            int custIDNum = currentSelectedCustomer.getCustomerID();
            for (Appointments selectedCustomersAppointments : customerAppointments) {
                if (selectedCustomersAppointments.getCustomerID() == currentSelectedCustomer.getCustomerID()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Appointments Present");
                    alert.setContentText("The selected customer currently has scheduled appointments! Please delete current appointments prior to deleting customer record!");
                    alert.showAndWait();
                }
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Are You Sure?");
            alert.setContentText("Are you sure you want to delete the customer record?");
            Optional<ButtonType> deleteCustomerConfirmation = alert.showAndWait();

            if (deleteCustomerConfirmation.isPresent() && deleteCustomerConfirmation.get() == ButtonType.OK) {
                CustomerQuery.deleteCustomerRecord(custIDNum);

                customerDirectoryTable.setItems(allCustomers);
                customerDirectoryTable.refresh();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
