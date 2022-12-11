package Utils;

import ExceptionHandling.MissingReservationsException;
import Hibernate.Control.Main.HibernateMain;
import Hibernate.Control.Main.Repository.LibraryRepository;
import Library.Dto.java.DTOLibraryItems.Reservation;
import com.example.librarysoftware.ReservationSession;

import java.util.ArrayList;
import java.util.List;

public class ReservationHelper {
    public static List<Reservation> LoadReservations() throws MissingReservationsException {
        List<Reservation> reservations = new ArrayList<>();
        LibraryRepository lr = new LibraryRepository(new HibernateMain());
        reservations = (List<Reservation>)(Object) lr.GetListOfObject(QueryGenerator.GetListOfReservations());
        if(reservations == null){
            throw new MissingReservationsException();
        }

        return  reservations;

    }

    public static boolean AddReservation(Reservation res){
        LibraryRepository lr = new LibraryRepository(new HibernateMain());
        lr.AddObject(res);
        return true;
    }

    public static void DeleteReservation(Reservation res){
        LibraryRepository lr = new LibraryRepository(new HibernateMain());
        lr.DeleteObject(res);
    }

}
