package Library.Dto.java.DTOLibraryItems;


import Library.Dto.java.Contracts.LibraryItemInterface;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class ArchiveItem extends BaseLibraryItem implements LibraryItemInterface {
    public ArchiveItem(String title, String description, LocalDate publishDate, List<Author> authors, int quantity) {
        super(title, description, publishDate, authors, quantity);
    }

    public ArchiveItem(){}
}
