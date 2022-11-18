package DTOLibraryItems;

import Contracts.LibraryItemInterface;

import java.time.LocalDate;

public class PaperMediaItem extends BaseLibraryItem implements LibraryItemInterface {
    private int pageCount;

    public PaperMediaItem(String title, String description, LocalDate publishDate, int pageCount) {
        super(title, description, publishDate);
        this.pageCount = pageCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
