package Library.Dto.java.DTOLibraryItems;


import Library.Dto.java.Contracts.LibraryItemInterface;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class ArchiveItem extends BaseLibraryItem implements LibraryItemInterface {
    public ArchiveItem(String title, String description, String itemGenre, LocalDate publishDate, List<Author> authors) {
        super(title, description, publishDate, authors);
    }

    public ArchiveItem(){}
}
