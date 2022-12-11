package Library.Dto.java.DTOLibraryItems;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name="AudioMediaItem_ID")
public class AudioBook extends AudioMediaItem{

    public AudioBook(String title, String description, LocalDate publishDate, int runtime, List<Author> authors) {
        super(title, description, publishDate, runtime, authors);
    }

}

