package patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;

public class PatientDAO {

    private Connection connection;

    public PatientDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public Patient getPatientById(int patientId) {
        String query = "SELECT * FROM patient WHERE patient_id = ?";
        Patient patient = null;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, patientId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                patient = new Patient();
                patient.setPatientId(resultSet.getInt("patient_id"));
                patient.setName(resultSet.getString("name"));
                patient.setPhoneNumber(resultSet.getString("phone_number"));
                patient.setEmail(resultSet.getString("email"));
                patient.setAge(resultSet.getInt("age"));
                patient.setGender(resultSet.getString("gender"));
                patient.setAddress(resultSet.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patient;
    }

    public void addPatient(Patient patient) {
        String query = "INSERT INTO patient (name, lastname, phone_number, email, age, gender, address) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, patient.getName());
            statement.setString(2, patient.getPhoneNumber());
            statement.setString(3, patient.getEmail());
            statement.setInt(4, patient.getAge());
            statement.setString(5, patient.getGender());
            statement.setString(6, patient.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePatient(Patient patient) {
        String query = "UPDATE patient SET name=?, phone_number=?, email=?, age=?, gender=?, address=? WHERE patient_id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, patient.getName());
            statement.setString(2, patient.getPhoneNumber());
            statement.setString(3, patient.getEmail());
            statement.setInt(4, patient.getAge());
            statement.setString(5, patient.getGender());
            statement.setString(6, patient.getAddress());
            statement.setInt(7, patient.getPatientId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePatient(int patientId) {
        String query = "DELETE FROM patient WHERE patient_id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, patientId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Patient> getAllPatients() {
        String query = "SELECT * FROM patient";
        List<Patient> patients = new ArrayList<Patient>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Patient patient = new Patient();
                patient.setPatientId(resultSet.getInt("patient_id"));
                patient.setName(resultSet.getString("name"));
                patient.setPhoneNumber(resultSet.getString("phone_number"));
                patient.setEmail(resultSet.getString("email"));
                patient.setAge(resultSet.getInt("age"));
                patient.setGender(resultSet.getString("gender"));
                patient.setAddress(resultSet.getString("address"));
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }
}
