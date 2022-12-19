package Utils;

import ExceptionHandling.AlreadyLoggedException;
import ExceptionHandling.LibraryException;
import ExceptionHandling.NotExistException;
import ExceptionHandling.SeverityCodes;
import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.Alert.Alert;
import Library.Dto.java.Alert.AlertFactory;
import Library.Dto.java.Alert.AlertSeverity;
import Library.Dto.java.DTOAccount.AccountBase;
import Library.Dto.java.DTOAccount.OperatorAccount;
import Library.Dto.java.DTOAccount.ReaderAccount;
import Library.Dto.java.Form.Form;
import Library.Dto.java.Form.FormFactory;
import Logger.Logger;
import com.example.librarysoftware.UserSession;

import java.time.LocalDate;

public class AccountHelper {
    private static boolean CheckIfExists(String username, String password){
        String hashedPass = HashPassword(password);

        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        AccountBase account = (AccountBase) lr.GetObject(QueryGenerator.GetLoginQuery(username, hashedPass));

        if(account == null){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean LogInUser(String username, String password)throws NotExistException, AlreadyLoggedException{
        if(!UserSession.isLoggedIn()){
            if(CheckIfExists(username, password)){
                String hashedPass = HashPassword(password);

                LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
                AccountBase account = (AccountBase) lr.GetObject(QueryGenerator.GetLoginQuery(username, hashedPass));

                return UserSession.logIn(account);
            }
            else{
                throw new NotExistException(username, password);
            }
        }
        else {
            throw new AlreadyLoggedException();
        }
    }

    public static boolean RegisterOperator(String username, String password){
        if(CheckIfExists(username, password)){
            Logger log = new Logger();
            LibraryException lEx = new LibraryException("Operator user with these credentials already exists", SeverityCodes.Light);
            log.LogException(lEx);
            return false;
        }

        String hashedPass = HashPassword(password);

        OperatorAccount operator = new OperatorAccount(username, hashedPass);

        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        lr.AddObject(operator);
        lr.CloseSession();

        return true;
    }
    public static boolean RegisterReaderForm(String firstName, String lastName, String username, String password){
        if(CheckIfExists(username, password)){
            Logger log = new Logger();
            LibraryException lEx = new LibraryException("User with these credentials already exists", SeverityCodes.Light);
            log.LogException(lEx);
            return false;
        }

        String hashedPass = HashPassword(password);

        Form form = FormFactory.CreateForm(firstName, lastName, username, hashedPass);
        Alert alert = AlertFactory.CreateAlert("New user form for registry", AlertSeverity.NewReader);

        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        lr.AddObject(form);
        lr.AddObject(alert);

        lr.CloseSession();

        return true;
    }

    //After the user registry form has been accepted
    public static void RegisterReader(ReaderAccount reader){
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        lr.AddObject(reader);
    }

    private static void UnsignReader(int id){
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        ReaderAccount reader = (ReaderAccount) lr.GetObject(QueryGenerator.GetReaderById(id));
        lr.DeleteObject(reader);
        lr.CloseSession();
    }

    private static String HashPassword(String password){
        String hashedPass = "";
        try{
            //byte[] hash = PasswordHasher.HashPassword(password);
            hashedPass = PasswordHasher.HashPassword(password);
        }catch(Exception ex){
            Logger log = new Logger();
            LibraryException lEx = new LibraryException("Password hashing not working: " + ex.getMessage(), SeverityCodes.Severe);
            log.LogException(lEx);
        }
        return hashedPass;
    }
}
