package appointment;

import java.sql.Date;
import java.sql.Time;

public class Appointment {
    private int appointmentId;
    private int doctorId;
    private int patientId;
    private Date date;
    private Time time;
    private int duration;
    private String status;
    private int roomNumber;
    private int serviceId;

    public Appointment() {};
    
    public Appointment(int appointmentId, int doctorId, int patientId, Date date, Time time, int duration,
                       String status, int roomNumber, int serviceId) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.status = status;
        this.roomNumber = roomNumber;
        this.serviceId = serviceId;
    }

    // getters and setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    // toString method
    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", doctorId=" + doctorId +
                ", patientId=" + patientId +
                ", date=" + date +
                ", time=" + time +
                ", duration=" + duration +
                ", status='" + status + '\'' +
                ", roomNumber=" + roomNumber +
                ", serviceId=" + serviceId +
                '}';
    }
}
