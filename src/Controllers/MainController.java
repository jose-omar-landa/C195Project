package Controllers;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private static boolean firstTime = true;


    public void showAppointments() {
        if(!firstTime) {
            return;    }
        firstTime = false;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
