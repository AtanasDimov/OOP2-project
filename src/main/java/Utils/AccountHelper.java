package Utils;

import ExceptionHandling.LibraryException;
import ExceptionHandling.SeverityCodes;
import Hibernate.Control.Main.HibernateMain;
import Hibernate.Control.Main.Repository.LibraryRepository;
import Library.Dto.java.DTOAccount.AccountBase;
import Logger.Logger;

public class AccountHelper {
    public static boolean CheckIfExists(String username, String password){
        String hashedPass = "";
        try{
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
}
