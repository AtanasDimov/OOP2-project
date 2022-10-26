package DTOLibraryItems;

import Contracts.LibraryItemInterface;

import java.time.LocalDate;
import java.util.List;

public class BookItem extends PaperMedia {
    private List<Autor> autors;

    public BookItem(String title, String description, LocalDate publishDate, List<Autor> autors) {
        super(title, description, publishDate);
        this.autors.addAll(autors);
    }

    public void addAutor(Autor autor){
        this.autors.add(autor);
    }

    public List<Autor> getAutors() {
        return autors;
    }

    public void setAutors(List<Autor> autors) {
        this.autors = autors;
    }
}
