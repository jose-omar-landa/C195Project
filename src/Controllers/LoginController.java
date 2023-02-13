package Controllers;


import DBQueries.AppointmentQuery;
import DBQueries.LoginQuery;
import Objects.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;


/** This class allows a user to log in to the scheduling application if they
 * enter an existing username and password. Both successful and unsuccessful
 * login attempts will be recorded in a text file. The user's region will also
 * be assessed in order to change the language used in the login screen. Currently
 * there is only support for English and French. */
public class LoginController implements Initializable {

    public TextField enteredUserName;
    public PasswordField enteredPassword;
    public Button loginButton;
    public Label titleDescription;
    public Label usernameLabel;
    public Label passwordLabel;
    public Label countryLabel;
    public Label timeZoneLabel;

    public void onEnteredUserName(ActionEvent actionEvent) {
    }

    public void onEnteredPassword(ActionEvent actionEvent) {
    }

    /** This method will log a successful login attempt in a text file named login_activity.txt.
     * If the file does not already exist, the file will be created. If it does exist, the file will
     * be appended with the new login information. The username and timestamp of the login attempt
     * will be recorded.
     * @param user this selected the username that is entered in the textfield. */
    private void loginSuccessful(String user) throws IOException {
        FileWriter fileWriter = new FileWriter("login_activity.txt", true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime currentTime = LocalDateTime.now();
        String loginAttempt = dateTimeFormatter.format(currentTime);
        printWriter.println(user + " successfully logged in at " + loginAttempt);
        printWriter.close();
    }

    /** This method will log an unsuccessful login attempt in a text file named login_activity.txt.
     * If the file does not already exist, the file will be created. If it does exist, the file will
     * be appended with the new login information. The username and timestamp of the login attempt
     * will be recorded.
     * @param user this selected the username that is entered in the textfield. */
    private void loginFailed(String user) throws IOException {
        FileWriter fileWriter = new FileWriter("login_activity.txt", true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime currentTime = LocalDateTime.now();
        String loginAttempt = dateTimeFormatter.format(currentTime);
        printWriter.println(user + " unsuccessfully attempted to log in at " + loginAttempt);
        printWriter.close();
    }

    /** This method initializes the Login Controller. The user's region will be assessed in order to
     * provide the correct language support. Currently, there is only suppor for English and French. */
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
            timeZoneLabel.setText((resourceBundle.getString("timeZoneLabel") + ZoneId.of(TimeZone.getDefault().getID())));
        }
    }

    /** This method checks to see if any appointments will be starting within fifteen minutes of
     * the user's login. If yes, a warning will notify the user of the appointment ID and start time.
     * If there are no appointments beginning within fifteen minutes of the user's login, the user
     * will be notified as well through a warning pop-up. */
    public void pendingAppointmentInFifteenMinutes() throws SQLException {
        ObservableList<Appointments> appointments = AppointmentQuery.allAppointmentsList();
        ObservableList<Appointments> pendingAppointments = FXCollections.observableArrayList();

        if (appointments != null) {
            for (Appointments appointmentInFifteenMinutes : appointments) {
                LocalDateTime currentTime = LocalDateTime.now();
                LocalDateTime timeInFifteenMinutes = currentTime.plusMinutes(15);

                if (appointmentInFifteenMinutes.getAptStart().isAfter(currentTime) && appointmentInFifteenMinutes.getAptStart().isBefore(timeInFifteenMinutes)) {
                    pendingAppointments.add(appointmentInFifteenMinutes);
                    if (Locale.getDefault().getLanguage().equals("fr")) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Rendez-vous à partir de bientôt!");
                        alert.setContentText("Un rendez-vous est prévu pour commencer dans les 15 minutes! ID du rendez-vous: " + appointmentInFifteenMinutes.getAptID() + "     Heure de début:" + appointmentInFifteenMinutes.getAptStart());
                        alert.showAndWait();
                        return;
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Appointment Starting Soon!");
                        alert.setContentText("An appointment is scheduled to begin within 15 minutes! Appointment ID: " + appointmentInFifteenMinutes.getAptID() + "     Start Time: " + appointmentInFifteenMinutes.getAptStart());
                        alert.showAndWait();
                        return;
                    }
                }
            }
        }

        if (pendingAppointments.size() < 1) {
            if (Locale.getDefault().getLanguage().equals("fr")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pas de rendez-vous dans les 15 minutes!");
                alert.setContentText("Il n’y a pas de rendez-vous prévus dans les 15 prochaines minutes!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No Appointments Within 15 Minutes!");
                alert.setContentText("There are no appointments scheduled within the next 15 minutes!");
                alert.showAndWait();
            }
        }
    }

    /** This method allows functionality to the Login button on the screen. When the button is
     * clicked, the entered username and password is verified against the existing database users. If the
     * username and password match an existing user, the login is successful. If the entered username or
     * password do not match, an error will generate notifying the user that the login was unsuccessful. */
    public void onLoginButtonClicked(ActionEvent actionEvent) {
        try {
            String user = enteredUserName.getText();

            if (enteredUserName.getText().isEmpty()) {
                if (Locale.getDefault().getLanguage().equals("fr")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Nom d’utilisateur manquant!");
                    alert.setContentText("Un nom d’utilisateur valide doit être entré!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Missing Username!");
                    alert.setContentText("A valid username must be entered!");
                    alert.showAndWait();
                }

            } else if (enteredPassword.getText().isEmpty()) {
                if (Locale.getDefault().getLanguage().equals("fr")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Mot de passe manquant!");
                    alert.setContentText("Un mot de passe valide doit être saisi!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Missing Password!");
                    alert.setContentText("A valid password must be entered!");
                    alert.showAndWait();
                }

            } else {
                boolean validLogin = LoginQuery.existingUserAndPassword((enteredUserName.getText()), enteredPassword.getText());
                if (validLogin) {
                    loginSuccessful(user);

                    if (Locale.getDefault().getLanguage().equals("fr")) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Connexion réussie!");
                        alert.setContentText("Tentative de connexion réussie ! Bienvenue!");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Login Successful!");
                        alert.setContentText("Login attempt successful! Welcome!");
                        alert.showAndWait();
                    }
                    pendingAppointmentInFifteenMinutes();
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
                    loginFailed(user);

                    if (Locale.getDefault().getLanguage().equals("fr")) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Échec de la connexion!");
                        alert.setContentText("Échec de la connexion! Nom d’utilisateur ou mot de passe incorrect!");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Login Failed!");
                        alert.setContentText("Login attempt failed! Please try again.");
                        alert.showAndWait();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




