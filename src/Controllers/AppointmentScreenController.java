package Controllers;

import DBQueries.AppointmentQuery;
import DBQueries.CustomerQuery;
import Objects.Appointments;
import Objects.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Controllers.MainController;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

/**This class allows the user to view the current schedule of appointments in a table view. */
public class AppointmentScreenController implements Initializable {
    public TableView<Appointments> tableViewSchedule;
    public TableColumn<Appointments, Integer> tableAptID;
    public TableColumn<Appointments, String> tableAptTitle;
    public TableColumn<Appointments, String> tableAptDescription;
    public TableColumn<Appointments, String> tableAptLocation;
    public TableColumn<Appointments, Integer> tableContact;
    public TableColumn<Appointments, String> tableType;
    public TableColumn<Appointments, Date> tableStart;
    public TableColumn<Appointments, Date> tableEnd;
    public TableColumn<Appointments, Integer> tableCustId;
    public TableColumn<Appointments, Integer> tableUserId;
    public RadioButton allAptRadio;
    public ToggleGroup aptRadioToggleGroup;
    public RadioButton weeklyAptRadio;
    public RadioButton monthlyAptRadio;
    public Label titleLabel;
    public Button addAptButton;
    public Button updateAptButton;
    public Button deleteAptButton;
    public Button viewDirectoryButton;
    public Button reportsButton;
    private Object Stage;
    public static Appointments selectedAppointmentData;

    /**This method will change the label above the appointment view table depending on the
     * radio button that is selected. */
    public void onChangeLabelText(InputMethodEvent inputMethodEvent) {
        aptRadioToggleGroup.getSelectedToggle();
        if(allAptRadio.isSelected()) {
            titleLabel.setText("Currently Viewing All Appointments:");
        }
        else if(weeklyAptRadio.isSelected()) {
            titleLabel.setText("Currently Viewing This Week's Appointments:");
        }
        else if (monthlyAptRadio.isSelected()) {
            titleLabel.setText("Currently Viewing This Month's Appointments:");
        }
    }

