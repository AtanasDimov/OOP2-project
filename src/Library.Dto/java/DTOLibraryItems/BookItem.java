package DTOLibraryItems;

import java.time.LocalDate;
import java.util.List;

public class BookItem extends PaperMediaItem {
    private List<Author> authors;

    public BookItem(String title, String description, LocalDate publishDate, List<Author> authors) {
        super(title, description, publishDate);
        this.authors.addAll(authors);
    }

    public void addAutor(Author author){
        this.authors.add(author);
    }

    public List<Author> getAutors() {
        return authors;
    }

    public void setAutors(List<Author> authors) {
        this.authors = authors;
    }
}
