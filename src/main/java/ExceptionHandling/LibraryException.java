package ExceptionHandling;

public class LibraryException extends Exception{
    private SeverityCodes SeverityCode;

    public LibraryException(String message, SeverityCodes code){
        super(message);
        SeverityCode = code;
    }

    public SeverityCodes getSeverityCode() {
        return SeverityCode;
    }

    public void setSeverityCode(SeverityCodes severityCode) {
        SeverityCode = severityCode;
    }
}
