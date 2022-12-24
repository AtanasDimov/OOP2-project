package Library.Dto.java.DTOLibraryItems;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
public class ScrappedItem extends BaseLibraryItem{
    public ScrappedItem(String title, String description, LocalDate publishDate, List<Author> authors, int quantity) {
        super(title, description, publishDate, authors, quantity);
    }

    public ScrappedItem(){}
}
