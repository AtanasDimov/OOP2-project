package Logger;

import ExceptionHandling.LibraryException;
import ExceptionHandling.SeverityCodes;

import java.time.LocalDate;

public class Logger {

    public void LogMessage(String message){
        System.out.println(LocalDate.now() + message);
    }
    public void LogException(LibraryException ex){
        LogMessage(ex.getMessage());
        switch (ex.getSeverityCode()){
            case Medium:{
                FileWriter fw = new FileWriter();
                fw.WriteToFile(ex.getMessage());
            }break;
            case Severe:{
                FileWriter fw = new FileWriter();
                fw.WriteToFile(ex.getMessage());
                EmailSender es = new EmailSender();
                es.SendEmails(ex.getMessage());
            }
        }
    }
}
