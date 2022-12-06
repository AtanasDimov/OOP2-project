package Library.Dto.java.DTOLibraryItems;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("1")
public class AudioBook extends AudioMediaItem{

    public AudioBook(String title, String description, LocalDate publishDate, int runtime, List<Author> authors) {
        super(title, description, publishDate, runtime, authors);
    }

}

