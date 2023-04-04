package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import cache.UserInformation;
import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class UpdateAppointment {

    @FXML
    private TableColumn<Appointment, LocalDate> dateColumn;

    @FXML
    private DatePicker dateField;

    @FXML
    private TableColumn<Appointment, String> issueColumn;

    @FXML
    private TextField issueField;

    @FXML
    private TableColumn<Appointment, String> statusColumn;

    @FXML
    private TableView<Appointment> tableView;
    
    Connection con = DatabaseConnection.getConnection();
    
    public void initialize() {

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        issueColumn.setCellValueFactory(new PropertyValueFactory<>("issue"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
            
        List<Appointment> appointmentList = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT date, issue, status FROM Appointment where patient_id = ?");
            ps.setInt(1, UserInformation.id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LocalDate date = rs.getDate("date").toLocalDate();
                String issue = rs.getString("issue");
                String status = rs.getString("status");
                Appointment appointment = new Appointment(date, issue, status);
                appointmentList.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableView.getItems().addAll(appointmentList);
    }


    @FXML
    void updateAppointment(ActionEvent event) {
        Appointment selectedAppointment = tableView.getSelectionModel().getSelectedItem();
        if (selectedAppointment != null) {
            try  {
            	PreparedStatement ps = con.prepareStatement("UPDATE Appointment SET date = ?, issue = ? WHERE patient_id = ? AND date = ?");
                ps.setDate(1, java.sql.Date.valueOf(dateField.getValue()));
                ps.setString(2, issueField.getText());
                ps.setInt(3, UserInformation.id);
                ps.setDate(4, java.sql.Date.valueOf(tableView.getSelectionModel().getSelectedItem().getDate()));
                ps.executeUpdate();
                
                PreparedStatement ps2 = con.prepareStatement("UPDATE payment SET admitDate = ? WHERE patientID = ? AND admitDate = ?");
                ps2.setDate(1, java.sql.Date.valueOf(dateField.getValue()));
                ps2.setInt(2, UserInformation.id);
                ps2.setDate(3, java.sql.Date.valueOf(tableView.getSelectionModel().getSelectedItem().getDate()));
                ps2.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            selectedAppointment.setIssue(issueField.getText());
            selectedAppointment.setDate(dateField.getValue());
            tableView.refresh();
        }
    }



    public class Appointment {
        private LocalDate date;
        private String issue;
        private String status;

        public Appointment(LocalDate date, String issue, String status) {
            this.date = date;
            this.issue = issue;
            this.status = status;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public String getIssue() {
            return issue;
        }

        public void setIssue(String issue) {
            this.issue = issue;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
