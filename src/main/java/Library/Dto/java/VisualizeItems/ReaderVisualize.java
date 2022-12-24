package Library.Dto.java.VisualizeItems;

import Library.Dto.java.DTOAccount.ReaderAccount;
import Library.Dto.java.DTOLibraryItems.BaseLibraryItem;

import java.time.LocalDate;
import java.util.List;

public class ReaderVisualize {
    private LocalDate firstRegistration;
    private String firstName;
    private String lastName;
    private double readerRating;
    public List<BaseLibraryItem> readerItems;

    public ReaderVisualize(LocalDate firstRegistration, String firstName, String lastName, double readerRating, List<BaseLibraryItem> readerItems) {
        this.firstRegistration = firstRegistration;
        this.firstName = firstName;
        this.lastName = lastName;
        this.readerRating = readerRating;
        this.readerItems = readerItems;
    }

    public ReaderVisualize(ReaderAccount reader, List<BaseLibraryItem> readerItems){
        this.firstRegistration = reader.getFirstRegistration();
        this.firstName = reader.getFirstName();
        this.lastName = reader.getLastName();
        this.readerRating = reader.getReaderRating();
        this.readerItems = readerItems;
    }

    @Override
    public String toString() {
        return "ReaderVisualize{" +
                "firstRegistration=" + firstRegistration +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", readerRating=" + readerRating +
                ", readerItems=" + readerItems +
                '}';
    }
}