package ExceptionHandling;

public class MissingReservationsException extends Exception{
    public MissingReservationsException(){
        super("No reservations found!");
    }
}
