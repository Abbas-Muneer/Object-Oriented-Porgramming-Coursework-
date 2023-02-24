public class Patient extends Person{//this has all the info about patient

    private String title;
    private String id;
    private String mail;


    //creates a constructor
    public Patient(String name, String surname, String dob, String mobile_number, String title, String id, String mail){
        super(name, surname, dob, mobile_number);
        this.title = title;
        this.id= id;
        this.mail = mail;
    }

    //getters and setters methods
    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setMail(String mail){
        this.mail = mail;
    }

    public String getMail(){
        return mail;
    }



    public String toString(){
        return super.toString() +
                "Title = " + title +
                "ID = "  + id +
                "Mail = "+ mail ;

    }
}
