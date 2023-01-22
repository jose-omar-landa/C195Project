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


        if (addAptTitle.getText().isEmpty() ||
                addAptDescription.getText().isEmpty() ||
                addAptLocation.getText().isEmpty() ||
                addAptType.getText().isEmpty() ||
                addAptStartDateTime.getControlCssMetaData().isEmpty() ||
                addAptEndDateTime.getControlCssMetaData().isEmpty() ||
                addAptCustomerID.getSelectionModel().isEmpty() ||
                addAptUserID.getSelectionModel().isEmpty() ||
                addAptContact.getSelectionModel().isEmpty()
        ){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("All fields required!");
            alert.showAndWait();

        } else {
            try {
                AppointmentQuery.createNewAppointment(id, aptTitle, aptDescription, aptLocation, aptType,
                        startTimeStamp, endTimeStamp, customerID, userID, contactID);
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
