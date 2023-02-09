package Controllers;

import DBQueries.CountriesQuery;
import DBQueries.CustomerQuery;
import DBQueries.DivisionQuery;
import Objects.Countries;
import Objects.Customers;
import Objects.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

/**This class allows the user the functionality to add a customer to the
 * customer directory within the application. */
public class AddCustomerController implements Initializable {
    public Button cancelButton;
    public TextField custNameTextField;
    public TextField custAddressTextField;
    public TextField addCustPostalCodeTextField;
    public TextField addCustPhoneTextField;
    public Button addCustSaveButton;
    public ComboBox<String> addCustDivisionComboBox;
    public ComboBox<String> addCustCountryComboBox;
    public TextField addCustIDTextField;

    /**This method creates the functionality for the Cancel button on the screen.
     * This button allows the user to return to the customer directory screen. */
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

    /**This method allows the user to save the data entered in the text fields by
     * clicking on the Save button on the screen. This method also generates an error if
     * any of the fields are left empty. If the data successfully saves, the user is returned
     * to the customer directory view screen. */
    public void onAddCustSaveClicked(ActionEvent actionEvent) throws SQLException {
        int id = CustomerQuery.allCustomersList().size() +1 ;
        String customerName = custNameTextField.getText();
        String customerAddress = custAddressTextField.getText();
        String customerPostalCode =addCustPostalCodeTextField.getText() ;
        String customerPhoneNumber = addCustPhoneTextField.getText();
        String divisions = addCustDivisionComboBox.getValue();
        String country = addCustCountryComboBox.getValue();

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
                        CustomerQuery.createNewCustomer(String.valueOf(id), customerName, customerAddress, customerPostalCode, customerPhoneNumber, divisions);
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

    /**This method creates the functionality to populate the Division combo box. */
    public void onAddCustDivisionComboBoxSelection() throws SQLException {
        ObservableList<String> listOfDivisions = FXCollections.observableArrayList();
        try {
            ObservableList<Divisions> divisions = DivisionQuery.pullDivisionByCountry(addCustCountryComboBox.getSelectionModel().getSelectedItem());
            if (divisions != null) {
                for (Divisions division : divisions) {
                    listOfDivisions.add(division.getDivision());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addCustDivisionComboBox.setItems(listOfDivisions);
    }

    /**This method creates the functionality to populate the Country combo box.
     * When this method is called, it will call the method to populate the
     * division combo box as well. */
    public void onAddCustCountryComboBoxSelection() throws SQLException{
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
        addCustCountryComboBox.setItems(listOfCountries);
        onAddCustDivisionComboBoxSelection();
    }

    /**This method initializes the controller and runs the method that populates
     * the country combo box, which then populates the division combo box as well.  */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            onAddCustCountryComboBoxSelection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

