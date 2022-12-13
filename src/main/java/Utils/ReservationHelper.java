package Utils;

import ExceptionHandling.MissingReservationsException;
import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.DTOLibraryItems.Reservation;
import Library.Dto.java.DTOLibraryItems.ReservationDueDates;
import Library.Dto.java.DTOLibraryItems.ReservationTypes;
import com.example.librarysoftware.ReservationSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationHelper {
    public static List<Reservation> LoadReservations() throws MissingReservationsException {
        List<Reservation> reservations = new ArrayList<>();
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        reservations = (List<Reservation>)(Object) lr.GetListOfObject(QueryGenerator.GetListOfReservations());
        if(reservations == null){
            throw new MissingReservationsException();
        }

        return  reservations;

    }

    public static boolean AddReservation(int readerId, int itemId, String borrowDate, ReservationDueDates dueDate){
        //to add - quantity update
        Reservation res = ReservationFactory.CreateReservation(itemId, readerId, borrowDate, dueDate);

        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        lr.AddObject(res);

        ItemHelper.GiveItem(itemId);

        ReservationSession.AddReservation(res);

        return true;
    }

    public static void DeleteReservation(Reservation res){
        //to add - quantity update
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        lr.DeleteObject(res);
    }

}
