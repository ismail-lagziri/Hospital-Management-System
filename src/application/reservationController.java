package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class reservationController {
	
	@FXML
    private AnchorPane homeAnchorPane;
	
	@FXML
    void ClickShow(ActionEvent event) throws IOException{
		AnchorPane homePane = FXMLLoader.load(getClass().getResource("AllPayment.fxml"));
        homeAnchorPane.getChildren().setAll(homePane);
    }
	
    @FXML
    void insertPayment(ActionEvent event) throws IOException{
    	AnchorPane homePane = FXMLLoader.load(getClass().getResource("NewPayment.fxml"));
        homeAnchorPane.getChildren().setAll(homePane);
    }
    
    @FXML
    void updatePayment(ActionEvent event) throws IOException{
    	AnchorPane homePane = FXMLLoader.load(getClass().getResource("UpdatePayment.fxml"));
        homeAnchorPane.getChildren().setAll(homePane);
    }
    
    @FXML
    void deletePayment(ActionEvent event) throws IOException{
    	AnchorPane homePane = FXMLLoader.load(getClass().getResource("DeletePayment.fxml"));
        homeAnchorPane.getChildren().setAll(homePane);
    }
}
