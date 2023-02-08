package Controllers;

import DBQueries.AppointmentQuery;
import DBQueries.ContactsQuery;
import DBQueries.CustomerQuery;
import DBQueries.UsersQuery;
import Objects.Appointments;
import Objects.Contacts;
import Objects.Customers;
import Objects.Users;
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
import java.sql.Timestamp;
import java.time.*;
import java.util.ResourceBundle;

public class UpdateAppointmentController implements Initializable {
    public Button cancelButton;
    public ComboBox<Integer> updateAptContactComboBox;
    public TextField updateAptIDTextField;
    public TextField updateAptTitleTextField;
    public TextField updateAptDescriptionTextField;
    public TextField updateAptLocationTextField;
    public TextField updateAptTypeTextField;
    public DatePicker updateAptStartDate;
    public DatePicker updateAptEndDate;
    public Button updateAptSaveButton;
    public ComboBox<Integer> updateAptCustomerIdComboBox;
    public ComboBox<Integer> updateAptUserIdComboBox;
    public ComboBox<String> updateAptStartTime;
    public ComboBox<String> updateAptEndTime;

    private ZoneId zoneID = ZoneId.of("UTC");
    private ZoneId zoneIdEasternStandardTime = ZoneId.of("America/New_York");
    private ZoneId zoneIdDefault = ZoneId.systemDefault();

    private ZonedDateTime convertToEastern(LocalDateTime time) {
        return ZonedDateTime.of(time, ZoneId.of("America/New_York"));
    }

    private ZonedDateTime convertToTimeZone(LocalDateTime time, String zoneID) {
        return ZonedDateTime.of(time, ZoneId.of(zoneID));
    }

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

    public void onUpdateAptContactComboBoxClicked() {

        ObservableList<Integer> contactComboBox = FXCollections.observableArrayList();

        try {
            ObservableList<Contacts> selectContactID = ContactsQuery.allContactsList();
            for (Contacts contact : selectContactID) {
                contactComboBox.add(contact.getContactID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateAptContactComboBox.setItems(contactComboBox);


    }

    public void onUpdateAptStartDateClicked(ActionEvent actionEvent) {
    }

    public void onUpdateAptEndDateClicked(ActionEvent actionEvent) {
    }

    public void onUpdateAptSaveButtonClicked(ActionEvent actionEvent) {

        String appointmentID = updateAptIDTextField.getText();
        String appointmentTitle = updateAptTitleTextField.getText();
        String appointmendDescription = updateAptDescriptionTextField.getText();
        String appointmentLocation = updateAptLocationTextField.getText();
        String appointmentType = updateAptTypeTextField.getText();
        int contacts = updateAptContactComboBox.getSelectionModel().getSelectedItem();
        LocalDateTime aptStart = LocalDateTime.of(updateAptStartDate.getValue(), LocalTime.parse(updateAptStartTime.getSelectionModel().getSelectedItem()));
        LocalDateTime aptEnd = LocalDateTime.of(updateAptEndDate.getValue(), LocalTime.parse(updateAptEndTime.getSelectionModel().getSelectedItem()));
        int customerID = updateAptCustomerIdComboBox.getValue();
        int userID = updateAptUserIdComboBox.getValue();
        ZonedDateTime startUTC = aptStart.atZone(zoneID).withZoneSameInstant(ZoneId.of("UTC"));
        ZonedDateTime endUTC = aptEnd.atZone(zoneID).withZoneSameInstant(ZoneId.of("UTC"));
        Timestamp startTimeStamp = Timestamp.valueOf(startUTC.toLocalDateTime());
        Timestamp endTimeStamp = Timestamp.valueOf(endUTC.toLocalDateTime());

        if (updateAptDescriptionTextField.getText().isEmpty() ||
                updateAptTitleTextField.getText().isEmpty() ||
                updateAptLocationTextField.getText().isEmpty() ||
                updateAptTypeTextField.getText().isEmpty() ||
                updateAptStartTime.getSelectionModel().isEmpty() ||
                updateAptEndTime.getSelectionModel().isEmpty() ||
                updateAptStartDate.getControlCssMetaData().isEmpty() ||
                updateAptEndDate.getControlCssMetaData().isEmpty() ||
                updateAptContactComboBox.getSelectionModel().isEmpty() ||
                updateAptCustomerIdComboBox.getSelectionModel().isEmpty() ||
                updateAptUserIdComboBox.getSelectionModel().isEmpty()
        ) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("All fields required!");
            alert.showAndWait();

        } else {

            try {

                AppointmentQuery.updateAppointmentRecord(appointmentID, appointmentTitle, appointmendDescription, appointmentLocation, appointmentType, startTimeStamp, endTimeStamp, customerID, userID, contacts);
                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("../FXML_Files/AppointmentViewScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.setTitle("Customer Directory");
                stage.show();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }

    }


    public void onUpdateAptCustomerIDComboBoxClicked() {

        ObservableList<Integer> userIdComboBox = FXCollections.observableArrayList();

        try {
            ObservableList<Users> selectedUserID = UsersQuery.allUsersList();
            for (Users users : selectedUserID) {
                userIdComboBox.add(users.getUserID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateAptCustomerIdComboBox.setItems(userIdComboBox);

    }

    public void onUpdateAptUserIDComboBoxClicked() {

        ObservableList<Integer> customersComboBox = FXCollections.observableArrayList();

        try {
            ObservableList<Customers> selectCustomerId = CustomerQuery.allCustomersList();
            for (Customers customer : selectCustomerId) {
                customersComboBox.add(customer.getCustomerID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateAptUserIdComboBox.setItems(customersComboBox);


    }

    public void onUpdateAptStartTimeComboClicked(ActionEvent actionEvent) {
    }

    public void onUpdateAptEndTimeComboClicked(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        onUpdateAptContactComboBoxClicked();
        onUpdateAptUserIDComboBoxClicked();
        onUpdateAptCustomerIDComboBoxClicked();

        ObservableList<String> time = FXCollections.observableArrayList();
        LocalTime startTime = LocalTime.of(7, 0);
        LocalTime endTime = LocalTime.of(23, 0);
        time.add(startTime.toString());

        while (startTime.isBefore(endTime)) {
            startTime = startTime.plusMinutes(15);
            time.add(startTime.toString());
        }
        updateAptStartTime.setItems(time);
        updateAptEndTime.setItems(time);

        try {

            Appointments selectedAppointment = AppointmentScreenController.getSelectedAppointmentData();


            updateAptIDTextField.setText(String.valueOf(selectedAppointment.getAptID()));
            updateAptDescriptionTextField.setText(String.valueOf(selectedAppointment.getAptDescription()));
            updateAptTitleTextField.setText(String.valueOf(selectedAppointment.getAptTitle()));
            updateAptTypeTextField.setText(String.valueOf(selectedAppointment.getAptType()));
            updateAptLocationTextField.setText(String.valueOf(selectedAppointment.getAptLocation()));
            updateAptContactComboBox.getSelectionModel().select(selectedAppointment.getContactID());
            updateAptUserIdComboBox.getSelectionModel().select(selectedAppointment.getUserID());
            updateAptCustomerIdComboBox.getSelectionModel().select(Integer.valueOf(selectedAppointment.getCustomerID()));
            updateAptStartDate.setValue(selectedAppointment.getAptStart().toLocalDate());
            updateAptEndDate.setValue(selectedAppointment.getAptEnd().toLocalDate());
            updateAptStartTime.setValue(String.valueOf(selectedAppointment.getAptStart().toLocalTime()));
            updateAptEndTime.setValue(String.valueOf(selectedAppointment.getAptEnd().toLocalTime()));



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
