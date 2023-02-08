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
    public Label countryLabel;
    private ResourceBundle resourceBundle;

    public void onEnteredUserName(ActionEvent actionEvent) {
    }

    public void onEnteredPassword(ActionEvent actionEvent) {
    }

    private void loginSuccessful() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(resourceBundle.getString("successfulLoginTitle"));
        alert.setContentText(resourceBundle.getString("successfulLoginContext"));
        alert.showAndWait();
    }


    private void loginFailed() {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         resourceBundle = ResourceBundle.getBundle("UserLanguage/language", Locale.getDefault());

        if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")){
            titleDescription.setText(resourceBundle.getString("title"));
            usernameLabel.setText(resourceBundle.getString("username"));
            passwordLabel.setText(resourceBundle.getString("password"));
            loginButton.setText(resourceBundle.getString("loginButton"));
            enteredUserName.setPromptText(resourceBundle.getString("usernamePrompt"));
            enteredPassword.setPromptText(resourceBundle.getString("passwordPrompt"));
            countryLabel.setText(resourceBundle.getString("country"));
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

                if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(resourceBundle.getString("failedLoginTitle"));
                    alert.setContentText(resourceBundle.getString("failedLoginContext"));
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




