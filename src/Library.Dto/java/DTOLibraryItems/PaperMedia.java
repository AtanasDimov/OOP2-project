package DTOLibraryItems;

import java.time.LocalDate;

public class PaperMedia extends BaseLibraryItem {
    private int pageCount;

    public PaperMedia(String title, String description, LocalDate publishDate) {
        super(title, description, publishDate);
    }

    public PaperMedia(String title, String description, LocalDate publishDate, int pageCount) {
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
