package Library.Dto.java.Alert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDate;

@Entity
public class Alert {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String Message;
    private LocalDate DateOfAlert;
    private String Severity;

    public Alert(String message, AlertSeverity severity) {
        Message = message;
        DateOfAlert = LocalDate.now();
        Severity = severity.toString();
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

}
