package Utils;

import ExceptionHandling.NotLoggedException;
import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.Alert.Alert;
import Library.Dto.java.Alert.AlertFactory;
import Library.Dto.java.Alert.AlertSeverity;
import Library.Dto.java.DTOAccount.AccountBase;
import Library.Dto.java.DTOAccount.ReaderAccount;
import Library.Dto.java.DTOLibraryItems.BaseLibraryItem;
import Library.Dto.java.DTOLibraryItems.BorrowForm;
import Library.Dto.java.DTOLibraryItems.Reservation;
import com.example.librarysoftware.UserSession;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;


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
        lr.CloseSession();
    }

    public static void BorrowItem(int itemId) throws NotLoggedException {
        BorrowForm form = new BorrowForm();
        AccountBase loggedInUser = new AccountBase();
        try{
            loggedInUser = UserSession.getInstance();
        }
        catch(Exception ex){
            throw ex;
        }
        int userId = loggedInUser.getAccountId();
        LocalDate date = LocalDate.now();

        form.setItemId(itemId);
        form.setReaderId(userId);
        form.setRequestDate(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));

        Alert alert = AlertFactory.CreateAlert("New borrow request!", AlertSeverity.NewReservation);

        LibraryRepository repository = RepositoryFactory.CreateLibraryRepository();
        repository.AddObject(form);
        repository.AddObject(alert);
        repository.CloseSession();
    }
}
