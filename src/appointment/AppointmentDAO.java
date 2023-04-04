package appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;

public class AppointmentDAO {

    private Connection connection;

    public AppointmentDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public void addAppointment(Appointment appointment) {
        String query = "INSERT INTO appointment (doctor_id, patient_id, date, time, duration, status, room_number, service_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, appointment.getDoctorId());
            statement.setInt(2, appointment.getPatientId());
            statement.setDate(3, appointment.getDate());
            statement.setTime(4, appointment.getTime());
            statement.setInt(5, appointment.getDuration());
            statement.setString(6, appointment.getStatus());
            statement.setInt(7, appointment.getRoomNumber());
            statement.setInt(8, appointment.getServiceId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAppointment(Appointment appointment) {
        String query = "UPDATE appointment SET doctor_id = ?, patient_id = ?, date = ?, time = ?, duration = ?, status = ?, room_number = ?, service_id = ? WHERE appointment_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, appointment.getDoctorId());
            statement.setInt(2, appointment.getPatientId());
            statement.setDate(3, appointment.getDate());
            statement.setTime(4, appointment.getTime());
            statement.setInt(5, appointment.getDuration());
            statement.setString(6, appointment.getStatus());
            statement.setInt(7, appointment.getRoomNumber());
            statement.setInt(8, appointment.getServiceId());
            statement.setInt(9, appointment.getAppointmentId ());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAppointment(int appointmentId) {
        String query = "DELETE FROM appointment WHERE appointment_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, appointmentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<Appointment>();
        String query = "SELECT * FROM appointment";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(resultSet.getInt("appointment_id"));

                int doctorId = resultSet.getInt("doctor_id");
                appointment.setDoctorId(doctorId);

                int patientId = resultSet.getInt("patient_id");
                appointment.setPatientId(patientId);

                appointment.setDate(resultSet.getDate("date"));
                appointment.setTime(resultSet.getTime("time"));
                appointment.setDuration(resultSet.getInt("duration"));
                appointment.setStatus(resultSet.getString("status"));
                appointment.setRoomNumber(resultSet.getInt("room_number"));

                int serviceId = resultSet.getInt("service_id");
                appointment.setServiceId(serviceId);

                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }
    
    
}
