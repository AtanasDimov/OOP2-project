package DTOLibraryItems;

public class PaperMedia extends BaseLibraryItem {
    private int pageCount;

    public PaperMedia(String title, String description) {
        super(title, description);
    }

    public PaperMedia(String title, String description, int pageCount) {
        super(title, description);
        this.pageCount = pageCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
