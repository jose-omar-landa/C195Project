package Controllers;


import DBQueries.ContactsQuery;
import DBQueries.CustomerQuery;
import DBQueries.UsersQuery;
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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addAptIDTextField.setText(Integer.toString(appointmentIDNum));

        onAddAptContactComboBox();
        onAddAptCustomerIDComboBox();
        onAddAptUserIDComboBox();


    }
}
