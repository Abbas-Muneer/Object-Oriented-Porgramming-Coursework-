import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {// this the main driver class

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Scanner s = new Scanner(System.in);//creates new scanner to get user input
        String input;//creates new string
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager(); //creates new constructor

        try{// load previous file if available
            manager.loadFile();
        }catch (FileNotFoundException e){
            System.out.println("No file found");

        }

        do{//asking users the options they want to choose
            System.out.println("---------------------------------------------");
            System.out.println("This is the menu system");
            System.out.println("Enter 1 to add doctor: ");
            System.out.println("Enter 2 to delete doctor: ");
            System.out.println("Enter 3 to print the unsorted doctor list: ");
            System.out.println("Enter 4 to save the file:");
            System.out.println("Enter 5 to print the sorted doctor list");
            System.out.println("Enter G to open GUI");
            System.out.println("Enter Q to end program");
            System.out.println(" ");

            input = s.next().toUpperCase();// storing the user input to the variable
            switch (input) { //calls method based on user method
                case ("1"):
                    manager.addDoctor();
                    break;
                case ("2"):
                    manager.deleteDoctor();
                    break;
                case ("3"):
                    manager.printDoctor();
                    break;
                case ("4"):
                    manager.saveFile();
                    break;
                case("5"):
                    manager.printSortedDoctor();
                    break;
                case ("G"):
                    manager.loadGUI();
                    break;
                case("Q"):
                    System.out.println("Program Ending.....:(((");//ends program when user enters Q
                    break;
            }
        }while(!input.equals("Q"));

    }
}






//