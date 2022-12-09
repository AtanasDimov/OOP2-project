package ExceptionHandling;

public class AlreadyLoggedException extends Exception{
    public AlreadyLoggedException(){
        super("User already logged!");
    }
}
