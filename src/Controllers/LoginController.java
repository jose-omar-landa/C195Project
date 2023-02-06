package Controllers;


import DBQueries.LoginQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    public TextField enteredUserName;
    public PasswordField enteredPassword;
    public Button loginButton;
    public Label titleDescription;
    public Label usernameLabel;
    public Label passwordLabel;

    public void onEnteredUserName(ActionEvent actionEvent) {
    }

    public void onEnteredPassword(ActionEvent actionEvent) {
    }

    private void loginSuccessful() {
        System.out.println("Welcome!");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Login Successful!");
        alert.setContentText("Welcome! Login Successful!");
        alert.showAndWait();
    }


    private void loginFailed() {
            System.out.println("Invalid Login!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Login");
            alert.setContentText("Incorrect Username or Password!");
            alert.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         resourceBundle = ResourceBundle.getBundle("UserLanguage/language", Locale.getDefault());

        if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")){
            titleDescription.setText(resourceBundle.getString("title"));
            usernameLabel.setText(resourceBundle.getString("username"));
            passwordLabel.setText(resourceBundle.getString("password"));
        }


    }

    public void onLoginButtonClicked(ActionEvent actionEvent) {
        try {
            boolean validLogin = LoginQuery.existingUserAndPassword((enteredUserName.getText()), enteredPassword.getText());
            if (validLogin) {
                loginSuccessful();
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
            else {
                loginFailed();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




