import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GUI extends WestminsterSkinConsultationManager implements ActionListener {

    //initialising variables for the home page
    private static JFrame homeFrame;
    private static JLabel welcomeLabel = new JLabel("WESTMINSTER SKIN CONSULTATION CLINIC");
    private static JButton viewDoctorButton = new JButton("VIEW DOCTOR DETAILS");
    private static JButton consultDoctorButton = new JButton("CONSULT A DOCTOR");
    private static JButton allConsultationDetailsButton = new JButton("VIEW ALL CONSULTATION DETAILS");
    //..........................................................................

    //initialising variables for viewing Doctor Page
    private static JFrame viewDoctorFrame;
    private static JTable table;
    private static JButton bookDoctorButton = new JButton("Book a Doctor");
    private static JButton sortButton = new JButton("Sort Doctors");
    private static JButton backButtonOne = new JButton("Go Back");
    private static JPanel panelOne;
    //..........................................................................

    //initialising variables for booking Doctor Page
    private static JFrame bookDoctorFrame;
    private static JLabel titleOne = new JLabel("BOOK YOUR DOCTOR");
    private static JPanel panelTwo;
    private static JComboBox<String> chooseDoctor = new JComboBox<String>();
    private static String[] choices = new String[10];//creating an array to store the names of doctors to show the  user so they can choose
    private static JLabel chooseDoctorLabel = new JLabel("Select the doctor you want to consult");
    private static JLabel chooseDateLabel = new JLabel("Choose the date you want to consult the doctor");
    private static JLabel chooseTimeLabel = new JLabel("Choose the time you want to consult the doctor");
    private static JTextField dateTextField = new JTextField("Enter the date in dd/mm/yyyy format");
    private static JTextField timeTextField = new JTextField("Enter the time in hh/mm format");
    private static JButton bookButton = new JButton("BOOK");
    //..........................................................................

    //initialising variables for entering personal details Page
    private static JFrame enterDetailsFrame;
    private static JPanel panelThree;
    private static JLabel titleTwo = new JLabel("ENTER YOUR PERSONAL DETAILS");
    private static JLabel fnameLabel = new JLabel("ENTER YOUR FIRST NAME");
    private static JTextField fNameTextField  = new JTextField("Eg: 'Abbas' ");
    private static JLabel snameLabel = new JLabel("ENTER YOUR SURNAME");
    private static JTextField sNameTextField  = new JTextField("Eg: 'Muneer' ");
    private static JLabel dobLabel = new JLabel("ENTER YOUR D.O.B");
    private static JTextField dobTextField  = new JTextField("Eg: '13/12/2004' ");
    private static JLabel mobileLabel = new JLabel("ENTER YOUR MOBILE NUMBER");
    private static JTextField mobileTextField  = new JTextField("Eg: '0776699899' ");
    private static JLabel idLabel = new JLabel("ENTER YOUR ID NUMBER");
    private static JTextField idTextField  = new JTextField("Eg: '200434800591' ");
    private static JLabel durationLabel = new JLabel("ENTER THE DURATION IN HOURS");
    private static JTextField durationTextField  = new JTextField("Eg: '2' ");
    private static JLabel notesLabel = new JLabel("ADD NOTES");
    private static JTextField notesTextField  = new JTextField("Add Notes");
    private static JButton saveButton = new JButton("SAVE");
    private static JLabel messageLabel = new JLabel("**The notes or images will be encrypted and will be visible to the admins only:)");
    //..........................................................................

    //initialising variables for viewing personal details Page
    private static JFrame viewDetailsFrame;
    private static JPanel panelFour;
    private static JLabel titleThree = new JLabel("BOOKING SUCCESSFULLY DONE");
    private static JLabel nameLabelTwo;
    private static JLabel dobLabelTwo;
    private static JLabel mobileNOLabelTwo;
    private static JLabel idLabelTwo;
    private static JLabel durationLabelTwo;
    private static JLabel costLabel;
    private static JLabel doctorNameLabelTwo;
    private static JLabel dateLabelTwo;
    private static JLabel notesLabelTwo;
    private static JButton exitButton = new JButton("EXIT");
    //..........................................................................

    //initialising variables to use in all places
    private static String doctorName; //creating a string variable to store random doctor name
    FileWriter fWriter = new FileWriter("CheckAvailability.txt",true);//creating a text file in append mode
    private static ArrayList<String> entireDetailsList = new ArrayList<String>();//creating a list to store all the consultation details
    private static int totalCost;
    Random random = new Random();
    String encryptNumber;
    //..........................................................................



    public GUI() throws IOException {  //creating method to run the program
        homePage();
    }


    public void homePage(){ //creating a frame for the home page
        homeFrame = new JFrame(); //creating the home frame
        homeFrame.setTitle("Home Page"); //setting title for the frame
        homeFrame.getContentPane().setBackground(Color.pink);//setting colour of the frame
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setSize(500,500); //setting size of the frame
        homeFrame.setLayout(null);

        welcomeLabel.setBounds(10,10,500,50); //setting bounds of title
        welcomeLabel.setFont(new Font("Serif",Font.BOLD,21)); //changing font size and font type to make it look good

        viewDoctorButton.setBounds(115,100,250,50);//setting the height,width,positions of the buttons
        viewDoctorButton.setBackground(Color.orange);//setting colour of the button
        viewDoctorButton.setForeground(Color.BLACK);
        viewDoctorButton.setFocusable(false);//setting the focusable of the buttons to false
        viewDoctorButton.addActionListener(this);//making the button perform an action on click

        consultDoctorButton.setBounds(115,200,250,50);//setting the height,width,positions of the buttons
        consultDoctorButton.setBackground(Color.orange);//setting colour of the button
        consultDoctorButton.setForeground(Color.BLACK);
        consultDoctorButton.setFocusable(false);//setting the focusable of the buttons to false
        consultDoctorButton.addActionListener(this);//making the button perform an action on click

        allConsultationDetailsButton.setBounds(115,300,250,50);//setting the height,width,positions of the buttons
        allConsultationDetailsButton.setBackground(Color.orange);//setting colour of the button
        allConsultationDetailsButton.setForeground(Color.BLACK);
        allConsultationDetailsButton.setFocusable(false);//setting the focusable of the buttons to false
        allConsultationDetailsButton.addActionListener(this);//making the button perform an action on click

        //adding the label and button to the frame
        homeFrame.add(welcomeLabel);
        homeFrame.add(viewDoctorButton);
        homeFrame.add(consultDoctorButton);
        homeFrame.add(allConsultationDetailsButton);

        homeFrame.setVisible(true);//setting the frame to be visible
    }

    public void viewDoctor(){//this frame is to view the doctor's list
        viewDoctorFrame = new JFrame(); //creating frame to view the doctors
        viewDoctorFrame.setTitle("View all Doctors");//setting the title of the frame
        viewDoctorFrame.getContentPane().setBackground(Color.pink);//setting colour of the frame
        viewDoctorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewDoctorFrame.setSize(500,1000); //setting size of the frame
        viewDoctorFrame.setLayout(new GridLayout(2,2));

        Object[][] unsortedData = new Object[doctorList.size()][6]; //creating an array to store the doctor details to print in the table
        for(int i = 0; i<doctorList.size(); i++){
            unsortedData[i][0] = doctorList.get(i).getName(); //adding the doctor list to the array items
            unsortedData[i][1] = doctorList.get(i).getSurname();
            unsortedData[i][2] = doctorList.get(i).getDOB();
            unsortedData[i][3] = doctorList.get(i).getNumber();
            unsortedData[i][4] = doctorList.get(i).getLicenseNumber();
            unsortedData[i][5] = doctorList.get(i).getSpecialisation();
        }


        String[] columns = {"Name", "Surname", "D.O.B", "Mobile No.", "License No.", "Specialisation"}; //creating a column to display the heading of the tables

        table = new JTable(unsortedData,columns);//adding the columns and details to the tables
        table.setBounds(0,0,500,600); //setting the height, width, and position of the table
        table.setBackground(Color.orange);//setting the colour of the table

        bookDoctorButton.setBounds(10,250,250,50);
        bookDoctorButton.setPreferredSize(new Dimension(450,50));
        bookDoctorButton.setBackground(Color.orange);//setting colour of the button
        bookDoctorButton.setForeground(Color.BLACK);
        bookDoctorButton.setFocusable(false);//setting the focusable of the buttons to false
        bookDoctorButton.addActionListener(this);//making the button perform an action on click

        sortButton.setBounds(10,800,250,50);
        sortButton.setPreferredSize(new Dimension(450,50));
        sortButton.setBackground(Color.orange);//setting colour of the button
        sortButton.setForeground(Color.BLACK);
        sortButton.setFocusable(false);//setting the focusable of the buttons to false
        sortButton.addActionListener(this);//making the button perform an action on click

        backButtonOne.setBounds(10,1000,450,50);
        backButtonOne.setPreferredSize(new Dimension(450,50));
        backButtonOne.setBackground(Color.orange);//setting colour of the button
        backButtonOne.setForeground(Color.BLACK);
        backButtonOne.setFocusable(false);//setting the focusable of the buttons to false
        backButtonOne.addActionListener(this);//making the button perform an action on click

        panelOne = new JPanel(); //creating panel to add the buttons
        panelOne.setBounds(0,700,500,400); //setting bounds for the panel
        panelOne.setBackground(Color.pink);//setting the colour of the panel
        panelOne.add(bookDoctorButton);//adding the buttons to the panel
        panelOne.add(sortButton);
        panelOne.add(backButtonOne);

        // adding the content to the frame
        viewDoctorFrame.add(new JScrollPane(table));
        viewDoctorFrame.add(panelOne);

        viewDoctorFrame.setVisible(true); //setting the frame to be visible
    }


    public void viewSortedDoctor(){ //thisframe is to show the sorted doctor list when user clicks the sort button
        Collections.sort(doctorList, new Comparator<Doctor>() {
            public int compare(Doctor d1, Doctor d2) {
                return d1.getSurname().compareTo(d2.getSurname());
            }
        });

        viewDoctorFrame = new JFrame(); //creating frame to view the doctors
        viewDoctorFrame.setTitle("View all Doctors");//setting the title of the frame
        viewDoctorFrame.getContentPane().setBackground(Color.pink);//setting colour of the frame
        viewDoctorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewDoctorFrame.setSize(500,1000); //setting size of the frame
        viewDoctorFrame.setLayout(new GridLayout(2,2));

        Object[][] sortedData = new Object[doctorList.size()][6]; //creating an array to store the doctor details to print in the table
        for(int i = 0; i<doctorList.size(); i++){
            sortedData[i][0] = doctorList.get(i).getName(); //adding the doctor list to the array items
            sortedData[i][1] = doctorList.get(i).getSurname();
            sortedData[i][2] = doctorList.get(i).getDOB();
            sortedData[i][3] = doctorList.get(i).getNumber();
            sortedData[i][4] = doctorList.get(i).getLicenseNumber();
            sortedData[i][5] = doctorList.get(i).getSpecialisation();
        }


        String[] columns = {"Name", "Surname", "D.O.B", "Mobile No.", "License No.", "Specialisation"}; //creating a column to display the heading of the tables

        table = new JTable(sortedData,columns);//adding the columns and details to the tables
        table.setBounds(0,0,500,600); //setting the height, width, and position of the table
        table.setBackground(Color.orange);//setting the colour of the table

        bookDoctorButton.setBounds(10,250,250,50);
        bookDoctorButton.setPreferredSize(new Dimension(450,50));
        bookDoctorButton.setBackground(Color.orange);//setting colour of the button
        bookDoctorButton.setForeground(Color.BLACK);
        bookDoctorButton.setFocusable(false);//setting the focusable of the buttons to false
        bookDoctorButton.addActionListener(this);//making the button perform an action on click

        sortButton.setBounds(10,800,250,50);
        sortButton.setPreferredSize(new Dimension(450,50));
        sortButton.setBackground(Color.orange);//setting colour of the button
        sortButton.setForeground(Color.BLACK);
        sortButton.setFocusable(false);//setting the focusable of the buttons to false
        sortButton.addActionListener(this);//making the button perform an action on click

        backButtonOne.setBounds(10,1000,450,50);
        backButtonOne.setPreferredSize(new Dimension(450,50));
        backButtonOne.setBackground(Color.orange);//setting colour of the button
        backButtonOne.setForeground(Color.BLACK);
        backButtonOne.setFocusable(false);//setting the focusable of the buttons to false
        backButtonOne.addActionListener(this);//making the button perform an action on click

        panelOne = new JPanel(); //creating panel to add the buttons
        panelOne.setBounds(0,700,500,400); //setting bounds for the panel
        panelOne.setBackground(Color.pink);//setting the colour of the panel
        panelOne.add(bookDoctorButton);//adding the buttons to the panel
        panelOne.add(sortButton);
        panelOne.add(backButtonOne);

        // adding the content to the frame
        viewDoctorFrame.add(new JScrollPane(table));
        viewDoctorFrame.add(panelOne);

        viewDoctorFrame.setVisible(true); //setting the frame to be visible
    }

    public void bookDoctor(){
        bookDoctorFrame = new JFrame();
        bookDoctorFrame.setTitle("Book the Doctor");//setting the title of the frame
        bookDoctorFrame.getContentPane().setBackground(Color.pink);//setting colour of the frame
        bookDoctorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bookDoctorFrame.setSize(500,500); //setting size of the frame



        //inserting the doctor names to the combo box for ths uer to choose
        for(int i = 0; i<doctorList.size(); i++){
            String name = doctorList.get(i).getName();
            choices[i] = name;
        }

        chooseDoctor = new JComboBox((String[]) choices);//adding the list of doctor nameto combo box

        titleOne.setFont(new Font("Serif", Font.BOLD, 30)); //setting the title of the page
        titleOne.setForeground(Color.BLACK);//setting the colour of the font

        chooseDoctorLabel.setBounds(10,35,300,50); //setting bounds of the label
        chooseDoctor.setBounds(10,75,450,50);//setting bounds of the label
        chooseDoctor.setBackground(Color.orange);

        chooseDateLabel.setBounds(10,125,300,50);//setting bounds of the label
        dateTextField.setBounds(10,165,450,50);//setting bounds of the text field
        dateTextField.setBackground(Color.orange); //setting colour of the text field

        chooseTimeLabel.setBounds(10,215,300,50);
        timeTextField.setBounds(10,255,450,50);
        timeTextField.setBackground(Color.orange);

        bookButton.setBounds(175,350,100,50);//setting bounds to the button
        bookButton.setBackground(Color.black);//setting the colour of the button
        bookButton.setForeground(Color.WHITE);
        bookButton.addActionListener(this); //making the button perform an action on click

        panelTwo = new JPanel(); //creating panel two
        panelTwo.setLayout(null);
        panelTwo.setBackground(Color.pink);
        panelTwo.add(titleOne);//adding all elements to panel 2
        panelTwo.add(chooseDoctorLabel);
        panelTwo.add(chooseDoctor);
        panelTwo.add(chooseDateLabel);
        panelTwo.add(dateTextField);
        panelTwo.add(chooseTimeLabel);
        panelTwo.add(timeTextField);
        panelTwo.add(bookButton);

        bookDoctorFrame.add(panelTwo);//adding the panel to frame
        bookDoctorFrame.setVisible(true); //setting the frame to be visible
    }

    public void enterPersonalDetails(){ //this method is to create a frame where user can enter their personal details after booking
        enterDetailsFrame = new JFrame();
        enterDetailsFrame.setTitle("Enter Personal Details");//setting the title of the frame
        enterDetailsFrame.getContentPane().setBackground(Color.pink);//setting colour of the frame
        enterDetailsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        enterDetailsFrame.setSize(500,800); //setting size of the frame

        panelThree = new JPanel();//creating the panel
        panelThree.setLayout(null); //setting the layout of the panel to null
        panelThree.setBackground(Color.pink);

        titleTwo.setBounds(10,20,500,50); //setting bounds of the label
        titleTwo.setFont(new Font("Serif", Font.PLAIN, 29)); //changing font size and type of the label

        fnameLabel.setBounds(10,50,400,50);
        fNameTextField.setBounds(10,90,450,25);
        fNameTextField.setBackground(Color.orange);

        snameLabel.setBounds(10,140,300,50);
        sNameTextField.setBounds(10,180,450,25);
        sNameTextField.setBackground(Color.orange);

        dobLabel.setBounds(10,230,300,50);
        dobTextField.setBounds(10,270,450,25);
        dobTextField.setBackground(Color.orange);

        mobileLabel.setBounds(10,320,300,50);
        mobileTextField.setBounds(10,360,450,25);
        mobileTextField.setBackground(Color.orange);

        idLabel.setBounds(10,410,300,50);
        idTextField.setBounds(10,450,450,25);
        idTextField.setBackground(Color.orange);

        durationLabel.setBounds(10,500,300,50);
        durationTextField.setBounds(10,540,450,25);
        durationTextField.setBackground(Color.orange);

        notesLabel.setBounds(10,550,300,50);
        notesTextField.setBounds(10,590,450,70);
        notesTextField.setBackground(Color.orange);

        int encryptedText = random.nextInt(1000);//a new number from 1-1000 gets generated
        encryptNumber = String.valueOf(encryptedText); //that number is now the encrypted form of the notes that user added

        saveButton.setBounds(175,680,100,50);
        saveButton.setBackground(Color.BLACK);
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(this);

        messageLabel.setBounds(2,700,490,100);
        messageLabel.setFont(new Font("Serif", Font.PLAIN, 15)); //changing font size and type of the label


        panelThree.add(titleTwo); //adding the labels, text fields and buttons to the panel
        panelThree.add(fnameLabel);
        panelThree.add(fNameTextField);
        panelThree.add(snameLabel);
        panelThree.add(sNameTextField);
        panelThree.add(dobLabel);
        panelThree.add(dobTextField);
        panelThree.add(mobileLabel);
        panelThree.add(mobileTextField);
        panelThree.add(idLabel);
        panelThree.add(idTextField);
        panelThree.add(durationLabel);
        panelThree.add(durationTextField);
        panelThree.add(notesLabel);
        panelThree.add(notesTextField);
        panelThree.add(saveButton);
        panelThree.add(messageLabel);


        enterDetailsFrame.add(panelThree); //adding the panel to the frame
        enterDetailsFrame.setVisible(true); //setting the frame to be visible
    }

    public void viewPersonalDetails(){
        viewDetailsFrame = new JFrame();
        viewDetailsFrame.setTitle("View your entered personal details");
        viewDetailsFrame.getContentPane().setBackground(Color.pink);//setting colour of the frame
        viewDetailsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewDetailsFrame.setSize(500,800); //setting size of the frame

        panelFour = new JPanel();//creating the panel
        panelFour.setLayout(null); //setting the layout of the panel to null
        panelFour.setBackground(Color.pink);
        Border blackline = BorderFactory.createLineBorder(Color.black); //creating a border line
        panelFour.setBorder(blackline);

        titleThree.setBounds(10,20,490,50);
        titleThree.setFont(new Font("Serif", Font.PLAIN, 30));

        nameLabelTwo = new JLabel("Name -                            " +fNameTextField.getText() + "    " + sNameTextField.getText());//adding text to label
        nameLabelTwo.setBounds(20,70,450,50);//setting bounds to the label
        nameLabelTwo.setFont(new Font("Serif", Font.PLAIN, 20)); //changing the font size and type of the label
        nameLabelTwo.setBorder(blackline);//adding the border to the label


        dobLabelTwo = new JLabel("D.O.B -                                               " + dobTextField.getText());
        dobLabelTwo.setBounds(20,140,450,50);
        dobLabelTwo.setFont(new Font("Serif", Font.PLAIN, 20));
        dobLabelTwo.setBorder(blackline);

        mobileNOLabelTwo = new JLabel("Mobile Number -                             " + mobileTextField.getText());
        mobileNOLabelTwo.setBounds(20,210,450,50);
        mobileNOLabelTwo.setFont(new Font("Serif", Font.PLAIN, 20));
        mobileNOLabelTwo.setBorder(blackline);

        idLabelTwo = new JLabel("ID -                                 " + idTextField.getText());
        idLabelTwo.setBounds(20,280,450,50);
        idLabelTwo.setFont(new Font("Serif", Font.PLAIN, 20));
        idLabelTwo.setBorder(blackline);

        durationLabelTwo = new JLabel("Duration of Consultation -                     " + durationTextField.getText() + " Hours");
        durationLabelTwo.setBounds(20,350,450,50);
        durationLabelTwo.setFont(new Font("Serif", Font.PLAIN, 20));
        durationLabelTwo.setBorder(blackline);

        costLabel = new JLabel("Total Cost -                                              " + totalCost + " Pounds");
        costLabel.setBounds(20,420,450,50);
        costLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        costLabel.setBorder(blackline);

        doctorNameLabelTwo = new JLabel("Assigned Doctor -                               " + "Dr. " + doctorName);
        doctorNameLabelTwo.setBounds(20,490,450,50);
        doctorNameLabelTwo.setFont(new Font("Serif", Font.PLAIN, 20));
        doctorNameLabelTwo.setBorder(blackline);

        dateLabelTwo = new JLabel("Booked Date & Time "  + dateTextField.getText() + "  " + " on " + "  " + timeTextField.getText());
        dateLabelTwo.setBounds(20,560,450,50);
        dateLabelTwo.setFont(new Font("Serif", Font.PLAIN, 20));
        dateLabelTwo.setBorder(blackline);


        notesLabelTwo = new JLabel("Notes in encrypted mode -                                    "  + encryptNumber);
        notesLabelTwo.setBounds(20,630,450,50);
        notesLabelTwo.setFont(new Font("Serif", Font.PLAIN, 20));
        notesLabelTwo.setBorder(blackline);

        exitButton.setBounds(175,700,100,50);
        exitButton.setBackground(Color.orange);
        exitButton.addActionListener(this);

        panelFour.add(titleThree); //adding the labels and buttons to the panel
        panelFour.add(nameLabelTwo);
        panelFour.add(dobLabelTwo);
        panelFour.add(mobileNOLabelTwo);
        panelFour.add(idLabelTwo);
        panelFour.add(durationLabelTwo);
        panelFour.add(costLabel);
        panelFour.add(doctorNameLabelTwo);
        panelFour.add(dateLabelTwo);
        panelFour.add(notesLabelTwo);
        panelFour.add(exitButton);

        viewDetailsFrame.add(panelFour); //adding the panel to the frame
        viewDetailsFrame.setVisible(true); //setting the frame to be visible
    }


     //gui methods
    public void checkDateTime(){//this is to check whether the entered date format is correct
        Date date;
        try{
            date = new SimpleDateFormat("dd/mm/yyy").parse(dateTextField.getText());
            checkDoctorVacancy();
        } catch (ParseException ex){
            JFrame warning = new JFrame();
            JOptionPane.showMessageDialog(warning,"Invalid Date format","Alert",JOptionPane.WARNING_MESSAGE);
            return;
        }
    }

    public void checkValidation() throws NoSuchPaddingException, NoSuchAlgorithmException {//this method is to check the user input validation for the user's personal details
        String fName = fNameTextField.getText(); //storing the text field inputs to a vaiable
        String surName = sNameTextField.getText();
        String dob = dobTextField.getText();
        String mobile = mobileTextField.getText();
        String duration = durationTextField.getText();
        String id = idTextField.getText();

        if(fName.equalsIgnoreCase("")) { //checking whether the input in empty
            //displays an error message if its empty
            JOptionPane.showMessageDialog(null, "First Name can not be empty", "Error", JOptionPane.ERROR_MESSAGE);
            fNameTextField.requestFocus();
        }
        else if(surName.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(null, "Sir Name can not be empty", "Error", JOptionPane.ERROR_MESSAGE);
            sNameTextField.requestFocus();
        }
        else if(dob.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "D.O.B can not be empty", "Error", JOptionPane.ERROR_MESSAGE);
            dobTextField.requestFocus();
        }
        else if(mobile.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Mobile Number can not be empty", "Error", JOptionPane.ERROR_MESSAGE);
            mobileTextField.requestFocus();
        }
        else if(duration.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Duration can not be empty", "Error", JOptionPane.ERROR_MESSAGE);
            durationTextField.requestFocus();
        }
        else if(id.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "ID number can not be empty", "Error", JOptionPane.ERROR_MESSAGE);
            idTextField.requestFocus();
        }
        else if((mobile.length()>10) || mobile.length()<0){
            JOptionPane.showMessageDialog(null, "Mobile number can contain only 10 numbers", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            //asks whether it's the first consultation
            int consultation = JOptionPane.showConfirmDialog(null, "Is this your first consultation?");
            switch (consultation) {
                case JOptionPane.YES_OPTION:
                    totalCost = Integer.parseInt(durationTextField.getText())*15;//calculates cost
                    break;
                case JOptionPane.NO_OPTION:
                    totalCost = Integer.parseInt(durationTextField.getText())*25;//calculates cost
                    break;
            }
            //if it's not empty it add the details to a list
            entireDetailsList.add("Name " + fNameTextField.getText() + " " + sNameTextField.getText() + " " + "DOB " + dobTextField.getText() + " " +
                    "Mobile " + mobileTextField.getText() + " " + "ID " + idTextField.getText() + " " +  durationTextField.getText() + " Hours " + " " + totalCost + "Pounds" + " " +  "Decrypted Text " +
                    notesTextField.getText() + " " + "Dr " + doctorName + " " + "Date " + dateTextField.getText() + " " + "Time " + timeTextField.getText());
            //displays success message
            JOptionPane.showMessageDialog(null,"Successfully Saved","Message",JOptionPane.INFORMATION_MESSAGE);
            try {
                saveEntireConsultationDetails();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            enterDetailsFrame.dispose();
            viewPersonalDetails();
        }

    }

    public void checkDoctorVacancy(){// this method is to check whether the doctor is already booked
        //checks whether the user entered details are already in text file
        if(checkDoctorInFile("CheckAvailability.txt", chooseDoctor.getSelectedItem().toString()+ " " + dateTextField.getText() + " " + timeTextField.getText())){
            JFrame warning = new JFrame();
            JOptionPane.showMessageDialog(warning,"This Date and Time is not available!! Sorry:(((:(","Alert",JOptionPane.WARNING_MESSAGE);
            //creates random doctor if its booked
            String randomName = choices[random.nextInt(10)];
            doctorName = randomName;//assigns the random doctor to a variable
            try{
                fWriter.write(doctorName + " " + dateTextField.getText() + " " + timeTextField.getText() + "\n");//writing the details to a text
                fWriter.close();
            }catch (IOException e){
                e.printStackTrace();
            }
            //displays message
            JOptionPane.showMessageDialog(null,"Doctor " + randomName + " has been assigned to you on " + dateTextField.getText() + " on " + timeTextField.getText(),"Message",JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null,"Please Enter your personal details to confirm booking","Message",JOptionPane.INFORMATION_MESSAGE);
            bookDoctorFrame.dispose();
            enterPersonalDetails();//opens entering personal details page

        }
        else{
            doctorName = chooseDoctor.getSelectedItem().toString();
            //displays message
            JOptionPane.showMessageDialog(null,"Booking Successfully Done","Message",JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null,"Please Enter your personal details to confirm booking","Message",JOptionPane.INFORMATION_MESSAGE);
            bookDoctorFrame.dispose();
            enterPersonalDetails();//opens entering personal details page
            try{
                fWriter.write(chooseDoctor.getSelectedItem().toString()+ " " + dateTextField.getText() + " " + timeTextField.getText() + "\n");//writing the details to a text
                fWriter.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    private boolean checkDoctorInFile(String logFile, String text) {//checks whether the text exists in file using buffered reader method
        try{
            BufferedReader buff=new BufferedReader(new FileReader(logFile));
            String s;
            while((s=buff.readLine())!=null){
                if(s.trim().contains(text)){
                    return true;
                }
            }
            buff.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void saveEntireConsultationDetails() throws IOException {//this saves all the booking details including user personal details in a text file
        fWriter = new FileWriter("EntireConsultationDetails.txt",true);//create a new text file
        try{
            for(int i=0; i<entireDetailsList.size(); i++){//goes through the array list which contains the details
                fWriter.write(entireDetailsList.get(i) + "\n"); //writes them into the file
                fWriter.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void viewEntireConsultationDetails(){//this method opens when the user clicks to view all entire details of the consultation
        String password = JOptionPane.showInputDialog("This is only for the admins. Please enter the password if you are an admin:)");// it will ask password bcz its restrcited to admins
        if(password.equalsIgnoreCase("1234")){//checks whether password is 1234
            //displays a message
            JOptionPane.showMessageDialog(null,"Password Correct, Check Console for all the details of the entire consultation history.", "Admin",JOptionPane.PLAIN_MESSAGE);
            try {
                File file = new File("EntireConsultationDetails.txt");//opens the file
                Scanner scan = new Scanner(file);
                while(scan.hasNextLine()){
                    System.out.println(scan.nextLine());//prints the details in the console
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else{
            //displays the message if the password is incorrect
            JOptionPane.showMessageDialog(null,"Incorrect Password entered", "Admin",JOptionPane.WARNING_MESSAGE);
        }
    }


    //calling methods once the button gets clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==viewDoctorButton){
            homeFrame.dispose();
            viewDoctor();
        }
        else if(e.getSource()==consultDoctorButton){
            homeFrame.dispose();
            bookDoctor();
        }
        else if(e.getSource()==allConsultationDetailsButton){
            viewEntireConsultationDetails();
        }
        else if(e.getSource()==bookDoctorButton){
            viewDoctorFrame.dispose();
            bookDoctor();
        }
        else if(e.getSource()==sortButton){
            viewDoctorFrame.dispose();
            viewSortedDoctor();
        }
        else  if(e.getSource()==backButtonOne){
            viewDoctorFrame.dispose();
            homeFrame.dispose();
            homePage();
        }
        else if(e.getSource()==bookButton){
            checkDateTime();
        }
        else if(e.getSource()==saveButton){
            try {
                checkValidation();
            } catch (NoSuchPaddingException ex) {
                ex.printStackTrace();
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }

            //viewPersonalDetails();
        }
        else if(e.getSource()==exitButton){
            System.exit(0);
        }
    }
}
