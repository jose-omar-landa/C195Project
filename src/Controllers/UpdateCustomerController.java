package Controllers;

import DBQueries.CountriesQuery;
import DBQueries.CustomerQuery;
import DBQueries.DivisionQuery;
import Objects.Divisions;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Objects.Customers;
import Objects.Countries;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/** This class allows the user to update an existing customer record. */
public class UpdateCustomerController implements Initializable {
    public Button cancelButton;
    public ComboBox<String> updateCustomerDivision;
    public TextField updateCustomerID;
    public TextField updateCustomerName;
    public TextField updateCustomerAddress;
    public TextField updateCustomerPostalCode;
    public TextField updateCustomerPhoneNumber;
    public ComboBox<String> updateCustomerCountry;
    public Button updateCustomerSave;

    /** This method allows functionality for the Cancel button. When the cancel button is
     * clicked, the user is taken back to the Customer Directory screen. */
    public void onCancelClicked(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("../FXML_Files/CustomerDirectory.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Scheduled Appointments");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** This method initializes the Update Customer Controller. The selected customer data is pulled in order
     * to pre-populate the text fields and combo boxes. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Customers selectedCustomerData = CustomerDirectoryController.getCustomerData();
            updateCustomerID.setText(String.valueOf(selectedCustomerData.getCustomerID()));
            updateCustomerName.setText(selectedCustomerData.getCustomerName());
            updateCustomerAddress.setText(selectedCustomerData.getCustomerAddress());
            updateCustomerPhoneNumber.setText(selectedCustomerData.getCustomerAddress());
            updateCustomerPostalCode.setText(selectedCustomerData.getCustomerAddress());
            updateCustomerCountry.getSelectionModel().select(selectedCustomerData.getCountry());
            updateCustomerDivision.getSelectionModel().select(selectedCustomerData.getDivision());

            onUpdateCustomerCountryComboBox();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** This method populates the Division combo box. */
    public void onUpdateCustomerDivisionComboBox() throws SQLException {

        ObservableList<String> listOfDivisions = FXCollections.observableArrayList();
        try {
            ObservableList<Divisions> divisions = DivisionQuery.pullDivisionByCountry(updateCustomerCountry.getSelectionModel().getSelectedItem());
            if (divisions != null) {
                for (Divisions division : divisions) {
                    listOfDivisions.add(division.getDivision());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateCustomerDivision.setItems(listOfDivisions);
    }

    /** This method populates the Country combo box. */
    public void onUpdateCustomerCountryComboBox() throws SQLException {
        ObservableList<String> listOfCountries = FXCollections.observableArrayList();
        try {
            ObservableList<Countries> countries = CountriesQuery.allCountriesList();
            if (countries != null) {
                for (Countries country : countries) {
                    listOfCountries.add(country.getCountry());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateCustomerCountry.setItems(listOfCountries);
        onUpdateCustomerDivisionComboBox();
    }

    /** This method allows the functionality of the Save button. When the save button is clicked,
     * the entered data is saved into the database and into the Customer Directory table on the
     * Customer Directory Screen. If the data is saved successfully, the user is taken back to the
     * Customer Directory Screen. An error will generate if any of the text fields or combo boxes
     * are left empty. */
    public void onUpdateCustomerSaveButtonClicked(ActionEvent actionEvent) {

        String customerID = updateCustomerID.getText();
        String customerName = updateCustomerName.getText();
        String customerAddress = updateCustomerAddress.getText();
        String customerPostalCode =updateCustomerPostalCode.getText() ;
        String customerPhoneNumber = updateCustomerPhoneNumber.getText();
        String divisions = updateCustomerDivision.getValue();
        String country = updateCustomerCountry.getValue();

        if (updateCustomerName.getText().isEmpty() ||
                updateCustomerAddress.getText().isEmpty() ||
                updateCustomerPostalCode.getText().isEmpty() ||
                updateCustomerPhoneNumber.getText().isEmpty() ||
                updateCustomerDivision.getSelectionModel().isEmpty() ||
                updateCustomerCountry.getSelectionModel().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("All fields required!");
            alert.showAndWait();
        } else {
            try {
                CustomerQuery.updateCustomerAccount(customerID, customerName, customerAddress, customerPostalCode, customerPhoneNumber, divisions);
                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("../FXML_Files/CustomerDirectory.fxml"));
                stage.setScene(new Scene(scene));
                stage.setTitle("Customer Directory");
                stage.show();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
