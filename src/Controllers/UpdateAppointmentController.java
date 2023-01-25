package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateAppointmentController {
    public Button cancelButton;
    public ComboBox updateAptContactComboBox;
    public TextField updateAptIDTextField;
    public TextField updateAptTitleTextField;
    public TextField updateAptDescriptionTextField;
    public TextField updateAptLocationTextField;
    public TextField updateAptTypeTextField;
    public DatePicker updateAptStartDate;
    public DatePicker updateAptEndDate;
    public Button updateAptSaveButton;
    public ComboBox updateAptCustomerIdComboBox;
    public ComboBox updateAptUserIdComboBox;
    public ComboBox updateAptStartTime;
    public ComboBox updateAptEndTime;

    public void onCancelClicked(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("../FXML_Files/AppointmentViewScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Scheduled Appointments");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onUpdateAptContactComboBoxClicked(ActionEvent actionEvent) {
    }

    public void onUpdateAptStartDateClicked(ActionEvent actionEvent) {
    }

    public void onUpdateAptEndDateClicked(ActionEvent actionEvent) {
    }

    public void onUpdateAptSaveButtonClicked(ActionEvent actionEvent) {
    }

    public void onUpdateAptCustomerIDComboBoxClicked(ActionEvent actionEvent) {
    }

    public void onUpdateAptUserIDComboBoxClicked(ActionEvent actionEvent) {
    }

    public void onUpdateAptStartTimeComboClicked(ActionEvent actionEvent) {
    }

    public void onUpdateAptEndTimeComboClicked(ActionEvent actionEvent) {
    }
}
