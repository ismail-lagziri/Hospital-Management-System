package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cache.UserInformation;
import database.DatabaseConnection;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AllPayment {

    @FXML
    private TableColumn<Payment, String> admitDateColumn;

    @FXML
    private TableColumn<Payment, Float> amountColumn;

    @FXML
    private TableColumn<Payment, Boolean> isPaidColumn;

    @FXML
    private TableView<Payment> tableView;
    
    private Connection conn;
    
    
    @FXML
    private void initialize() {
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        admitDateColumn.setCellValueFactory(new PropertyValueFactory<>("admitDate"));
        isPaidColumn.setCellValueFactory(new PropertyValueFactory<>("paid"));
        
        List<Payment> paymentList = new ArrayList<>();
        try {
            conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT amount, admitDate, isPaid FROM payment where name = ? and lastname = ?");
            ps.setString(1, UserInformation.name);
            ps.setString(2, UserInformation.lastname);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                float amount = rs.getFloat("amount");
                String admitDate = rs.getString("admitDate");
                boolean paid = rs.getBoolean("isPaid");
                Payment payment = new Payment(amount, admitDate, paid);
                paymentList.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableView.getItems().addAll(paymentList);
    }
    
    
    public static class Payment{
        private float amount;
        private String admitDate;
        private boolean paid;
        
        public Payment(float amount, String admitDate, boolean paid) {
            this.amount = amount;
            this.admitDate = admitDate;
            this.paid = paid;
        }

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }

        public String getAdmitDate() {
            return admitDate;
        }

        public void setAdmitDate(String admitDate) {
            this.admitDate = admitDate;
        }

        public boolean isPaid() {
            return paid;
        }

        public void setPaid(boolean paid) {
            this.paid = paid;
        }
        
    }
    
    @FXML
    void check(ActionEvent event) {

    }

}
