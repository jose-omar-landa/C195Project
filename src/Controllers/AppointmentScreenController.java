package Controllers;

import DBQueries.AppointmentQuery;
import Objects.Appointments;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentScreenController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void showAppointments(ActionEvent actionEvent) {
        ObservableList<Appointments> aptList = AppointmentQuery.allAppointmentsList();
        System.out.println(AppointmentQuery.allAppointmentsList());
    }






}
