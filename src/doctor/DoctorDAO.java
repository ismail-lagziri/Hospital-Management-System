package doctor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;

public class DoctorDAO {

    private Connection connection;

    public DoctorDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public Doctor getDoctorById(int doctorId) {
        String query = "SELECT * FROM doctor WHERE doctor_id = ?";
        Doctor doctor = null;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, doctorId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                doctor = new Doctor();
                doctor.setDoctorId(resultSet.getInt("doctor_id"));
                doctor.setName(resultSet.getString("name"));
                doctor.setLastName(resultSet.getString("last_name"));
                doctor.setSpecialization(resultSet.getString("specialization"));
                doctor.setPhoneNumber(resultSet.getString("phone_number"));
                doctor.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctor;
    }

    public void addDoctor(Doctor doctor) {
        String query = "INSERT INTO doctor (name, last_name, specialization, phone_number, email) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getLastName());
            statement.setString(3, doctor.getSpecialization());
            statement.setString(4, doctor.getPhoneNumber());
            statement.setString(5, doctor.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDoctor(Doctor doctor) {
        String query = "UPDATE doctor SET name=?, last_name=?, specialization=?, phone_number=?, email=? WHERE doctor_id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getLastName());
            statement.setString(3, doctor.getSpecialization());
            statement.setString(4, doctor.getPhoneNumber());
            statement.setString(5, doctor.getEmail());
            statement.setInt(6, doctor.getDoctorId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDoctor(int doctorId) {
        String query = "DELETE FROM doctor WHERE doctor_id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, doctorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Doctor> getAllDoctors() {
        String query = "SELECT * FROM doctor";
        List<Doctor> doctors = new ArrayList<Doctor>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorId(resultSet.getInt("doctor_id"));
                doctor.setName(resultSet.getString("name"));
                doctor.setLastName(resultSet.getString("last_name"));
                doctor.setSpecialization(resultSet.getString("specialization"));
                doctor.setPhoneNumber(resultSet.getString("phone_number"));
                doctor.setEmail(resultSet.getString("email"));
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctors;
    }
}
