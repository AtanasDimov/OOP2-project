package Library.Dto.java.DTOLibraryItems;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("3")
public class MovieDirector extends Author {
    public MovieDirector(String name, String description) {
        super(name, description);
    }
}
