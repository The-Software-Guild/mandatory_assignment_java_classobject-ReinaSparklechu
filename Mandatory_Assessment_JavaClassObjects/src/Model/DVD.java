package Model;

import java.time.DateTimeException;
import java.time.LocalDate;

public class DVD {
    private String title;
    private LocalDate releaseDate;
    private String MPAA;
    private String directorName;
    private String studio;
    private String userRating;

    public DVD() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMPAA() {
        return MPAA;
    }

    public void setMPAA(String MPAA) {
        this.MPAA = MPAA;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public DVD(String title, LocalDate releaseDate, String MPAA, String directorName, String studio, String userRating) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.MPAA = MPAA;
        this.directorName = directorName;
        this.studio = studio;
        this.userRating = userRating;
    }
    public DVD(String title, String releaseDate, String MPAA, String directorName, String studio, String userRating) {
        this.title = title;
        String[] dateInfo = releaseDate.split("/");
        try{
            this.releaseDate = LocalDate.of(Integer.parseInt(dateInfo[2]),Integer.parseInt(dateInfo[1]),Integer.parseInt(dateInfo[0]));
        }catch (DateTimeException e){
            this.releaseDate = null;
            System.err.println(e.getStackTrace());
        }

        this.MPAA = MPAA;
        this.directorName = directorName;
        this.studio = studio;
        this.userRating = userRating;
    }
}
