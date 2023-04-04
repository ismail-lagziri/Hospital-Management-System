package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cache.UserInformation;
import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class DeleteAppointment {

    @FXML
    private TextField FName;

    @FXML
    private TextField LName;
    
    @FXML
    private DatePicker dateappointment;
    
    public void initialize() {
    	FName.setText(UserInformation.name);
    	LName.setText(UserInformation.lastname);
    }

    @FXML
    void deleteAppointment(ActionEvent event) {

        // check if the appointment is already cancelled
        String query = "SELECT status FROM Appointment WHERE patient_id = ? AND status = 'Cancelled' and date = ?";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(query)) {
            statement.setInt(1, UserInformation.id);
            statement.setDate(2, java.sql.Date.valueOf(dateappointment.getValue()));
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                // appointment is already cancelled, display a prompt message
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Appointment Already Cancelled");
                alert.setHeaderText(null);
                alert.setContentText("This appointment has already been cancelled.");
                alert.showAndWait();
            } else {
                // update the appointment status to cancelled
                query = "UPDATE Appointment SET status = 'Cancelled' WHERE patient_id = ? and date = ?";
                try (PreparedStatement updateStatement = DatabaseConnection.getConnection().prepareStatement(query)) {
                    updateStatement.setInt(1, UserInformation.id);
                    updateStatement.setDate(2, java.sql.Date.valueOf(dateappointment.getValue()));
                    int updatedRows = updateStatement.executeUpdate();
                    if (updatedRows == 1) {
                        // appointment cancelled successfully, display a prompt message
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Appointment Cancelled");
                        alert.setHeaderText(null);
                        alert.setContentText("This appointment has been cancelled.");
                        alert.showAndWait();
                    } else {
                        // no appointment found for the given patient ID, display an error message
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("An error occurred while cancelling the appointment.");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
