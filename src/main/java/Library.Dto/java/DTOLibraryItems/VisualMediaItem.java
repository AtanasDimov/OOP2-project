package Library.Dto.java.DTOLibraryItems;

import Library.Dto.java.Contracts.LibraryItemInterface;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="media_type",
        discriminatorType = DiscriminatorType.INTEGER)
public class VisualMediaItem extends BaseLibraryItem implements LibraryItemInterface {
    private int runtime;
    private String videoQuality;

    public VisualMediaItem(String title, String description, LocalDate publishDate, int runtime,String videoQuality) {
        super(title, description, publishDate);
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
