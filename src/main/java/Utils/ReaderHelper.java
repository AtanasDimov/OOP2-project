package Utils;

import ExceptionHandling.LibraryException;
import ExceptionHandling.NotLoggedException;
import ExceptionHandling.NotReaderException;
import ExceptionHandling.SeverityCodes;
import Hibernate.Control.Main.Repository.ItemRepository;
import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.Alert.Alert;
import Library.Dto.java.Alert.AlertFactory;
import Library.Dto.java.Alert.AlertSeverity;
import Library.Dto.java.Contracts.LibraryItemInterface;
import Library.Dto.java.DTOAccount.AccountBase;
import Library.Dto.java.DTOAccount.ReaderAccount;
import Library.Dto.java.DTOLibraryItems.BaseLibraryItem;
import Library.Dto.java.DTOLibraryItems.BorrowForm;
import Library.Dto.java.DTOLibraryItems.Reservation;
import Library.Dto.java.VisualizeItems.BorrowedItemsVisualize;
import Logger.Logger;
import Sessions.UserSession;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class ReaderHelper {
    public static List<BorrowedItemsVisualize> GetReaderItems() throws NotReaderException {
        if(!UserSession.isReader()){
            throw new NotReaderException();
        }
        ItemRepository repository = RepositoryFactory.CreateItemRepository();
        int readerId = 0;
        try{
            readerId = UserSession.getInstance().getAccountId();
        }
        catch(Exception ex){
            Logger logger = new Logger();
            logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Severe));
        }
        List<Object> readerItems = (List<Object>) repository.GetListOfObject(QueryGenerator.GetReaderItems(readerId));

        List<BorrowedItemsVisualize> itemsVizualize = new ArrayList<>();

        repository.CloseSession();

        Iterator itr = readerItems.iterator();
        while(itr.hasNext()){
            Object[] obj = (Object[]) itr.next();
            repository = RepositoryFactory.CreateItemRepository();
            BaseLibraryItem item = (BaseLibraryItem) obj[0];
            Date borrowedDate = (Date) repository.GetObject(QueryGenerator.GetBorrowedDateForItem(item.getId(), readerId));
            Date dueDate = (Date) repository.GetObject(QueryGenerator.GetDueDateForItem(item.getId(), readerId));
            itemsVizualize.add(new BorrowedItemsVisualize(item.getId(),item.getTitle(), borrowedDate, dueDate));
            repository.CloseSession();
        }

        return itemsVizualize;
    }

    //Method that updates the reader rating, deletes reservation and makes an alert
    public static void ReturnItem(int itemId) throws NotReaderException {

        ReaderAccount reader = new ReaderAccount();
        if(!UserSession.isReader()){
            throw new NotReaderException();
        }
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
