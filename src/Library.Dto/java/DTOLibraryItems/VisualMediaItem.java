package DTOLibraryItems;

import Contracts.LibraryItemInterface;

import java.time.LocalDate;

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