    /**This method will populate the appointment view table with all scheduled appointments
     * that are within the database. */
    public void onAllSelected(javafx.event.ActionEvent actionEvent) {
        aptRadioToggleGroup.getSelectedToggle();
        if(allAptRadio.isSelected()) {
            try {
                titleLabel.setText("Currently Viewing All Appointments:");
                tableViewSchedule.setItems(AppointmentQuery.allAppointmentsList());
                tableAptID.setCellValueFactory(new PropertyValueFactory<>("aptID"));
                tableAptTitle.setCellValueFactory(new PropertyValueFactory<>("aptTitle"));
                tableAptDescription.setCellValueFactory(new PropertyValueFactory<>("aptDescription"));
                tableAptLocation.setCellValueFactory(new PropertyValueFactory<>("aptLocation"));
                tableContact.setCellValueFactory(new PropertyValueFactory<>("contactID"));
                tableType.setCellValueFactory(new PropertyValueFactory<>("aptType"));
                tableStart.setCellValueFactory(new PropertyValueFactory<>("aptStart"));
                tableEnd.setCellValueFactory(new PropertyValueFactory<>("aptEnd"));
                tableCustId.setCellValueFactory(new PropertyValueFactory<>("customerID"));
                tableUserId.setCellValueFactory(new PropertyValueFactory<>("userID"));
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            }
        }

    /**This method will populate the appointment view table with appointments that are
     * scheduled within the current week. */
    public void onWeeklySelected(javafx.event.ActionEvent actionEvent) {
        aptRadioToggleGroup.getSelectedToggle();
        if (weeklyAptRadio.isSelected()) {
            titleLabel.setText("Currently Viewing This Week's Appointments:");
            try {
                ObservableList<Appointments> allAppointmentsList = AppointmentQuery.allAppointmentsList();
                ObservableList<Appointments> weekAppointmentList = FXCollections.observableArrayList();

                for (Appointments appointments : allAppointmentsList) {
                    if (appointments.getAptStart().isAfter(LocalDateTime.now().minusDays(1)) && appointments.getAptStart().isBefore(LocalDateTime.now().plusDays(7))) {
                        weekAppointmentList.add(appointments);
                    }
                }
                tableViewSchedule.setItems(weekAppointmentList);
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    /**This method will populate the appointment view table with appointments that are
     * scheduled within the current month. */
    public void onMonthlySelected(javafx.event.ActionEvent actionEvent) {
        aptRadioToggleGroup.getSelectedToggle();
        if (monthlyAptRadio.isSelected()) {
            titleLabel.setText("Currently Viewing This Month's Appointments:");
            try {
                ObservableList<Appointments> allAppointmentsList = AppointmentQuery.allAppointmentsList();
                ObservableList<Appointments> monthAppointmentList = FXCollections.observableArrayList();

                for (Appointments appointments : allAppointmentsList) {
                    if (appointments.getAptStart().getMonth() == LocalDate.now().getMonth()) {
                        monthAppointmentList.add(appointments);
                    }
                }
                tableViewSchedule.setItems(monthAppointmentList);
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    /**This method initializes the Appointment Screen Controller with all appointments shown on the
     * appointment table. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            titleLabel.setText("Currently Viewing All Appointments:");

            tableViewSchedule.setItems(AppointmentQuery.allAppointmentsList());
            tableAptID.setCellValueFactory(new PropertyValueFactory<>("aptID"));
            tableAptTitle.setCellValueFactory(new PropertyValueFactory<>("aptTitle"));
            tableAptDescription.setCellValueFactory(new PropertyValueFactory<>("aptDescription"));
            tableAptLocation.setCellValueFactory(new PropertyValueFactory<>("aptLocation"));
            tableContact.setCellValueFactory(new PropertyValueFactory<>("contactID"));
            tableType.setCellValueFactory(new PropertyValueFactory<>("aptType"));
            tableStart.setCellValueFactory(new PropertyValueFactory<>("aptStart"));
            tableEnd.setCellValueFactory(new PropertyValueFactory<>("aptEnd"));
            tableCustId.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            tableUserId.setCellValueFactory(new PropertyValueFactory<>("userID"));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**This method allows the functionality for the Add Appointment button, and loads the
     * Add Appointment screen for the user. */
    public void onAddAptButtonClick(javafx.event.ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("../FXML_Files/AddAppointmentScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Update Appointment");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**This method allows the functionality for the Update Appointment button, and loads the Update Appointment
     * Screen for the user. This method will also pull the data from the currently selected appointment
     * in order to pre-populate the text fields and combo boxes. This method will also generate an error if
     * an appointment is not already selected. */
    public void onUpdateAptButtonClick(javafx.event.ActionEvent actionEvent) {
        selectedAppointmentData = tableViewSchedule.getSelectionModel().getSelectedItem();

        try {
            if (selectedAppointmentData == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No Appoimtment Selected!");
                alert.setContentText("An existing appointment must be selected!");
                alert.showAndWait();

            } else {
                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("../FXML_Files/UpdateAppointmentScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.setTitle("Add Appointment");
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**This method allows the functionality for the Delete Appointment button. An error will generate
     * if an appointment is not selected prior to clicking the button. If an appointment is selected,
     * a warning will generate and the user will need to confirm that they want to delete the selected
     * appointment. If the user clicks yes, the appointment will be deleted from the database and the
     * appointment view table will be refreshed to reflect the change. */
    public void onDeleteAptButtonClick(javafx.event.ActionEvent actionEvent) throws SQLException {
        Appointments currentSelectedAppointment = tableViewSchedule.getSelectionModel().getSelectedItem();
        ;
        ObservableList<Appointments> allAppointments = AppointmentQuery.allAppointmentsList();

        if (currentSelectedAppointment == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setContentText("An appointment must be selected first!");
            alert.showAndWait();
        } else {
            try {
                int appointmentID = currentSelectedAppointment.getAptID();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Are You Sure?");
                alert.setContentText("Are you sure you want to delete the selected appointment?");
                Optional<ButtonType> deleteAppointmentConfirmation = alert.showAndWait();

                if (deleteAppointmentConfirmation.isPresent() && deleteAppointmentConfirmation.get() == ButtonType.OK) {
                    AppointmentQuery.deleteAppointmentRecord(appointmentID);

                    tableViewSchedule.setItems(allAppointments);
                    try {
                        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                        Parent scene = FXMLLoader.load(getClass().getResource("../FXML_Files/AppointmentViewScreen.fxml"));
                        stage.setScene(new Scene(scene));
                        stage.setTitle("Update Customer Information");
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**This method obtains the data of the currently selected appointment on the appointment view tabe.
     * @return returns the selected appointments data. */
    public static Appointments getSelectedAppointmentData() {
        return selectedAppointmentData;
    }

    /**This method allows the functionality for the View Customer Directory button. When the
     * button is clicked, the user will be taken to the Customer Directory screen. */
    public void onViewDirectoryButtonClick(javafx.event.ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("../FXML_Files/CustomerDirectory.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Customer Directory");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**This method allows the functionality for the Reports button. When clicked, the user will
     * be taken to the Reports View screen. */
    public void onReportsButtonClicked(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("../FXML_Files/ReportsScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Scheduled Appointments");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
