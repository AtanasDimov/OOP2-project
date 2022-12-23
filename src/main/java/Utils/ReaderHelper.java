package Utils;

import ExceptionHandling.LibraryException;
import ExceptionHandling.NotLoggedException;
import ExceptionHandling.SeverityCodes;
import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.Alert.Alert;
import Library.Dto.java.Alert.AlertFactory;
import Library.Dto.java.Alert.AlertSeverity;
import Library.Dto.java.DTOAccount.AccountBase;
import Library.Dto.java.DTOAccount.ReaderAccount;
import Library.Dto.java.DTOLibraryItems.BorrowForm;
import Library.Dto.java.DTOLibraryItems.Reservation;
import Logger.Logger;
import com.example.librarysoftware.UserSession;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;


public class ReaderHelper {
    //Method that updates the reader rating, deletes reservation and makes an alert
    public static void ReturnItem(int itemId){

        ReaderAccount reader = new ReaderAccount();
        try{
            reader = (ReaderAccount) UserSession.getInstance();
        }
        catch (Exception ex){
            Logger logger = new Logger();
            logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Severe));
        }

        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        //retrieving the reservation
        Reservation res = (Reservation) lr.GetObject(QueryGenerator.GetReservationByReaderId(reader.getAccountId()));

        //calculating the new rating of the reader
        reader.setReaderRating(CalculateRating(res.getDueDate()));

        //Adding alert message
        String alertMessage = "Reader " + reader.getUsername() + " returned borrowed item with id: " + res.getItemId();
        Alert alert = AlertFactory.CreateAlert(alertMessage, AlertSeverity.FinishedReservation);

        //Updating the reader
        lr.UpdateObject(reader);
        //Adding the alert
        lr.AddObject(alert);

        lr.CloseSession();

        //Deleting the reservation
        ReservationHelper.DeleteReservation(res);
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

    private static int CalculateRating(Date dueDate){
        long daysAfterDueDate = ChronoUnit.DAYS.between(dueDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate(), LocalDate.now());

        if(daysAfterDueDate > 1 && daysAfterDueDate < 3){
            return 2;
        }
        else if(daysAfterDueDate >= 3){
            return 1;
        }
        else return 3;
    }
}
