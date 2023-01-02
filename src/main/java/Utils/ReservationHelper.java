package Utils;

import ExceptionHandling.MissingReservationsException;
import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.DTOLibraryItems.BorrowForm;
import Library.Dto.java.DTOLibraryItems.Reservation;
import Sessions.ReservationSession;

import java.util.ArrayList;
import java.util.List;

public class ReservationHelper {
    public static List<Reservation> LoadReservations() throws MissingReservationsException {
        List<Reservation> reservations = new ArrayList<>();
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        reservations = (List<Reservation>)(Object) lr.GetListOfObject(QueryGenerator.GetListOfReservations());
        if(reservations == null){
            throw new MissingReservationsException();
        }

        lr.CloseSession();
        return  reservations;
    }

    public static boolean AddReservation(int borrowId, String dueDate, String typeOfReservation){
        //to add - quantity update
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        BorrowForm form = (BorrowForm) lr.GetObject(QueryGenerator.GetBorrowFormById(borrowId));
        lr.CloseSession();

        int itemId = form.getItemId();
        int readerId = form.getReaderId();
        Reservation res = ReservationFactory.CreateReservation(itemId, readerId, dueDate, typeOfReservation);
        lr = RepositoryFactory.CreateLibraryRepository();

        //Delete the borrow form
        lr.DeleteObject(form);
        lr.CloseSession();
        lr = RepositoryFactory.CreateLibraryRepository();
        //Add the new reservation
        lr.AddObject(res);
        lr.CloseSession();

        //Update the item's count
        ItemHelper.GiveItem(itemId);

        //Add the new reservation to the session
        ReservationSession.AddReservation(res);



        return true;
    }

    public static void CancelReservation(int borrowFormId){
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        BorrowForm form = (BorrowForm) lr.GetObject(QueryGenerator.GetBorrowFormById(borrowFormId));

        lr.DeleteObject(form);

        lr.CloseSession();
    }

    public static void DeleteReservation(Reservation res){
        //to add - quantity update
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        lr.DeleteObject(res);
        lr.CloseSession();
        ItemHelper.ReturnItem(res.getItemId());
    }

    public static void DeleteReservationsForReader(int id){
        LibraryRepository repository = RepositoryFactory.CreateLibraryRepository();
        List<Reservation> readerReservations = (List<Reservation>)(Object)repository.GetListOfObject(QueryGenerator.GetReservationByReaderId(id));
        repository.CloseSession();

        for(Reservation res: readerReservations){
            DeleteReservation(res);
        }
    }

}
