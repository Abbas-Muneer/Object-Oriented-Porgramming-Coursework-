public class Doctor extends Person { //this has all the information about the doctors
    private String licenseNumber;
    private String specialisation;

    //creates a constructor
    public Doctor(String name, String surname, String dob, String mobileNumber, String licenseNumber, String specialisation){
        super(name,surname,dob,mobileNumber);
        this.licenseNumber = licenseNumber;
        this.specialisation = specialisation;

    }

    //getters and setters methods
    public void setLicenseNumber(String licenseNumber){
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber(){
        return licenseNumber;
    }

    public void setSpecialisation(String specialisation){
        this.specialisation = specialisation;
    }

    public String getSpecialisation(){
        return specialisation;
    }


    public String toString(){
        return super.toString() +
                "License Number= " + licenseNumber +
                "Specialisation= "  + specialisation;

    }
}
