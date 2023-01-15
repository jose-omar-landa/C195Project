package Controllers;

import DBQueries.AppointmentQuery;
import Objects.Appointments;
import javafx.collections.ObservableList;
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

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

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
    private Object Stage;

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


    public void onWeeklySelected(javafx.event.ActionEvent actionEvent) {
        aptRadioToggleGroup.getSelectedToggle();
        if (weeklyAptRadio.isSelected()) {
            try {
                titleLabel.setText("Currently Viewing This Week's Appointments:");

                tableViewSchedule.setItems(AppointmentQuery.weeklyAppointmentsList());
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

    public void onMonthlySelected(javafx.event.ActionEvent actionEvent) {
        aptRadioToggleGroup.getSelectedToggle();
        if (monthlyAptRadio.isSelected()) {
            try {
                titleLabel.setText("Currently Viewing This Month's Appointments:");

                tableViewSchedule.setItems(AppointmentQuery.monthlyAppointmentsList());
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

    public void onUpdateAptButtonClick(javafx.event.ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("../FXML_Files/UpdateAppointmentScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Add Appointment");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDeleteAptButtonClick(javafx.event.ActionEvent actionEvent) {
    }

    public void onViewDirectoryButtonClick(javafx.event.ActionEvent actionEvent) {
    }
}
