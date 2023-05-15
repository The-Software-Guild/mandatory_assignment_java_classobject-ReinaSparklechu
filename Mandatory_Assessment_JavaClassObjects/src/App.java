import controllers.DVDLibraryController;
import dao.DVDDao;
import dao.DVDDaoTextFileImpl;
import io.UserIO;
import io.UserIOConsoleImpl;
import view.DVDLibraryView;

public class App {
    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        DVDDao dao = new DVDDaoTextFileImpl();
        DVDLibraryView view = new DVDLibraryView(io);
        DVDLibraryController controller = new DVDLibraryController(dao,view);
        controller.run();

    }


}
