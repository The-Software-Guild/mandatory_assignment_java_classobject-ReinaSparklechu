package view;

import Model.DVD;
import io.UserIO;

public class DVDLibraryView {
    UserIO io;
    public DVDLibraryView(UserIO io){
        this.io = io;
    }

    public void displayDVD(DVD dvd){
        io.displayMessage("====================");
        io.displayMessage("Title: " +dvd.getTitle());
        io.displayMessage("Date released: " + dvd.getReleaseDate().toString());
        io.displayMessage("MPAA Rating: " + dvd.getMPAA());
        io.displayMessage("Director: " + dvd.getDirectorName());
        io.displayMessage("Studio: " + dvd.getStudio());
        io.displayMessage("Notes/User Rating: " + dvd.getUserRating());


    }

    public String getInput(String prompt){
        return io.getUserInput(prompt);
    }


    public void displayMenu(){
        io.displayMessage("=====Menu=====");
        io.displayMessage("1) Add DVD to library");
        io.displayMessage("2) Delete DVD");
        io.displayMessage("3) Edit DVD");
        io.displayMessage("4) Display all DVDs stored in library");
        io.displayMessage("5) Display information of 1 DVD");
        io.displayMessage("6) Search for DVDs by title");
        io.displayMessage("7) Load different DVD library");
        io.displayMessage("8) Save all data back to DVD library");
        io.displayMessage("9) Exit program");
    }

    public void displayMessage(String message){
        io.displayMessage(message);
    }

}
