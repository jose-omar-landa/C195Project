package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {
    public TextArea reportTextArea;
    public RadioButton aptByTypeRadio;
    public ToggleGroup reportsToggle;
    public RadioButton scheduleByContactRadio;
    public RadioButton aptByUserRadio;
    public Button cancelButton;
    public Label titleLable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onCancelButtonClicked(ActionEvent actionEvent) {
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
}
