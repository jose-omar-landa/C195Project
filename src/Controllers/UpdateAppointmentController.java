package Controllers;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalTime;
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



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
