package DTOLibraryItems;

import Contracts.BorrowItemInterface;

import java.time.LocalDate;

public class AudioMediaItem extends BaseLibraryItem implements BorrowItemInterface {
    private int runtime;

    public AudioMediaItem(String title, String description, LocalDate publishDate, int runtime) {
        super(title, description, publishDate);
        this.runtime = runtime;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }


}
