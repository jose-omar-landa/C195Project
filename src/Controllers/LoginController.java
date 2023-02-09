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
import java.time.format.DateTimeFormatter;
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

    public void onEnteredUserName(ActionEvent actionEvent) {
    }

    public void onEnteredPassword(ActionEvent actionEvent) {
    }

    private void loginSuccessful(String user) throws IOException {
        FileWriter fileWriter = new FileWriter("login_activity.txt", true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime currentTime = LocalDateTime.now();
        String loginAttempt = dateTimeFormatter.format(currentTime);

        printWriter.println(user + " successfully logged in at " + loginAttempt);
        printWriter.close();

    }


    private void loginFailed(String user) throws IOException {
        FileWriter fileWriter = new FileWriter("login_activity.txt", true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime currentTime = LocalDateTime.now();
        String loginAttempt = dateTimeFormatter.format(currentTime);

        printWriter.println(user + " unsuccessfully attempted to log in at " + loginAttempt);
        printWriter.close();
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
                    pendingAppointmentInFifteenMinutes();

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




