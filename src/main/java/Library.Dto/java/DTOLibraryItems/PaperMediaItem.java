package Library.Dto.java.DTOLibraryItems;


import Library.Dto.java.Contracts.LibraryItemInterface;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class PaperMediaItem extends BaseLibraryItem implements LibraryItemInterface {
    private int pageCount;

    public PaperMediaItem(){}

    public PaperMediaItem(String title, String description, LocalDate publishDate, int pageCount, List<Author> author) {
        super(title, description, publishDate, author);
        this.pageCount = pageCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
