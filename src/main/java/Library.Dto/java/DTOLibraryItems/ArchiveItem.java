package Library.Dto.java.DTOLibraryItems;


import Library.Dto.java.Contracts.LibraryItemInterface;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("1")
@DiscriminatorColumn(name="media_type",
        discriminatorType = DiscriminatorType.INTEGER)
public class ArchiveItem extends BaseLibraryItem implements LibraryItemInterface {
    public ArchiveItem(String title, String description, String itemGenre, LocalDate publishDate, List<Author> authors) {
        super(title, description, publishDate, authors);
    }
}
