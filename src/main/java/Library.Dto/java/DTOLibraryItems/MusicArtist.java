package Library.Dto.java.DTOLibraryItems;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("4")
public class MusicArtist extends Author{
    public MusicArtist(String[] name) {
        super(name);
    }
}
