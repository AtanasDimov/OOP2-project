package Library.Dto.java.DTOLibraryItems;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name="PaperMediaItem_ID")
public class BookItem extends PaperMediaItem {



    public BookItem(){}

    public BookItem(String title, String description, LocalDate publishDate, int quantity, int pageCount) {
        super(title, description, publishDate, quantity, pageCount);
    }

    public BookItem(String title, String description, LocalDate publishDate, List<Author> authors, int quantity, int pageCount) {
        super(title, description, publishDate, authors, quantity, pageCount);
    }
}
