package Controllers;

import DBQueries.AppointmentQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/** This class allows the user to generate three separate reports:
 * 1. A report that shows the total number of customer appointments by type and month.
 * 2. A report that shows a schedule for each contact in your organization that
 * includes appointment ID, title, type and description, start date and time, end date
 * and time, and customer ID.
 * 3. A report that shows all appointments scheduled per User, ordered by User ID. */
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

    /** This method allows the functionality of the Cancel button on the Reports screen.
     * When the button is clicked, the user is taken to the Appointment View screen. */
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

    /** This method populates the text area on the screen with the report of the total
     * number of customer appointments by type and month when the respective radio button is clicked. */
    public void onAptByTypeAndMonthSelected(ActionEvent actionEvent) {
        if (aptByTypeRadio.isSelected()) {
            reportTextArea.setText(AppointmentQuery.reportForAppointmentsByTypeAndMonth());
            titleLable.setText("Customer Appointments by Type/Month");
        }
    }

    /** This method populates the text area on the screen with a report that shows a schedule
     * for each contact in your organization that includes appointment ID, title, type and description,
     * start date and time, end date and time, and customer ID when the respective radio button is clicked. */
    public void onScheduleByContactSelected(ActionEvent actionEvent) {
        if (scheduleByContactRadio.isSelected()) {
            reportTextArea.setText(AppointmentQuery.reportOfScheduleByContact());
            titleLable.setText("Schedule by Contact");
        }
    }

    /** This method populates the text area on the screen with a report that shows all appointments scheduled
     * per User, ordered by User ID when the respective radio button is clicked. */
    public void onAptByUserSelected(ActionEvent actionEvent) {
        if (aptByUserRadio.isSelected()) {
            reportTextArea.setText(AppointmentQuery.reportAppointmentsByUser());
            titleLable.setText("Appointments By User");
        }
    }
}
