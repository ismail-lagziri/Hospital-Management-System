package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DeletePayment {

    @FXML
    private Button ButtonExit;

    @FXML
    private TextField address3;

    @FXML
    private TextField admitDate3;

    @FXML
    private TextField age3;

    @FXML
    private TextField amount3;

    @FXML
    private TextField doctorName3;

    @FXML
    private TextField gender3;

    @FXML
    private TextField mobileNO3;

    @FXML
    private TextField name3;

    @FXML
    private TextField patientID3;

    @FXML
    void ClickExit(ActionEvent event) throws IOException {
    	Pane root = (Pane) FXMLLoader.load(getClass().getResource("Sample.fxml"));
		Stage window = (Stage) ButtonExit.getScene().getWindow();
		window.setScene(new Scene(root, 600, 400));

    }
    @FXML
    void deletepayment(ActionEvent event) {
    	Connection con = DatabaseConnection.getConnection();
		try {
			PreparedStatement stmtt = con.prepareStatement("UPDATE payment SET amount = 0  WHERE paymentID = ?");
			stmtt.setInt(1, Integer.parseInt(patientID3.getText()));
			int rs= stmtt.executeUpdate();
			if (rs!=0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Delete Succeeded", ButtonType.OK);
                alert.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING,"failed Delete", ButtonType.OK);
                alert.show();
            }
		} catch (SQLException e) {
			System.out.println("Erreur lors de la suppression d'un payment : " + e.getMessage());
		}
		
	}

}
