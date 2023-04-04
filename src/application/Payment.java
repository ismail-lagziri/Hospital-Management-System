package application;

public class Payment {
	private int patientID;
	private String name;
	private int mobileNO;
	private String address;
	private String gender;
	private int age;
	private float amount;
	private String admitDate;
	private String doctorName;
	/**
	 * 
	 */
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param patientID
	 * @param name
	 * @param mobileNO
	 * @param address
	 * @param gender
	 * @param age
	 * @param amount
	 * @param admitDate
	 * @param doctorName
	 */
	public Payment(int patientID, String name, int mobileNO, String address, String gender, int age, float amount,
			String admitDate, String doctorName) {
		super();
		this.patientID = patientID;
		this.name = name;
		this.mobileNO = mobileNO;
		this.address = address;
		this.gender = gender;
		this.age = age;
		this.amount = amount;
		this.admitDate = admitDate;
		this.doctorName = doctorName;
	}
	public int getPatientID() {
		return patientID;
	}
	public String getName() {
		return name;
	}
	public int getMobileNO() {
		return mobileNO;
	}
	public String getAddress() {
		return address;
	}
	public String getGender() {
		return gender;
	}
	public int getAge() {
		return age;
	}
	public float getAmount() {
		return amount;
	}
	public String getAdmitDate() {
		return admitDate;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMobileNO(int mobileNO) {
		this.mobileNO = mobileNO;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public void setAdmitDate(String admitDate) {
		this.admitDate = admitDate;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	

}
