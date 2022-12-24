package ExceptionHandling;

public class NotReaderException extends Exception{
    public NotReaderException() {
        super("Logged in user is not reader!");
    }
}
