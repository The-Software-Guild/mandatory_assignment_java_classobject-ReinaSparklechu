package controllers;

import Model.DVD;
import dao.DVDDao;
import view.DVDLibraryView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class DVDLibraryController {
    DVDDao dao;
    DVDLibraryView view;
    public DVDLibraryController(DVDDao dao, DVDLibraryView view){
        this.dao = dao;
        this.view = view;
    }

    public void run(){
        boolean end = false;
        int selection = 0;
        while(!end){
            view.displayMenu();
            selection = Integer.parseInt(view.getInput("Please enter your choice from the options given above"));
            switch(selection){
                case 1:
                    addDvd();
                    break;
                case 2:
                    deleteDvd();
                    break;
                case 3:
                    editDvd();
                    break;
                case 4:
                    displayAllDvd();
                    break;
                case 5:
                    displayRequestedDvd();
                    break;
                case 6:
                    searchDvdByQuery();
                    break;
                case 7:
                    loadOtherLibrary();
                    break;
                case 8:
                    saveLibrary();
                    break;
                case 9:
                    end = true;
                    break;
                default:
                    view.displayMessage("Invalid option");
                    break;

            }
        }
    }

    private void saveLibrary() {
        try{
            dao.saveLibrary();
        }catch (IOException e){
            view.displayMessage("===The library does not seem to exist===");
        }catch(Exception e){
            view.displayMessage("Something went horribly wrong");
        }

    }

    private void loadOtherLibrary() {
        String libraryName =view.getInput("Enter the name of the library you would like to view: ");
        if(dao.changeLibrary(libraryName)){
            view.displayMessage("===Library changed successfully===");
        }else{
            view.displayMessage("Library does not exist.");
            view.displayMessage("Created new library " + libraryName);
        }
    }

    private void searchDvdByQuery() {
        String query = view.getInput("Please enter your title search query: ");
        List<DVD> dvdList = dao.getDVDsMatchingQuery(query);

        if(dvdList.size() ==0){
            view.displayMessage("There were no DVDs that had a title matching your query");
        }else{
            view.displayMessage("Here's a list of DVDs that match your query");
            for(DVD dvd: dvdList){
                view.displayDVD(dvd);
            }
        }
    }

    private void displayRequestedDvd() {
        DVD dvd = dao.getDVDByTitle(view.getInput("Please enter the title of the DVD which information you would like to view"));
        if(dvd == null){
            view.displayMessage("DVD does not exist");
        }else{
            view.displayDVD(dvd);
        }
    }

    private void displayAllDvd() {
        for(DVD dvd: dao.getAll()){
            view.displayDVD(dvd);
        }
        view.displayMessage("=====END OF LIST=====");
    }

    private void editDvd() {
        String title = view.getInput("Enter the title of the DVD you would like to edit");
        DVD dvd = dao.getDVDByTitle(title);
        if(dvd != null){

            int selection =0;
            boolean done = false;
            view.displayMessage("DVD found");
            String editMenu = "1) Title\n"+
                    "2) Date\n"+
                    "3) MPAA rating \n" +
                    "4) Director name \n" +
                    "5) Studio name \n" +
                    "6) Comments/Rating \n"+
                    "7) Done";
            while(!done){
                view.displayMessage(editMenu);
                selection = Integer.valueOf(view.getInput("Please enter your selection"));
                switch (selection){
                    case 1:
                        String oldTitle = dvd.getTitle();
                        dvd.setTitle(view.getInput("Please enter DVD's new title: "));
                        dao.delete(oldTitle);
                        dao.save(dvd);
                        break;
                    case 2:
                        String date = view.getInput("Please enter DVD's release date in dd/mm/yyyy format: ");
                        String[] values = date.split("/");
                        dvd.setReleaseDate(LocalDate.of(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2])));
                        view.displayMessage("Date has been saved");
                        break;
                    case 3:
                        dvd.setMPAA(view.getInput("Please enter new MPAA rating: "));
                        view.displayMessage("MPAA has been saved");
                        break;
                    case 4:
                        dvd.setDirectorName(view.getInput("Please enter new director name: "));
                        view.displayMessage("Director has been saved");
                        break;
                    case 5:
                        dvd.setStudio(view.getInput("Please enter new studio name: "));
                        view.displayMessage("Studio has been saved");
                        break;
                    case 6:
                        dvd.setUserRating(view.getInput("Please enter new comments and personal ratings: "));
                        view.displayMessage("Personal comments and ratings have been saved");
                        break;
                    case 7:
                        dao.save(dvd);
                        view.displayMessage("DVD has been saved to library");
                        done=true;
                        break;
                    default:
                        view.displayMessage("Invalid option");
                        break;
                }
            }

        }else{
            view.displayMessage("DVD does not exist in the library");
        }
    }

    private void deleteDvd() {
        String title = view.getInput("Enter the title of the DVD you would like to delete from the library");
        dao.delete(title);
        view.displayMessage("DVD has been removed from the library");
    }

    private void addDvd() {
        String title = view.getInput("Please enter title of the DVD: ");
        String releaseDate = view.getInput("Please enter release date of DVD in format dd/mm/yyyy: ");
        String MPAA = view.getInput("Please enter MPAA rating for the DVD: ");
        String directorName = view.getInput("Please enter the director's name: ");
        String studio = view.getInput("Please enter the name of the studio: ");
        String userRating = view.getInput("Please enter any comments and ratings you have: ");
        dao.save(new DVD(title,releaseDate,MPAA,directorName,studio,userRating));
        view.displayMessage("DVD has been saved to the library");
    }
}
