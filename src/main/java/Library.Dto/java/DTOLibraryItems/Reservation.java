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
    private String Type;

    public Reservation(int readerId, int itemId, Date borrowDate, ReservationTypes type) {
        ReaderId = readerId;
        ItemId = itemId;
        BorrowDate = borrowDate;
        Type = type.toString();
    }

    public Reservation(){

    }

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
    public int getReaderId(){return this.ReaderId;}

    public String getType() {
        return Type;
    }

    public void setType(String type){
        this.Type = type;
    }

    public int getItemId(){
        return this.ItemId;
    }
}
