package Controllers;


import DBQueries.AppointmentQuery;
import DBQueries.ContactsQuery;
import DBQueries.CustomerQuery;
import DBQueries.UsersQuery;
import Objects.*;
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
import java.security.spec.ECField;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    public Button cancelButton;
    public ComboBox<Integer> addAptContact;
    public TextField addAptTitle;
    public TextField addAptDescription;
    public TextField addAptLocation;
    public TextField addAptType;
    public DatePicker addAptStartDateTime;
    public DatePicker addAptEndDateTime;
    public Button addAptSaveButton;
    public ComboBox<Integer> addAptCustomerID;
    public ComboBox<Integer> addAptUserID;
    public TextField addAptIDTextField;

    public static int appointmentIDNum = 0;
    public ComboBox<String> startTimeCombo;
    public ComboBox<String> endTimeCombo;


    private ZoneId zoneID = ZoneId.of("UTC");
    private ZoneId zoneIdEasternStandardTime = ZoneId.of("America/New_York");
    private ZoneId zoneIdDefault = ZoneId.systemDefault();

    private ZonedDateTime convertTimeEST(LocalDateTime time) {
        return ZonedDateTime.of(time, ZoneId.of("America/New_York"));
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

    public void onAddAptContactComboBox() {

        ObservableList<Integer> contactComboBox = FXCollections.observableArrayList();

        try {
            ObservableList<Contacts> selectContactID = ContactsQuery.allContactsList();
                for (Contacts contact : selectContactID) {
                    contactComboBox.add(contact.getContactID());
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addAptContact.setItems(contactComboBox);

    }

    public void onStartDateTimeBox(ActionEvent actionEvent) {
    }

    public void onEndDateTimeBox(ActionEvent actionEvent) {
    }

    public void onAddAptCustomerIDComboBox() {
        ObservableList<Integer> userIdComboBox = FXCollections.observableArrayList();

        try {
        ObservableList<Users> selectedUserID = UsersQuery.allUsersList();
            for (Users users : selectedUserID) {
                userIdComboBox.add(users.getUserID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addAptUserID.setItems(userIdComboBox);
    }



    public void onAddAptUserIDComboBox() {

        ObservableList<Integer> customersComboBox = FXCollections.observableArrayList();

        try {
            ObservableList<Customers> selectCustomerId = CustomerQuery.allCustomersList();
            for (Customers customer : selectCustomerId) {
                customersComboBox.add(customer.getCustomerID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addAptCustomerID.setItems(customersComboBox);

    }



    public void onAddAptSaveButtonClicked(ActionEvent actionEvent) {

        boolean appointmentIsValid = appointmentErrorValidation(addAptTitle.getText(),
                addAptDescription.getText(),
                addAptLocation.getText(),
                addAptIDTextField.getText());

        if (appointmentIsValid) {
            try {
                int id = appointmentIDNum++;
                String aptTitle = addAptTitle.getText();
                String aptDescription = addAptDescription.getText();
                String aptLocation =addAptLocation.getText() ;
                String aptType = addAptType.getText();
                LocalDateTime aptStart = LocalDateTime.of(addAptStartDateTime.getValue(), LocalTime.parse(startTimeCombo.getSelectionModel().getSelectedItem()));
                LocalDateTime aptEnd = LocalDateTime.of(addAptEndDateTime.getValue(), LocalTime.parse(endTimeCombo.getSelectionModel().getSelectedItem()));
                int customerID = addAptCustomerID.getValue();
                int userID = addAptUserID.getValue();
                int contactID = addAptContact.getValue();
                ZonedDateTime startUTC = aptStart.atZone(zoneID).withZoneSameInstant(ZoneId.of("UTC"));
                ZonedDateTime endUTC = aptEnd.atZone(zoneID).withZoneSameInstant(ZoneId.of("UTC"));
                Timestamp startTimeStamp = Timestamp.valueOf(startUTC.toLocalDateTime());
                Timestamp endTimeStamp = Timestamp.valueOf(endUTC.toLocalDateTime());

                try {
                    AppointmentQuery.createNewAppointment(String.valueOf(id), aptTitle, aptDescription, aptLocation, aptType,
                            startTimeStamp, endTimeStamp, customerID, userID, contactID);
                    Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    Parent scene = FXMLLoader.load(getClass().getResource("../FXML_Files/AppointmentViewScreen.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.setTitle("Customer Directory");
                    stage.show();
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
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

        if (addAptContact.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Contact ID!");
            alert.setContentText("An appointment contact ID is required!");
            alert.showAndWait();
            return false;
        }

        if (addAptCustomerID.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Customer ID!");
            alert.setContentText("An appointment customer ID is required!");
            alert.showAndWait();
            return false;
        }

        if (addAptUserID.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Customer ID!");
            alert.setContentText("An appointment customer ID is required!");
            alert.showAndWait();
            return false;
        }

        if (addAptStartDateTime.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Start Date!");
            alert.setContentText("An appointment start date is required!");
            alert.showAndWait();
            return false;
        }

        if (addAptEndDateTime.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing End Date!");
            alert.setContentText("An appointment end date is required!");
            alert.showAndWait();
            return false;
        }

        if (startTimeCombo.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Start Time!");
            alert.setContentText("An appointment start time is required!");
            alert.showAndWait();
            return false;
        }

        if (endTimeCombo.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing End Time!");
            alert.setContentText("An appointment end time  is required!");
            alert.showAndWait();
            return false;
        }

        LocalTime enteredStartTime = LocalTime.parse(startTimeCombo.getSelectionModel().getSelectedItem());
        LocalTime enteredEndTime = LocalTime.parse(endTimeCombo.getSelectionModel().getSelectedItem());

        if (enteredEndTime.isBefore(enteredStartTime)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Time Error!");
            alert.setContentText("The appointment start time must be before the end time!");
            alert.showAndWait();
            return false;
        }

        LocalDate enteredStartDate = addAptStartDateTime.getValue();
        LocalDate enteredEndDate = addAptEndDateTime.getValue();

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

            ObservableList<Appointments> appointments = AppointmentQuery.pullAppointmentsByCustomerID(addAptCustomerID.getSelectionModel().getSelectedItem());
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

        ZonedDateTime startTimeConversion = convertTimeEST(LocalDateTime.of(addAptStartDateTime.getValue(), LocalTime.parse(startTimeCombo.getSelectionModel().getSelectedItem())));
        ZonedDateTime endTimeConversion = convertTimeEST(LocalDateTime.of(addAptEndDateTime.getValue(), LocalTime.parse(endTimeCombo.getSelectionModel().getSelectedItem())));

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




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addAptIDTextField.setText(Integer.toString(appointmentIDNum));

        onAddAptContactComboBox();
        onAddAptCustomerIDComboBox();
        onAddAptUserIDComboBox();


        ObservableList<String> time = FXCollections.observableArrayList();
        LocalTime startTime = LocalTime.of(7, 0);
        LocalTime endTime = LocalTime.of(23, 0);
        time.add(startTime.toString());

        while (startTime.isBefore(endTime)) {
            startTime = startTime.plusMinutes(15);
            time.add(startTime.toString());
        }
        startTimeCombo.setItems(time);
        endTimeCombo.setItems(time);



    }

    public void onStartTimeComboBox() {

    }

    public void onEndTimeComboBox() {

    }
}
