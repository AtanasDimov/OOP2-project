package Library.Dto.java.DTOLibraryItems;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name="AudioMediaItem_ID")
public class AudioBook extends AudioMediaItem{

    public AudioBook(){}
    public AudioBook(String title, String description, LocalDate publishDate, int quantity, int runtime) {
        super(title, description, publishDate, quantity, runtime);
    }

    public AudioBook(String title, String description, LocalDate publishDate, List<Author> authors, int quantity, int runtime) {
        super(title, description, publishDate, authors, quantity, runtime);
    }
}

