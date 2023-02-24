import java.awt.event.ActionEvent;
import java.io.IOException;

public interface SkinConsultationManager {//this interface stores all the method in the westminster manage class
    void addDoctor();
    void deleteDoctor();
    void printDoctor();
    void printSortedDoctor();
    //void sortDoctor();
    void saveFile() throws IOException;
    void loadFile() throws IOException , ClassNotFoundException;
    ActionEvent loadGUI() throws IOException;
}
