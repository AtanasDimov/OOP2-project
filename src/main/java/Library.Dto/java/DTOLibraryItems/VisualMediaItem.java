package Library.Dto.java.DTOLibraryItems;

import Library.Dto.java.Contracts.LibraryItemInterface;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class VisualMediaItem extends BaseLibraryItem implements LibraryItemInterface {
    private int runtime;
    private String videoQuality;

    public VisualMediaItem(String title, String description, LocalDate publishDate, List<Author> authors, int runtime, String videoQuality) {
        super(title, description, publishDate, authors);
        this.runtime = runtime;
        this.videoQuality=videoQuality;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getVideoQuality() {
        return videoQuality;
    }

    public void setVideoQuality(String videoQuality) {
        this.videoQuality = videoQuality;
    }
}
