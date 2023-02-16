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


/**This class allows the user the functionality to add an appointment to the schedule within the application.
 *
 * Lambda expressions are used from Line 404 to Line 420. */
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
    public static int appointmentIDNum;

    /**This method takes the size of the total appointment list and adds 1 to generate a new appointment ID for
     * the appointment being added. */
    static {
        try {
            appointmentIDNum = AppointmentQuery.allAppointmentsList().size() + 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ComboBox<String> startTimeCombo;
    public ComboBox<String> endTimeCombo;

    private ZoneId zoneID = ZoneId.of("UTC");
    private ZoneId zoneIdEasternStandardTime = ZoneId.of("America/New_York");
    private ZoneId zoneIdDefault = ZoneId.systemDefault();

    /**This method converts the selected time to EST.
     * @param time The time parameter selects the local time of the user. */
    private ZonedDateTime convertTimeEST(LocalDateTime time) {
        return ZonedDateTime.of(time, ZoneId.of("America/New_York"));
    }

    /**This method adds functionality to the Cancel button on the add appointment page.
     * This allows the user to return to the appointment schedule view screen. */
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

    /**This method creates the functionality for populating the Contact combo box. */
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

    /**This method creates the functionality to populate the Customer ID combo box. */
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

    /**This method creates the functionality to populate the User ID combo box.*/
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

    /**This method creates the functionality for the Save button. This allows the user to save
     * the data entered on the text fields that will then populate as an appointment on the
     * appointment view screen. If the data is saved successfully, the user is returned to the
     * appointment view screen. */
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


    /**This method checks for errors in the data entry for a new appointment. This will generate an error for
     * the following reasons:
     * 1. Any of the fields are left empty.
     * 2. If the start and end dates are not on the same date.
     * 3. If the start time is after the end time.
     * 4. If the appointment overlaps with an existing appointment.
     * 5. If the appointment is scheduled outside of 0800 and 2200.
     * @param aptTitle this selects the appointment title from the respective text field.
     * @param aptDescription this selects the appointment description from the respective text field.
     * @param aptLocation this selects the appointment location from the respective text field.
     * @param aptID this selects the appointment ID from the respective text field.
     * @return returns either true or false depending on if conditions are met. */
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

        if (addAptType.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Appointment Type!");
            alert.setContentText("An appointment type is required!");
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

        try {
            LocalDateTime aptStart = LocalDateTime.of(addAptStartDateTime.getValue(), LocalTime.parse(startTimeCombo.getSelectionModel().getSelectedItem()));
            LocalDateTime aptEnd = LocalDateTime.of(addAptEndDateTime.getValue(), LocalTime.parse(endTimeCombo.getSelectionModel().getSelectedItem()));
            ZonedDateTime startUTC = aptStart.atZone(zoneID).withZoneSameInstant(ZoneId.of("UTC"));
            ZonedDateTime endUTC = aptEnd.atZone(zoneID).withZoneSameInstant(ZoneId.of("UTC"));
            Timestamp startTimeStamp = Timestamp.valueOf(startUTC.toLocalDateTime());
            Timestamp endTimeStamp = Timestamp.valueOf(endUTC.toLocalDateTime());

            try {
                ObservableList<Appointments> appointments = AppointmentQuery.pullAppointmentsByCustomerID(addAptCustomerID.getSelectionModel().getSelectedItem());
                for (Appointments currentAppointments : appointments) {
                    LocalDateTime currentAptStart = currentAppointments.getAptStart();
                    LocalDateTime curretAptEnd = currentAppointments.getAptEnd();
                    Timestamp aptStartTimeStamp = Timestamp.valueOf(currentAptStart);
                    Timestamp aptEndTimeStamp = Timestamp.valueOf(curretAptEnd);
                    LocalDate startDate = addAptStartDateTime.getValue();
                    LocalDate endDate = addAptEndDateTime.getValue();

                    if (startTimeStamp.after(aptStartTimeStamp) && startTimeStamp.before(aptEndTimeStamp) ||
                            endTimeStamp.after(aptStartTimeStamp) && endTimeStamp.before(aptEndTimeStamp) ||
                            startTimeStamp.before(aptStartTimeStamp) && endTimeStamp.after(aptStartTimeStamp) ||
                            startTimeStamp.equals(aptStartTimeStamp) && endTimeStamp.equals(aptEndTimeStamp) ||
                            startTimeStamp.equals(aptStartTimeStamp) ||
                            endTimeStamp.before(startTimeStamp) ||
                            endDate.isAfter(startDate)) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Appointment Date Or Time Error!");
                        alert.setContentText("Appointment must not overlap with existing appointment! Appointment start and end dates must be on the same day!");
                        alert.showAndWait();
                        return false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
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

    /**This method initializes the Add Appointment screen and pulls an auto generated
     * appointment ID number. This also runs the methods to populate the combo boxes on
     * the page.
     *
     * Lambda expressions are used from Line 404 to Line 420. These lambda expressions allowed me to
     * easily create a function that prevents the user from scheduling an appointment on a day before
     * the current date. All dates prior to the current dates are disabled. A lambda expression is used
     * on both the start date picker and the end date picker to achieve this goal.*/
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

        //lambda expression
        addAptStartDateTime.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate aptStartDate, boolean empty) {
                super.updateItem(aptStartDate, empty);
                setDisable(
                        empty || aptStartDate.isBefore(LocalDate.now()));
            }
        });
        //lambda expression
        addAptEndDateTime.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate aptEndDate, boolean empty) {
                super.updateItem(aptEndDate, empty);
                setDisable(
                        empty || aptEndDate.isBefore(LocalDate.now()));
            }
        });

    }

    public void onStartTimeComboBox() {

    }

    public void onEndTimeComboBox() {

    }
}
