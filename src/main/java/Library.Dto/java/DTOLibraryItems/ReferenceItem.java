package Library.Dto.java.DTOLibraryItems;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;
@Entity
@DiscriminatorValue("1")
public class ReferenceItem extends ArchiveItem{
    private ReferenceEnum type;

    public ReferenceItem(String title, String description, String itemGenre, LocalDate publishDate) {
        super(title, description, itemGenre, publishDate);
    }

    public ReferenceEnum getType() {
        return type;
    }

    public void setType(ReferenceEnum type) {
        this.type = type;
    }
}
