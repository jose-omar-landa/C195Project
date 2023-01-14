package Controllers;

import DBQueries.AppointmentQuery;
import Objects.Appointments;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Controllers.MainController;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
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
    public CheckBox weeklyAptCheckbox;
    public CheckBox monthlyAptCheckbox;
    public CheckBox allAptCheckbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }








    }

    public void showAppointments(ActionEvent actionEvent) throws SQLException {
        ObservableList<Appointments> aptList = AppointmentQuery.allAppointmentsList();
        System.out.println(AppointmentQuery.allAppointmentsList());
    }


    public void onWeekCheckboxClick(javafx.event.ActionEvent actionEvent) {

    }

    public void onMonthCheckboxClick(javafx.event.ActionEvent actionEvent) {
    }

    public void onAllAptCheckboxClick(javafx.event.ActionEvent actionEvent) {
    }
}
