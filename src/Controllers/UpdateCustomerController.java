package Controllers;

import DBQueries.CountriesQuery;
import DBQueries.DivisionQuery;
import Objects.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Objects.Customers;
import Objects.Countries;

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


    }

    public void onUpdateCustomerDivisionComboBox(ActionEvent actionEvent) {
    }

    public void onUpdateCustomerSaveButtonClicked(ActionEvent actionEvent) {
    }

    public void onUpdateCustomerCountryComboBox(ActionEvent actionEvent) {
    }
}
