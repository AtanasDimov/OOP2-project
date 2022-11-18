package Library.Dto.java.DTOLibraryItems;


import java.time.LocalDate;

public class BookItem extends PaperMediaItem {
    private BookAuthor author;

    public BookItem(String title, String description, LocalDate publishDate, int pageCount, BookAuthor аuthor) {
        super(title, description, publishDate, pageCount);
        this.author = аuthor;
    }

    public BookAuthor getАuthor() {
        return author;
    }

    public void setАuthor(BookAuthor аuthor) {
        this.author = аuthor;
    }
}
