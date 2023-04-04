package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class appointmentController {
	
	@FXML
    private AnchorPane homeAnchorPane;
	
	
    @FXML
    void insertappointment(ActionEvent event) throws IOException{
    	AnchorPane homePane = FXMLLoader.load(getClass().getResource("Newappointment.fxml"));
        homeAnchorPane.getChildren().setAll(homePane);
    }
    
    @FXML
    void updateappointment(ActionEvent event) throws IOException{
    	AnchorPane homePane = FXMLLoader.load(getClass().getResource("Updateappointment.fxml"));
        homeAnchorPane.getChildren().setAll(homePane);
    }
    
    @FXML
    void deleteAppointement(ActionEvent event) throws IOException{
    	AnchorPane homePane = FXMLLoader.load(getClass().getResource("DeleteAppointment.fxml"));
        homeAnchorPane.getChildren().setAll(homePane);
    }
}
