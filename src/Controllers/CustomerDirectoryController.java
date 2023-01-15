package Controllers;

import DBQueries.AppointmentQuery;
import DBQueries.CustomerQuery;
import Objects.Countries;
import Objects.Customers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.print.attribute.standard.ColorSupported;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerDirectoryController implements Initializable {
    public TableView<Customers> customerDirectoryTable;
    public TableColumn<Customers, Integer> tableCustID;
    public TableColumn<Customers, String> tableCustName;
    public TableColumn<Customers, String> tableCustAddress;
    public TableColumn<Customers, String> tablePostalCode;
    public TableColumn<Customers, String> tablePhoneNumber;
    public TableColumn<Customers, Integer> tableDivision;
    public TableColumn<Customers, String> tableCountry;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            customerDirectoryTable.setItems(CustomerQuery.allCustomersList());
            tableCustID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            tableCustName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            tableCustAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
            tablePostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
            tablePhoneNumber.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
            tableDivision.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
            tableCountry.setCellValueFactory(new PropertyValueFactory<>("country"));

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
