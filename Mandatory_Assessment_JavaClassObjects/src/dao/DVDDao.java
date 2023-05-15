package dao;

import Model.DVD;

import java.io.IOException;
import java.util.List;

public interface DVDDao {
    void save(DVD dvd);
    void delete(String dvdTitle);

    List<DVD> getAll();
    DVD getDVDByTitle(String title);
    List<DVD> getDVDsMatchingQuery(String query);

    boolean changeLibrary(String libraryName);

    void saveLibrary() throws IOException;
}
