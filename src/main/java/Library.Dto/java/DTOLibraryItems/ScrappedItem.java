package Library.Dto.java.DTOLibraryItems;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
public class ScrappedItem extends BaseLibraryItem{
    int itemId;
    public ScrappedItem(String title, String description, LocalDate publishDate, List<Author> authors, int quantity, int itemId) {
        super(title, description, publishDate, authors, quantity);
        this.itemId = itemId;
    }

    public ScrappedItem(){}
}
