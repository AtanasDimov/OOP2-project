package Library.Dto.java.DTOAccount;


import Library.Dto.java.Contracts.BorrowItemInterface;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@DiscriminatorValue("3")

public class ReaderAccount extends AccountBase implements BorrowItemInterface {
    private  UUID id;
    private final LocalDate firstRegistration;
    private String firstName;
    private String lastName;
    //list of borrowed books at the moment and in the past
    private List<BorrowItemInterface> borrowedItems;
    private double readerRating;

    public List<BorrowItemInterface> getBorrowedItems() {
        return borrowedItems;
    }

    public void setBorrowedItems(List<BorrowItemInterface> borrowedItems) {
        this.borrowedItems = borrowedItems;
    }

    public ReaderAccount(LocalDate firstRegistration, String firstName, String lastName) {
        this.id = UUID.randomUUID();
        this.firstRegistration = firstRegistration;
        this.firstName = firstName;
        this.lastName = lastName;
        this.borrowedItems = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getFirstRegistration() {
        return firstRegistration;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
