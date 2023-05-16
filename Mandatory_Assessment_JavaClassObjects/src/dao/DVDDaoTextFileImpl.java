package dao;

import Model.DVD;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class DVDDaoTextFileImpl implements DVDDao{
    private Map<String, DVD> dvdMap;
    private String filename;
    public void loadDVD(String filename) throws FileNotFoundException {
        Map<String, DVD> dvdMap = new HashMap<>();
        Scanner scanner = new Scanner(new FileInputStream(filename));
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] data = line.split(","); //title,releasedate,mpaa,directorName,studio,userRating
            DVD dvd = new DVD(data[0],data[1],data[2],data[3],data[4],data[5]);
            dvdMap.put(dvd.getTitle(), dvd);
        }
        this.dvdMap = dvdMap;
    }
    public DVDDaoTextFileImpl(){
        this.filename = "DefaultLibrary.txt";
        Scanner scanner;
        try{
           loadDVD(this.filename);
        } catch (FileNotFoundException e) {
            System.err.println(e.getStackTrace());
            dvdMap = new HashMap<>();
        }
    }

    @Override
    public void save(DVD dvd) {
        this.dvdMap.put(dvd.getTitle(), dvd);
    }

    @Override
    public void delete(String dvdTitle) {
        this.dvdMap.remove(dvdTitle);
    }

    @Override
    public List<DVD> getAll() {
        return this.dvdMap.values().stream().collect(Collectors.toList());

    }

    @Override
    public DVD getDVDByTitle(String title) {
        return this.dvdMap.get(title);
    }

    @Override
    public List<DVD> getDVDsMatchingQuery(String query) {
        Set<String> keys = this.dvdMap.keySet();
        List<String> matches = new ArrayList<>();
        for(String key: keys){
            if(key.contains(query)){
                matches.add(key);
            }
        }
        List<DVD> matchingDVDs = new ArrayList<>();
        for(String match: matches){
            matchingDVDs.add(this.dvdMap.get(match));
        }
        return matchingDVDs;
    }

    @Override
    public boolean changeLibrary(String libraryName){
        try{
            loadDVD(libraryName);
        }catch (FileNotFoundException f){
            this.filename = libraryName;
            this.dvdMap = new HashMap<>();
            return false;
        }
        return true;

    }

    @Override
    public void saveLibrary() throws IOException {
        File f = new File(filename);
        FileWriter writer = new FileWriter(f);
        for(DVD dvd: dvdMap.values()){
            String line = dvd.getTitle()+","+String.valueOf(dvd.getReleaseDate().getDayOfMonth())+"/"+String.valueOf(dvd.getReleaseDate().getMonth().getValue())+"/"+String.valueOf(dvd.getReleaseDate().getYear())+","+
                    dvd.getMPAA()+","+dvd.getDirectorName()+","+dvd.getStudio()+","+dvd.getUserRating()+"\n";
            writer.write(line);
        }
        writer.close();
    }
}
