package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class CustomerDirectoryController {
    public TableView customerDirectoryTable;
    public TableColumn tableCustID;
    public TableColumn tableCustName;
    public TableColumn tableCustAddress;
    public TableColumn tablePostalCode;
    public TableColumn tablePhoneNumber;
    public TableColumn tableDivision;
    public TableColumn tableCountry;
    public Button addCustButton;
    public Button updateCustButton;
    public Button removeCustButton;
    public Button aptScheduleButton;

    public void onAddCustButtonClicked(ActionEvent actionEvent) {
    }

    public void onUpdateCustButtonClicked(ActionEvent actionEvent) {
    }

    public void onRemoveCustButtonClicked(ActionEvent actionEvent) {
    }

    public void onAptScheduleClick(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("../FXML_Files/AppointmentViewScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Customer Directory");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
