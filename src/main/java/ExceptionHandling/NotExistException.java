package ExceptionHandling;

public class NotExistException extends Exception{
    public NotExistException(String username, String password){
        super("User with username: " + username + " does not exist");
    }
}
