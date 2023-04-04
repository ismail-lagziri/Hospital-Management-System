package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import database.DatabaseConnection;

public class signupController implements Initializable{

    @FXML
    private ChoiceBox<String> Gender= new ChoiceBox<String>();

    @FXML
    private TextField address;

    @FXML
    private TextField age;

    @FXML
    private TextField email;

    @FXML
    private TextField first;

    @FXML
    private TextField last;

    @FXML
    private TextField pass;

    @FXML
    private TextField phone;
    
    private String[] test = {"Male", "Female"};

    @FXML
    private Button signup;
    

    @FXML
    void addPatient(ActionEvent event) throws IOException {
        String firstName = first.getText();
        String lastName = last.getText();
        String phoneNumber = phone.getText();
        String emailAddr = email.getText();
        int patientAge = Integer.parseInt(age.getText());
        String patientGender = Gender.getValue();
        String patientAddress = address.getText();

        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();

            String insertQuery = "INSERT INTO Patient (name, lastname, phone_number, email, age, gender, address) " +
                    "VALUES ('" + firstName + "', '" + lastName + "', '" + phoneNumber + "', '" + emailAddr + "', " +
                    patientAge + ", '" + patientGender + "', '" + patientAddress + "')";
            stmt.executeUpdate(insertQuery);
            
            Stage stage = (Stage) signup.getScene().getWindow();
            stage.close();

            // Load the home fxml file and show the window
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage homeStage = new Stage();
            homeStage.setScene(new Scene(root));
            homeStage.show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Gender.getItems().addAll(test);
	}

    
}
