package patient;

public class Patient {
    private int patientId;
    private String name;
    private String lastname;
    private String phoneNumber;
    private String email;
    private int age;
    private String gender;
    private String address;

    // Constructors
    public Patient() {}

    public Patient(int patientId, String name,String lastname, String phoneNumber, String email, int age, String gender, String address) {
        this.patientId = patientId;
        this.name = name;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }

    // Getters and setters
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Override toString method for debugging purposes
    @Override
    public String toString() {
        return "Patient [patientId=" + patientId + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email
                + ", age=" + age + ", gender=" + gender + ", address=" + address + "]";
    }
}
