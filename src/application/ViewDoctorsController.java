package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewDoctorsController {
	@FXML
    private TableView<Doctor_lite> tableView;
    @FXML
    private TableColumn<Doctor_lite, String> firstNameColumn;
    @FXML
    private TableColumn<Doctor_lite, String> lastNameColumn;
    @FXML
    private TableColumn<Doctor_lite, String> specializationColumn;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    
    private Connection conn;
    
    
    @FXML
    private void initialize() {
    	
//        new AnimationTimer() {
//            private long lastUpdate = 0;
//
//            @Override
//            public void handle(long now) {
//                if (now - lastUpdate >= 1_000_000_000L) {
//                    // Query the database and update the table
//                    List<Doctor_lite> doctorList = new ArrayList<>();
//                    try {
//                        PreparedStatement ps = conn.prepareStatement("SELECT name, lastname, specialization FROM doctor");
//                        ResultSet rs = ps.executeQuery();
//                        while (rs.next()) {
//                            String firstName = rs.getString("name");
//                            String lastName = rs.getString("lastname");
//                            String specialization = rs.getString("specialization");
//                            Doctor_lite doctor = new Doctor_lite(firstName, lastName, specialization);
//                            doctorList.add(doctor);
//                        }
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                    tableView.getItems().setAll(doctorList);
//
//                    // Update the last update time
//                    lastUpdate = now;
//                }
//            }
//        }.start();
//        
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        specializationColumn.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        
        List<Doctor_lite> Doctor_liteList = new ArrayList<>();
        try {
            conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT name, lastname, specialization FROM doctor");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String firstName = rs.getString("name");
                String lastName = rs.getString("lastname");
                String specialization = rs.getString("specialization");
                Doctor_lite Doctor_lite = new Doctor_lite(firstName, lastName, specialization);
                Doctor_liteList.add(Doctor_lite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableView.getItems().addAll(Doctor_liteList);
    }
    
    @FXML
    private void search() {
        String search = searchField.getText();
        if (!search.isEmpty()) {
            List<Doctor_lite> Doctor_liteList = new ArrayList<>();
            try {
                PreparedStatement ps = conn.prepareStatement("SELECT name, lastname, specialization FROM doctor WHERE specialization LIKE ?");
                ps.setString(1, "%" + search + "%");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String firstName = rs.getString("name");
                    String lastName = rs.getString("lastname");
                    String specialization = rs.getString("specialization");
                    Doctor_lite Doctor_lite = new Doctor_lite(firstName, lastName, specialization);
                    Doctor_liteList.add(Doctor_lite);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            tableView.getItems().clear();
            tableView.getItems().addAll(Doctor_liteList);
        }
    }
    
    public static class Doctor_lite{
        private String firstName;
        private String lastName;
        private String specialization;
        
        public Doctor_lite(String firstName, String lastName, String specialization) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.specialization = specialization;
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

        public String getSpecialization() {
            return specialization;
        }

        public void setSpecialization(String specialization) {
            this.specialization = specialization;
        }
        
    }
}
