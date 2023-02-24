import java.util.Date;
import java.text.SimpleDateFormat;


public class Consultation extends Person {//this class initialises all the required information for consultation

    public static Date date; //to store date
    private  Doctor doctor;//to store doctor name
    private Patient patient;// to store patient name
    private int cost;
    private String notes;


    public Consultation(String name, String surname, String dob, String mobileNumber, Date date, Doctor doctor, Patient patient, int cost, String notes) {//creates a constructor
        super(name, surname, dob, mobileNumber);
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
        this.cost = cost;
        this.notes = notes;
    }

    //gettes and setters method
    public void setDate(Date date){
        this.date = date;
    }

    public Date getDate(){
        return date;
    }

    public void setDoctor(Doctor doctor){
        this.doctor = doctor;
    }

    public Doctor getDoctor(){
        return doctor;
    }

    public void setPatient(Patient patient){
        this.patient = patient;
    }

    public Patient getPatient(){
        return patient;
    }

    public void setCost(int cost){
        this.cost = cost;
    }

    public int getCost(){
        return cost;
    }

    public void setNotes(String Notes){
        this.notes = notes;
    }

    public String getNotes(){
        return notes;
    }
}
