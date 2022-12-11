package Utils;

import Library.Dto.java.DTOLibraryItems.Reservation;
import Library.Dto.java.DTOLibraryItems.ReservationDueDates;
import Logger.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReservationFactory {
    public static Reservation CreateReservation(int itemId, int readerId, String borrowDate, ReservationDueDates dueDate){
        Reservation res = new Reservation();
        res.setItemId(itemId);
        res.setReaderId(readerId);
        try{
            SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
            Date bDate = DateFor.parse(borrowDate);
            res.setBorrowDate(bDate);

            switch (dueDate){
                case Long:{
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(bDate);
                    cal.add(Calendar.DATE, 3);
                    res.setDueDate(cal.getTime());
                }break;

                case Short:{
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(bDate);
                    cal.add(Calendar.DATE, 5);
                    res.setDueDate(cal.getTime());
                }break;

                default:break;
            }
        }
        catch(Exception ex){
            Logger log = new Logger();
            log.LogException(ex);
        }
    }
}
