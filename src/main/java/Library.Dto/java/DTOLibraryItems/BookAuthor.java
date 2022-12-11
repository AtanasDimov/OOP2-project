package Library.Dto.java.DTOLibraryItems;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class BookAuthor extends Author{
    public BookAuthor(){}
    public BookAuthor(String[] name) {
        super(name);
    }
}
