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

    public Date getDueDate(){
        return this.DueDate;
    }

    public int getId(){
        return this.Id;
    }
}
