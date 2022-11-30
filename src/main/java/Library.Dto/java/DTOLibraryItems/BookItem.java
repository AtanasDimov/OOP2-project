package Library.Dto.java.DTOLibraryItems;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("1")
public class BookItem extends PaperMediaItem {

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Book_Autor",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "author_id") }
    )
    private List<BookAuthor> author;

    public BookItem(){}
    public BookItem(String title, String description, LocalDate publishDate, int pageCount){
        super(title, description, publishDate, pageCount);
        author = new ArrayList<>();
    }

    public BookItem(String title, String description, LocalDate publishDate, int pageCount, List<BookAuthor> аuthor) {
        super(title, description, publishDate, pageCount);
        this.author = аuthor;
    }

    public List<BookAuthor> getАuthor() {
        return author;
    }

    public void setАuthor(BookAuthor аuthor) {
        this.author.add(аuthor);
    }
}
