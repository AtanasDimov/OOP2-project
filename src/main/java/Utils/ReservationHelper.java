package Utils;

import Hibernate.Control.Main.HibernateMain;
import Hibernate.Control.Main.Repository.LibraryRepository;
import Library.Dto.java.DTOLibraryItems.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationHelper {
    public static void LoadReservations(){
        List<Reservation> reservations = new ArrayList<>();
        LibraryRepository lr = new LibraryRepository(new HibernateMain());
        reservations = (List<Reservation>)(Object) lr.GetListOfObject(QueryGenerator.GetListOfReservations());
        if(reservations == null){
            //throw exception
        }
        else{

        }
    }
}
