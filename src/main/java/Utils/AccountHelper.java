package Utils;

import ExceptionHandling.*;
import Hibernate.Control.Main.HibernateMain;
import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.DTOAccount.AccountBase;
import Library.Dto.java.DTOAccount.OperatorAccount;
import Library.Dto.java.DTOAccount.ReaderAccount;
import Logger.Logger;
import com.example.librarysoftware.UserSession;

import javax.jws.soap.SOAPBinding;
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

        return true;
    }
    public static boolean RegisterReader(String firstName, String lastName, String password){
        //pseudo username for now
        String username = firstName + lastName;

        if(CheckIfExists(username, password)){
            Logger log = new Logger();
            LibraryException lEx = new LibraryException("User with these credentials already exists", SeverityCodes.Light);
            log.LogException(lEx);
            return false;
        }

        String hashedPass = HashPassword(password);

        //refactor, add password, generate username
        ReaderAccount reader = new ReaderAccount(LocalDate.now(), firstName, lastName);

        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        lr.AddObject(reader);

        return true;
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
