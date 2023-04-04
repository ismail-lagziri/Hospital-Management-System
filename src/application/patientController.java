package application;

import java.io.IOException;

import cache.UserInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class patientController {
	
	@FXML
    private Button Exit;
	
	@FXML
    public static Label nameZone;
	
	

    @FXML
    private AnchorPane homeAnchorPane;

    @FXML
    void loadHome(ActionEvent event) throws IOException {
    	AnchorPane homePane = FXMLLoader.load(getClass().getResource("home.fxml"));
        homeAnchorPane.getChildren().setAll(homePane);
    }
    
    @FXML
    void loadDoctors(ActionEvent event) throws IOException {
    	AnchorPane homePane = FXMLLoader.load(getClass().getResource("viewDoctors.fxml"));
        homeAnchorPane.getChildren().setAll(homePane);
    }
    
    @FXML
    void loadHelp(ActionEvent event) throws IOException {
    	AnchorPane homePane = FXMLLoader.load(getClass().getResource("help.fxml"));
        homeAnchorPane.getChildren().setAll(homePane);
    }
    
    @FXML
    void loadReservation(ActionEvent event) throws IOException {
    	AnchorPane homePane = FXMLLoader.load(getClass().getResource("reservation.fxml"));
        homeAnchorPane.getChildren().setAll(homePane);
    }
    
    @FXML
    void loadAppointment(ActionEvent event) throws IOException {
    	AnchorPane homePane = FXMLLoader.load(getClass().getResource("appointment.fxml"));
        homeAnchorPane.getChildren().setAll(homePane);
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
    	Pane root = (Pane) FXMLLoader.load(getClass().getResource("login.fxml"));
		Stage window = (Stage) Exit.getScene().getWindow();
		window.setScene(new Scene(root, 600, 400));
    }

}
