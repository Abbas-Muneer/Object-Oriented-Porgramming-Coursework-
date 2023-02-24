import javax.print.Doc;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*;

import java.util.Collections;

public  class WestminsterSkinConsultationManager implements SkinConsultationManager {

    protected static ArrayList<Doctor> doctorList = new ArrayList<Doctor>();//creating a list to store the doctor details
    private Scanner input = new Scanner(System.in);//intialising a scanner to get input
    private String option;//creating a variable to get user option

    @Override
    public void addDoctor() {// this method is to add a new doctor to the system
        if (doctorList.size() <= 9) {//checking whether the system already has 10 doctors
            System.out.println("Enter the doctor's name: ");//asking the name of the doctor
            String doctorName = input.next();//storing the input in a variable
            System.out.println("Enter the doctor's surname: ");//asking the surname of the doctor
            String doctorSurname = input.next();//storing the surname ina variable
            System.out.println("Enter the doctor's D.O.B: ");//asking the date of birth of the doctor
            String doctorDob = input.next();//storing the DOB in a variable
            System.out.println("Enter the doctor's mobile number: ");//asking the mobile number of the doctor
            String mobileNumber = input.next();//storing the number in a variable
            System.out.println("Enter the doctor's license number: eg-'name12345'");//asking the license number of the doctor
            String licenseNumber = input.next();//storing the license number in a variable
            System.out.println("Enter the doctor's specialisation: ");//asking the specialisation of the doctor
            String doctorSpecialisation = input.next();

            //creating a constructor from the doctor class and adding the varibales to them
            Doctor doctor = new Doctor(doctorName, doctorSurname, doctorDob, mobileNumber, licenseNumber, doctorSpecialisation);
            doctorList.add(doctor); //adding the new constructor wiht the details in it to the doctor list
            System.out.println("The doctor has been successfully added in the system:))"); //printing successful message
        } else {
            System.out.println("There are already 10 doctors in the system");//printing an error message if the system has 10 doctors already
            System.out.println("If you want to add more doctors, delete a doctor from the system");
            System.out.println(" ");
        }


    }

    @Override
    public void deleteDoctor() {//this method is to delete a doctor from the system
        System.out.println("Enter the license number of the doctor to delete from the system");//asking the user to enter the license of the doctor to check
        String selectedLicense = input.next(); //storing the user input ina variable
        //print list
        for (Doctor doctor : doctorList) {//traversing trough the doctor list to check whether the license number exists
            if (doctor.getLicenseNumber().toLowerCase().equals(selectedLicense.toLowerCase())) {//checking whether it exists
                System.out.println("Dr: " + doctor.getName() + " Is it this doctor that you want to delete?");//confirming whether it's the correct doctor by asking the doctor's name
                System.out.println("Enter yes/no");
                option = input.next();

                if (option.toLowerCase().equals("yes")) {//if the user confirms yes it will delete the doctor
                    doctorList.remove(doctor);//removes the doctor from the list

                    System.out.println("---------------------------------------------------------------------------------");
                    System.out.println(doctor.getName() + " was successfully deleted");//printing successful message
                    //sortDrivers();
                    System.out.println("The number of doctors right now in the system are :" + doctorList.size());//printing how many doctors remaining in the system
                    return;
                } else {
                    return;
                }
            }
        }
        System.out.println(selectedLicense + " is not in the system ");// if the license number is not there in the system, it will print this message
    }



    @Override
    public void printDoctor() {//this method is to print the unsorted list of doctor's and their list
        System.out.println("This is the unsorted list of Doctors");
        for (Doctor doctor : doctorList) {//traversing through the list
            System.out.println(doctor.getName() + "\t" + //printing the list
                    doctor.getSurname() + "\t" +
                    doctor.getDOB() + "\t" +
                    doctor.getNumber() + "\t" +
                    doctor.getLicenseNumber() + "\t" +
                    doctor.getSpecialisation());
        }


    }

    public void printSortedDoctor() {// this method is to print the list of sorted doctors
        sortDoctor();// it sorts the doctor before printing
        System.out.println("This is the sorted list of Doctors");
        for (Doctor doctor : doctorList) {//prints the list
            System.out.println(doctor.getName() + "\t" +
                    doctor.getSurname() + "\t" +
                    doctor.getDOB() + "\t" +
                    doctor.getNumber() + "\t" +
                    doctor.getLicenseNumber() + "\t" +
                    doctor.getSpecialisation());
        }


    }


    private static  void sortDoctor(){// this method is to sort the doctors by their surname
        Collections.sort(doctorList, new Comparator<Doctor>() {
            public int compare(Doctor d1, Doctor d2) {
                return d1.getSurname().compareTo(d2.getSurname());
            }
        });
    }


    @Override
    public void loadFile() throws IOException, ClassNotFoundException {// this method loads/updates all the details to the text file before the program starts
        FileInputStream fi = new FileInputStream("DoctorDetails.txt"); // new text file to load the details
        ObjectInputStream oi = new ObjectInputStream(fi);
        for(;;){
            try{
                Doctor doctor =    (Doctor) oi.readObject();//reads through the object
                doctorList.add(doctor);
            }catch(EOFException e){
                break;

            }
        }
        fi.close();
        oi.close();

        System.out.println("---------------------------------------------------------");
        System.out.println(" Doctors Loaded Successfully");
        sortDoctor();
    }


    @Override
    public void saveFile() throws IOException {//this method is to write the doctor details to a text file
        FileOutputStream f = new FileOutputStream(new File("DoctorDetails.txt"));//creates new next file
        ObjectOutputStream o = new ObjectOutputStream(f);

        for(Doctor doctor : doctorList){//traverses through the text file
            o.writeObject(doctor);//writes them to the text file
        }

        o.flush();//closes the file
        f.close();//closes the file
        o.close();//closes the file
        System.out.println("The file has been successfully saved!!..");//prints sucess message
    }

    @Override
    public ActionEvent loadGUI() throws IOException {//this method is to call and run the GUI
        new GUI(); //calling the class
        return null;
    }
    
}

