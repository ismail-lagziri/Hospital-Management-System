package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class UpdatePayment {

    @FXML
    private TableColumn<?, ?> AmountColumn;

    @FXML
    private TableColumn<?, ?> firstNameColumn;

    @FXML
    private TableColumn<?, ?> lastNameColumn;

    @FXML
    private TextField newAmount;

    @FXML
    private TableView<?> tableView;

    @FXML
    void updateAmount(ActionEvent event) {

    }

}
