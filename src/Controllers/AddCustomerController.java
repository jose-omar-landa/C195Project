package Controllers;

import DBQueries.CustomerQuery;
import Objects.Divisions;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public class AddCustomerController {
    public Button cancelButton;
    public TextField custNameTextField;
    public TextField custAddressTextField;
    public TextField addCustPostalCodeTextField;
    public TextField addCustPhoneTextField;
    public Button addCustSaveButton;
    public ComboBox addCustDivisionComboBox;

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
        boolean validEntry = validateIfEmpty(
                custNameTextField.getText(),
                custAddressTextField.getText(),
                addCustPostalCodeTextField.getText(),
                addCustPhoneTextField.getText());

        if (validEntry) {
            try {
                boolean successfullyCreated = CustomerQuery.createNewCustomer(
                        custNameTextField.getText(),
                        custAddressTextField.getText(),
                        addCustPostalCodeTextField.getText(),
                        addCustPhoneTextField.getText(),
                        String.valueOf(addCustDivisionComboBox.getValue()));

                if (successfullyCreated) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    Optional<ButtonType> userConfirmation = alert.showAndWait();

                    if (userConfirmation.isPresent() && (userConfirmation.get() == ButtonType.OK)) {
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
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("New customer creation failed!");
                    alert.showAndWait();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    private boolean validateIfEmpty(String name, String address, String postalCode, String phoneNum) {

        if (name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Text");
            alert.setContentText("Name field is required!");
            alert.showAndWait();
            return false;
        }

        if (address.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Text");
            alert.setContentText("Address field is required!");
            alert.showAndWait();
            return false;
        }

        if (postalCode.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Text");
            alert.setContentText("Postal code field is required!");
            alert.showAndWait();
            return false;
        }

        if (phoneNum.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Text");
            alert.setContentText("Phone number field is required!");
            alert.showAndWait();
            return false;
        }

        return true;
    }

}
