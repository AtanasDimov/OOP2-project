package Utils;

import Library.Dto.java.DTOLibraryItems.Reservation;
import java.text.SimpleDateFormat;

public class ReservationFactory {
    public static Reservation CreateReservation(int itemId, int readerId, String borrowDate, String dueDate){
        Reservation res = new Reservation();
        res.setItemId(itemId);
        res.setReaderId(readerId);
        try{
            SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
            res.setBorrowDate(DateFor.parse(borrowDate));
            res.setDueDate(DateFor.parse(dueDate));
        }
        catch(Exception ex){

        }
    }
}
