package Controllers;



import DBConnection.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**This class creates an application that displays appointments, a customer directory, and allows the user
 * to add, update, or delete appointments and to add, update, or delete customer records.
 *
 * Lambda expressions are located in the following controllers:
 * 1. AppointmentScreenController Lines 168-200
 * 2. AddAppointmentController Lines 404-420
 * 3. UpdateAppointmentController Lines 396-407 */

public class Main extends Application {

    /**The start method loads the LoginFXML screen on launch of the application.*/
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Files/LoginFXML.fxml"));
        primaryStage.setTitle("Employee Login");
        primaryStage.setScene(new Scene(root, 600, 450));
        primaryStage.show();
    }

    /**The main method of the application. This method opens and closes the database connection for the
     * application. */
    public static void main(String[] args) {
        JDBC.openConnection();

        launch(args);

        JDBC.closeConnection();
    }

}
