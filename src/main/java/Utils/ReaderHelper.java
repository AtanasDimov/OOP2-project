package Utils;

import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.DTOAccount.ReaderAccount;
import Library.Dto.java.DTOLibraryItems.BaseLibraryItem;
import Library.Dto.java.DTOLibraryItems.Reservation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;


public class ReaderHelper {
    public static void ReturnItem(int readerId, int resId){
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        Reservation res = (Reservation) lr.GetObject(QueryGenerator.GetReservationById(resId));
        ReaderAccount reader = (ReaderAccount) lr.GetObject(QueryGenerator.GetReaderById(readerId));

        long daysAfterDueDate = ChronoUnit.DAYS.between(res.getDueDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate(), LocalDate.now());

        if(daysAfterDueDate > 1 && daysAfterDueDate < 3){
            reader.setReaderRating(2);
        }
        else if(daysAfterDueDate >= 3){
            reader.setReaderRating(1);
        }
        else reader.setReaderRating(3);

        lr.UpdateObject(reader);

    }
}
