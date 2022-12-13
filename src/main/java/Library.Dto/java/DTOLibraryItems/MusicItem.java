package Library.Dto.java.DTOLibraryItems;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name="AudioMediaItem_ID")
public class MusicItem extends AudioMediaItem{
    private String album;

    public MusicItem(){}
    public MusicItem(String title, String description, LocalDate publishDate, int quantity, int runtime, String album) {
        super(title, description, publishDate, quantity, runtime);
        this.album = album;
    }

    public MusicItem(String title, String description, LocalDate publishDate, List<Author> authors, int quantity, int runtime, String album) {
        super(title, description, publishDate, authors, quantity, runtime);
        this.album = album;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
