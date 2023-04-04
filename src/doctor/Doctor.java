package doctor;
public class Doctor {
    private int doctorId;
    private String name;
    private String lastName;
    private String specialization;
    private String phoneNumber;
    private String email;
    
    // Constructor
    public Doctor() {}
    
    public Doctor(int doctorId, String name, String lastName, String specialization, String phoneNumber, String email) {
        this.doctorId = doctorId;
        this.name = name;
        this.lastName = lastName;
        this.specialization = specialization;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    
    // Getters and setters
    public int getDoctorId() {
        return doctorId;
    }
    
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
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
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    // Override toString method for debugging purposes
    @Override
    public String toString() {
        return "Doctor [doctorId=" + doctorId + ", name=" + name + ", lastName=" + lastName
                + ", specialization=" + specialization + ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
    }
}
