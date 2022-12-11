package Library.Dto.java.DTOLibraryItems;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Entity

public class Reservation {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private int ReaderId;
    private int ItemId;
    private Date BorrowDate;
    private Date DueDate;

    public void setReaderId(int readerId) {
        ReaderId = readerId;
    }

    public void setItemId(int itemId) {
        ItemId = itemId;
    }

    public void setBorrowDate(Date borrowDate) {
        BorrowDate = borrowDate;
    }

    public void setDueDate(Date dueDate) {
        DueDate = dueDate;
    }

    public Date getDueDate(){
        return this.DueDate;
    }

    public int getId(){
        return this.Id;
    }
}
