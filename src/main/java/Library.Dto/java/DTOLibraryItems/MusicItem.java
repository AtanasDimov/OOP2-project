package Library.Dto.java.DTOLibraryItems;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("2")
public class MusicItem extends AudioMediaItem{
    private String album;

    public MusicItem(String title, String description, LocalDate publishDate, int runtime, List<Author> artist, String album) {
        super(title, description, publishDate, runtime, artist);
        this.album = album;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
