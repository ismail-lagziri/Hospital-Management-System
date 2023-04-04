package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cache.UserInformation;
import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginController {
	private static int cnt = 0;

    @FXML
    private Button login;

    @FXML
    private TextField pass;

    @FXML
    private Button signup;

    @FXML
    private TextField username;

    @FXML
    void loginAction(ActionEvent event) throws IOException {
    	PreparedStatement st = null;
        ResultSet rs = null;
        
        PreparedStatement st1 = null;
        ResultSet rs1 = null;
        Connection con = DatabaseConnection.getConnection();
        try {
            st = con.prepareStatement("SELECT * FROM patient WHERE name=? AND lastname=?");
            st.setString(1,username.getText());
            st.setString(2,pass.getText());
            rs = st.executeQuery();
            
            st1 = con.prepareStatement("SELECT * FROM doctor WHERE name=? AND lastname=?");
            st1.setString(1,username.getText());
            st1.setString(2,pass.getText());
            rs1 = st1.executeQuery();
            if (rs.next()){
            	// Close current window
            	UserInformation.name = username.getText();
            	UserInformation.lastname = pass.getText();
            	UserInformation.id = rs.getInt("patient_id");
                Stage stage = (Stage) login.getScene().getWindow();
                stage.close();

                // Load the home fxml file and show the window
                Parent root = FXMLLoader.load(getClass().getResource("patient.fxml"));
                Stage homeStage = new Stage();
                homeStage.setScene(new Scene(root));
                homeStage.show();
            	cnt = 0;
            }
            else if (rs1.next()){
            	// Close current window
            	UserInformation.name = username.getText();
            	UserInformation.lastname = pass.getText();
            	UserInformation.id = rs1.getInt("doctor_id");
                Stage stage = (Stage) login.getScene().getWindow();
                stage.close();

                // Load the home fxml file and show the window
                Parent root = FXMLLoader.load(getClass().getResource("doctor.fxml"));
                Stage homeStage = new Stage();
                homeStage.setScene(new Scene(root));
                homeStage.show();
            	cnt = 0;
            }else {
            	cnt++;
              if (cnt == 3) {
                  Stage stage = (Stage) username.getScene().getWindow ();
                  stage.close();
              } else {
                  Alert alert = new Alert(AlertType.ERROR);
                  alert.setTitle("Erreur de connexion");
                  alert.setHeaderText(null);
                  alert.setContentText("Nom d'utilisateur ou mot de passe incorrect.");
                  alert.showAndWait();
              }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void signupAction(ActionEvent event) throws IOException {
    	Stage stage = (Stage) login.getScene().getWindow();
        stage.close();

        // Load the home fxml file and show the window
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Stage homeStage = new Stage();
        homeStage.setScene(new Scene(root));
        homeStage.show();
    }

}
