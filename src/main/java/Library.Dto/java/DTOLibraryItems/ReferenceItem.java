package Library.Dto.java.DTOLibraryItems;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name="ArchiveItem_ID")
public class ReferenceItem extends ArchiveItem{
    private ReferenceEnum type;

    public ReferenceItem(String title, String description, LocalDate publishDate, List<Author> authors, int quantity, ReferenceEnum type) {
        super(title, description, publishDate, authors, quantity);
        this.type = type;
    }

    public ReferenceEnum getType() {
        return type;
    }

    public void setType(ReferenceEnum type) {
        this.type = type;
    }
}
