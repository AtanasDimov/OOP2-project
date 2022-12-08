package Utils;

import ExceptionHandling.LibraryException;
import ExceptionHandling.SeverityCodes;
import Hibernate.Control.Main.HibernateMain;
import Hibernate.Control.Main.Repository.LibraryRepository;
import Library.Dto.java.DTOAccount.AccountBase;
import Library.Dto.java.DTOAccount.OperatorAccount;
import Library.Dto.java.DTOAccount.ReaderAccount;
import Logger.Logger;

import java.time.LocalDate;

public class AccountHelper {
    public static boolean CheckIfExists(String username, String password){
        String hashedPass = "";
        try{
            //byte[] hash = PasswordHasher.HashPassword(password);
            hashedPass = PasswordHasher.HashPassword(password);
        }catch(Exception ex){
            Logger log = new Logger();
            LibraryException lEx = new LibraryException("Password hashing not working: " + ex.getMessage(), SeverityCodes.Severe);
            log.LogException(lEx);
        }

        LibraryRepository lr = new LibraryRepository(new HibernateMain());
        AccountBase account = (AccountBase) lr.GetObject(QueryGenerator.GetLoginQuery(username, hashedPass));

        if(account == null){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean RegisterOperator(String username, String password){
        if(CheckIfExists(username, password)){
            Logger log = new Logger();
            LibraryException lEx = new LibraryException("Operator user with these credentials already exists", SeverityCodes.Light);
            log.LogException(lEx);
            return false;
        }

        String hashedPass = "";
        try{
            //byte[] hash = PasswordHasher.HashPassword(password);
            hashedPass = PasswordHasher.HashPassword(password);
        }catch(Exception ex){
            Logger log = new Logger();
            LibraryException lEx = new LibraryException("Password hashing not working: " + ex.getMessage(), SeverityCodes.Severe);
            log.LogException(lEx);
        }

        OperatorAccount operator = new OperatorAccount(username, hashedPass);

        LibraryRepository lr = new LibraryRepository(new HibernateMain());
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

        String hashedPass = "";
        try{
            //byte[] hash = PasswordHasher.HashPassword(password);
            hashedPass = PasswordHasher.HashPassword(password);
        }catch(Exception ex){
            Logger log = new Logger();
            LibraryException lEx = new LibraryException("Password hashing not working: " + ex.getMessage(), SeverityCodes.Severe);
            log.LogException(lEx);
        }

        //refactor, add password, generate username
        ReaderAccount reader = new ReaderAccount(LocalDate.now(), firstName, lastName);

        LibraryRepository lr = new LibraryRepository(new HibernateMain());
        lr.AddObject(reader);

        return true;
    }
}
