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


    private ZonedDateTime convertTimeEST(LocalDateTime time) {
        return ZonedDateTime.of(time, ZoneId.of("America/New_York"));
    }


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

        boolean appointmentIsValid = appointmentErrorValidation(updateAptTitleTextField.getText(),
                updateAptDescriptionTextField.getText(),
                updateAptLocationTextField.getText(),
                updateAptIDTextField.getText());
        if (appointmentIsValid) {
            try {
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
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }


    private boolean appointmentErrorValidation(String aptTitle, String aptDescription, String aptLocation, String aptID) {

        if (aptTitle.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Appointment Title!");
            alert.setContentText("An appointment title is required!");
            alert.showAndWait();
            return false;
        }

        if (aptDescription.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Appointment Description!");
            alert.setContentText("An appointment description is required!");
            alert.showAndWait();
            return false;
        }

        if (aptLocation.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Appointment Location!");
            alert.setContentText("An appointment location is required!");
            alert.showAndWait();
            return false;
        }

        if (aptID.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Appointment ID!");
            alert.setContentText("An appointment ID is required!");
            alert.showAndWait();
            return false;
        }

        if (updateAptTypeTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Appointment Type!");
            alert.setContentText("An appointment type is required!");
            alert.showAndWait();
            return false;
        }

        if (updateAptContactComboBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Contact ID!");
            alert.setContentText("An appointment contact ID is required!");
            alert.showAndWait();
            return false;
        }

        if (updateAptCustomerIdComboBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Customer ID!");
            alert.setContentText("An appointment customer ID is required!");
            alert.showAndWait();
            return false;
        }

        if (updateAptUserIdComboBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Customer ID!");
            alert.setContentText("An appointment customer ID is required!");
            alert.showAndWait();
            return false;
        }

        if (updateAptStartDate.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Start Date!");
            alert.setContentText("An appointment start date is required!");
            alert.showAndWait();
            return false;
        }

        if (updateAptEndDate.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing End Date!");
            alert.setContentText("An appointment end date is required!");
            alert.showAndWait();
            return false;
        }

        if (updateAptStartTime.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Start Time!");
            alert.setContentText("An appointment start time is required!");
            alert.showAndWait();
            return false;
        }

        if (updateAptEndTime.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing End Time!");
            alert.setContentText("An appointment end time  is required!");
            alert.showAndWait();
            return false;
        }

        LocalTime enteredStartTime = LocalTime.parse(updateAptStartTime.getSelectionModel().getSelectedItem());
        LocalTime enteredEndTime = LocalTime.parse(updateAptEndTime.getSelectionModel().getSelectedItem());

        if (enteredEndTime.isBefore(enteredStartTime)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Time Error!");
            alert.setContentText("The appointment start time must be before the end time!");
            alert.showAndWait();
            return false;
        }

        LocalDate enteredStartDate = updateAptStartDate.getValue();
        LocalDate enteredEndDate = updateAptEndDate.getValue();

        if (!enteredStartDate.equals(enteredEndDate)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Date Error!");
            alert.setContentText("The appointment start date and end date must be on the same date!");
            alert.showAndWait();
            return false;
        }

        LocalDateTime selectedStartDateTime = enteredStartDate.atTime(enteredStartTime);
        LocalDateTime selectedEndDateTime = enteredEndDate.atTime(enteredEndTime);

        LocalDateTime requestedStart;
        LocalDateTime requestedEnd;

        try {

            ObservableList<Appointments> appointments = AppointmentQuery.pullAppointmentsByCustomerID(updateAptCustomerIdComboBox.getSelectionModel().getSelectedItem());
            for (Appointments currentAppointments : appointments) {
                requestedStart = currentAppointments.getAptStart();
                requestedEnd = currentAppointments.getAptEnd();

                if (requestedStart.isAfter(selectedStartDateTime) && requestedStart.isBefore(selectedEndDateTime)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setContentText("Requested appointment must not overlap with an existing appointment!");
                    alert.showAndWait();
                    return false;
                } else if (requestedEnd.isAfter(selectedStartDateTime) && requestedEnd.isBefore(selectedEndDateTime)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setContentText("Requested appointment must not overlap with an existing appointment!");
                    alert.showAndWait();
                    return false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        ZonedDateTime startTimeConversion = convertTimeEST(LocalDateTime.of(updateAptStartDate.getValue(), LocalTime.parse(updateAptStartTime.getSelectionModel().getSelectedItem())));
        ZonedDateTime endTimeConversion = convertTimeEST(LocalDateTime.of(updateAptEndDate.getValue(), LocalTime.parse(updateAptEndTime.getSelectionModel().getSelectedItem())));

        if (startTimeConversion.toLocalTime().isBefore(LocalTime.of(8, 0))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Appointments must be set between 0800 and 2200!");
            alert.showAndWait();
            return false;
        }

        if (startTimeConversion.toLocalTime().isAfter(LocalTime.of(22, 0))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Appointments must be set between 0800 and 2200!");
            alert.showAndWait();
            return false;
        }

        if (endTimeConversion.toLocalTime().isBefore(LocalTime.of(8,0))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Appointments must be set between 0800 and 2200!");
            alert.showAndWait();
            return false;
        }

        if (endTimeConversion.toLocalTime().isAfter(LocalTime.of(22, 0))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Appointments must be set between 0800 and 2200!");
            alert.showAndWait();
            return false;
        }

        return true;
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
