package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cache.UserInformation;
import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class NewPayment {

    @FXML
    private TextField FName;

    @FXML
    private TextField LName;
    
    @FXML
    private DatePicker admitDate;
    
    public void initialize() {
    	FName.setText(UserInformation.name);
    	LName.setText(UserInformation.lastname);
    }

    @FXML
    void newpayment(ActionEvent event) {
        // Get the first and last name entered by the user
        String firstName = FName.getText();
        String lastName = LName.getText();
        
        Connection connection = DatabaseConnection.getConnection();
        // Check if the payment for this patient has already been made
        boolean isAlreadyPaid = false;  // assume payment has not been made yet
        try {
            // Replace "your_username" and "your_password" with your database credentials
           

            String sql = "SELECT isPaid FROM Payment WHERE name=? AND lastname=? and admitDate = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setDate(3, java.sql.Date.valueOf(admitDate.getValue()));
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                isAlreadyPaid = result.getBoolean("isPaid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // If payment has not been made, update the database
        if (!isAlreadyPaid) {
            try {

                String sql = "UPDATE Payment SET isPaid=1 WHERE name=? AND lastname=?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Show a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Payment Successful");
            alert.setHeaderText(null);
            alert.setContentText("Payment has been successfully made for " + firstName + " " + lastName + ".");
            alert.showAndWait();
        } else {
            // Show a warning message if payment has already been made
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Payment Already Made");
            alert.setHeaderText(null);
            alert.setContentText("Payment has already been made for " + firstName + " " + lastName + ".");
            alert.showAndWait();
        }
    }


}
