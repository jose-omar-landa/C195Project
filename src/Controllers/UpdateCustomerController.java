package Controllers;

import DBQueries.CountriesQuery;
import DBQueries.CustomerQuery;
import DBQueries.DivisionQuery;
import Objects.Divisions;
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

public class UpdateCustomerController implements Initializable {
    public Button cancelButton;
    public ComboBox<Divisions> updateCustomerDivision;
    public TextField updateCustomerID;
    public TextField updateCustomerName;
    public TextField updateCustomerAddress;
    public TextField updateCustomerPostalCode;
    public TextField updateCustomerPhoneNumber;
    public ComboBox<Countries> updateCustomerCountry;
    public Button updateCustomerSave;


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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            Customers selectedCustomerRecord = CustomerDirectoryController.getUpdateCustomerRecordData();

            updateCustomerCountry.setItems(CountriesQuery.allCountriesList());

            updateCustomerID.setText(String.valueOf(selectedCustomerRecord.getCustomerID()));
            updateCustomerName.setText(selectedCustomerRecord.getCustomerName());
            updateCustomerAddress.setText(selectedCustomerRecord.getCustomerAddress());
            updateCustomerPostalCode.setText(selectedCustomerRecord.getPostalCode());
            updateCustomerPhoneNumber.setText(selectedCustomerRecord.getCustomerPhone());
//            updateCustomerCountry.getSelectionModel().select(selectedCustomerRecord.getCountry());
//            updateCustomerDivision.getSelectionModel().select(selectedCustomerRecord.getDivisionID());


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateCustomerDivisionComboBox(ActionEvent actionEvent) {

    }

    public void onUpdateCustomerCountryComboBox(ActionEvent actionEvent) throws SQLException {
        int countryID = updateCustomerCountry.getValue().getCountryID();

        try {
            updateCustomerDivision.setItems(DivisionQuery.pullDivisionByCountry(countryID));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void onUpdateCustomerSaveButtonClicked(ActionEvent actionEvent) {

        String customerName = updateCustomerName.getText();
        String customerAddress = updateCustomerAddress.getText();
        String customerPostalCode =updateCustomerPostalCode.getText() ;
        String customerPhoneNumber = updateCustomerPhoneNumber.getText();
        Divisions divisions = updateCustomerDivision.getValue();
        int divisionID = divisions.getDivisionID();
        Countries country = updateCustomerCountry.getValue();


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
                int id = CustomerDirectoryController.getUpdateCustomerRecordData().getCustomerID();
                CustomerQuery.deleteCustomerRecord(id);

                CustomerQuery.createNewCustomer(customerName, customerAddress, customerPostalCode, customerPhoneNumber, divisionID);
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
