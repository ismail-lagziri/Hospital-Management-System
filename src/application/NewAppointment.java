package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cache.UserInformation;
import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class NewAppointment {

    @FXML
    private DatePicker appdate;

    @FXML
    private TextField first;

    @FXML
    private TextField issue;

    @FXML
    private TextField last;

    @FXML
    private Button reserve;

    private int id = UserInformation.id;
    private Connection conn;
    
    public void initialize() {
    	first.setText(UserInformation.name);
    	last.setText(UserInformation.lastname);
    }
    
    @FXML
    void reserveAction(ActionEvent event) {
        if (appdate.getValue() == null) {
            showAlert("Please select a date.");
            return;
        }
        if (issue.getText().isEmpty()) {
            showAlert("Please enter an issue.");
            return;
        }
        try {
        	conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO Appointment (patient_id, date, issue, status) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.setDate(2, java.sql.Date.valueOf(appdate.getValue()));
            pstmt.setString(3, issue.getText());
            pstmt.setString(4, "Scheduled");

            pstmt.executeUpdate();
            
            String sql2 = "INSERT INTO payment (patientID, name, lastname, amount, admitDate, isPaid) VALUES (?, ?, ?, 150, ?, 0)";
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);

            pstmt2.setInt(1, id);
            pstmt2.setString(2, UserInformation.name);
            pstmt2.setString(3, UserInformation.lastname);
            pstmt2.setDate(4, java.sql.Date.valueOf(appdate.getValue()));
            

            pstmt2.executeUpdate();


            showAlert("Appointment reserved successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    

}
