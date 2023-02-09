package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
    }
}
