package Library.Dto.java.DTOLibraryItems;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("1")
public class AudioBook extends AudioMediaItem{
    private AudioBookNarrator narrator;

    public AudioBook(String title, String description, LocalDate publishDate, int runtime, AudioBookNarrator narrator) {
        super(title, description, publishDate, runtime);
        this.narrator = narrator;
    }

    public AudioBookNarrator getNarrator() {
        return narrator;
    }

    public void setNarrator(AudioBookNarrator narrator) {
        this.narrator = narrator;
    }
}

