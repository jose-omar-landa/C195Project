package Controllers;

import DBQueries.AppointmentQuery;
import Objects.Appointments;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Controllers.MainController;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.lang.reflect.AccessibleObject;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class AppointmentScreenController implements Initializable {
    public TableView<Appointments> weeklyViewSchedule;
    public TableView<Appointments> monthlyViewSchedule;
    public TableColumn<Appointments, Integer> weeklyAptID;
    public TableColumn<Appointments, String> weeklyAptTitle;
    public TableColumn<Appointments, String> weeklyAptDescription;
    public TableColumn<Appointments, String> weeklyAptLocation;
    public TableColumn<Appointments, Integer> weeklyContact;
    public TableColumn<Appointments, String> weeklyType;
    public TableColumn<Appointments, Date> weeklyStart;
    public TableColumn<Appointments, Date> weeklyEnd;
    public TableColumn<Appointments, Integer> weeklyCustId;
    public TableColumn<Appointments, Integer> weeklyUserId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        weeklyViewSchedule.setItems(AppointmentQuery.allAppointmentsList());
        weeklyAptID.setCellValueFactory(new PropertyValueFactory<>("aptID"));
        weeklyAptTitle.setCellValueFactory(new PropertyValueFactory<>("aptTitle"));
        weeklyAptDescription.setCellValueFactory(new PropertyValueFactory<>("aptDescription"));
        weeklyAptLocation.setCellValueFactory(new PropertyValueFactory<>("aptLocation"));
        weeklyContact.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        weeklyType.setCellValueFactory(new PropertyValueFactory<>("aptType"));
        weeklyStart.setCellValueFactory(new PropertyValueFactory<>("aptStart"));
        weeklyEnd.setCellValueFactory(new PropertyValueFactory<>("aptEnd"));
        weeklyCustId.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        weeklyUserId.setCellValueFactory(new PropertyValueFactory<>("userID"));







    }

    public void showAppointments(ActionEvent actionEvent) {
        ObservableList<Appointments> aptList = AppointmentQuery.allAppointmentsList();
        System.out.println(AppointmentQuery.allAppointmentsList());
    }






}
