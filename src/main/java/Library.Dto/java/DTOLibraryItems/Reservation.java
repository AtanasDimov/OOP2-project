package Library.Dto.java.DTOLibraryItems;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
@Entity

public class Reservation {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private int ReaderId;
    private int ItemId;
    private LocalDate BorrowDate;
    private LocalDate DueDate;
}
