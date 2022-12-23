package Utils;

import Library.Dto.java.DTOLibraryItems.Reservation;
import Library.Dto.java.DTOLibraryItems.ReservationDueDates;
import Logger.Logger;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class ReservationFactory {
    public static Reservation CreateReservation(int itemId, int readerId, String dueDate, String typeOfReservation){
        Reservation res = new Reservation();
        res.setItemId(itemId);
        res.setReaderId(readerId);
        res.setType(typeOfReservation);

        try{
            Date borrowDate = Calendar.getInstance().getTime();
            res.setBorrowDate(borrowDate);

            switch (dueDate){
                case "Long":{
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(borrowDate);
                    cal.add(Calendar.DATE, 3);
                    res.setDueDate(cal.getTime());
                }break;

                case "Short":{
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(borrowDate);
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
        return res;
    }
}
