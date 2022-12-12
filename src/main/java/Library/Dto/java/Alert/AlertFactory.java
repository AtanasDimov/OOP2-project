package Library.Dto.java.Alert;

public class AlertFactory {
    public static Alert CreateAlert(String message, AlertSeverity severity){
        return new Alert(message, severity);
    }
}
