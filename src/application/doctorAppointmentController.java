package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class doctorAppointmentController {
    @FXML
    private TableView<Appointment> tableView;
    @FXML
    private TableColumn<Appointment, String> firstNameColumn;
    @FXML
    private TableColumn<Appointment, String> lastNameColumn;
    @FXML
    private TableColumn<Appointment, String> issueColumn;
    @FXML
    private TableColumn<Appointment, String> dateColumn;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    
    private Connection conn;
    
    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        issueColumn.setCellValueFactory(new PropertyValueFactory<>("issue"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        List<Appointment> appointmentList = new ArrayList<>();
        try {
            conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT p.name, p.lastname, a.issue, a.date FROM Patient p JOIN Appointment a ON p.patient_id = a.patient_id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String firstName = rs.getString("name");
                String lastName = rs.getString("lastname");
                String issue = rs.getString("issue");
                String date = rs.getString("date");
                Appointment appointment = new Appointment(firstName, lastName, issue, date);
                appointmentList.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableView.getItems().addAll(appointmentList);
    }
    
    @FXML
    private void search() {
        String search = searchField.getText();
        if (!search.isEmpty()) {
            List<Appointment> appointmentList = new ArrayList<>();
            try {
                if (conn == null) {
                    conn = DatabaseConnection.getConnection();
                }
                PreparedStatement ps = conn.prepareStatement("SELECT p.name, p.lastname, a.issue, a.date FROM Patient p JOIN Appointment a ON p.patient_id = a.patient_id WHERE a.issue LIKE ?");
                ps.setString(1, "%" + search + "%");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String firstName = rs.getString("name");
                    String lastName = rs.getString("lastname");
                    String issue = rs.getString("issue");
                    String date = rs.getString("date");
                    Appointment appointment = new Appointment(firstName, lastName, issue, date);
                    appointmentList.add(appointment);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            tableView.getItems().clear();
            tableView.getItems().addAll(appointmentList);
        }
    }

    
    public static class Appointment {
        private String firstName;
        private String lastName;
        private String issue;
        private String date;
        
        public Appointment(String firstName, String lastName, String issue, String date) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.issue = issue;
            this.date = date;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getIssue() {
            return issue;
        }

        public void setIssue(String issue) {
            this.issue = issue;
        }

        public String getDate() {
        	return date;
        }
        
    }
}
