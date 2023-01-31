package Controllers;

import DBQueries.CountriesQuery;
import DBQueries.CustomerQuery;
import DBQueries.DivisionQuery;
import Objects.Countries;
import Objects.Customers;
import Objects.Divisions;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {
    public Button cancelButton;
    public TextField custNameTextField;
    public TextField custAddressTextField;
    public TextField addCustPostalCodeTextField;
    public TextField addCustPhoneTextField;
    public Button addCustSaveButton;
    public ComboBox<Divisions> addCustDivisionComboBox;
    public ComboBox<Countries> addCustCountryComboBox;

    public TextField addCustIDTextField;

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

    public void onAddCustSaveClicked(ActionEvent actionEvent) throws SQLException {

        int id = 0;
        for (Customers customer : CustomerQuery.allCustomersList()) {
            if (customer.getCustomerID() < id) {
                id = customer.getCustomerID();
            }
            else {
                id++;
            }
        }


        String customerName = custNameTextField.getText();
        String customerAddress = custAddressTextField.getText();
        String customerPostalCode =addCustPostalCodeTextField.getText() ;
        String customerPhoneNumber = addCustPhoneTextField.getText();
        Divisions divisions = addCustDivisionComboBox.getValue();
        int divisionID = divisions.getDivisionID();
        Countries country = addCustCountryComboBox.getValue();


                if (custNameTextField.getText().isEmpty() ||
                 custAddressTextField.getText().isEmpty() ||
                 addCustPostalCodeTextField.getText().isEmpty() ||
                addCustPhoneTextField.getText().isEmpty() ||
                addCustDivisionComboBox.getSelectionModel().isEmpty() ||
                addCustCountryComboBox.getSelectionModel().isEmpty()) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setContentText("All fields required!");
                    alert.showAndWait();

                } else {
                    try {
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

    public void onAddCustDivisionComboBoxSelection(ActionEvent actionEvent) {
    }

    public void onAddCustCountryComboBoxSelection(ActionEvent actionEvent) throws SQLException {
        int countryID = addCustCountryComboBox.getValue().getCountryID();

        try {
            addCustDivisionComboBox.setItems(DivisionQuery.pullDivisionByCountry(countryID));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            addCustCountryComboBox.setItems(CountriesQuery.allCountriesList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}

