package Library.Dto.java.DTOLibraryItems;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class AudioBookNarrator extends Author{

    public AudioBookNarrator(String name, String description) {
        super(name, description);
    }

    public AudioBookNarrator() {
    }
}
