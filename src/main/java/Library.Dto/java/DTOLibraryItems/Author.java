package Library.Dto.java.DTOLibraryItems;



import Library.Dto.java.Contracts.AuthorInterface;
import Library.Dto.java.Contracts.LibraryItemInterface;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="autor_type",
        discriminatorType = DiscriminatorType.INTEGER)
public abstract class Author implements AuthorInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String[] name;
    private String description;
    @ManyToMany(mappedBy = "author")
    private List<BookItem> work = new ArrayList<>();

    public Author(){}
    public Author(String[] name) {
        this.name = name;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addWork(BookItem w)
    {
        this.work.add(w);
    }

    public void addWork(List<BookItem> w){
        this.work.addAll(w);
    }

    public int getId(){
        return this.id;
    }
    public void lazyLoad(List<BookItem> w){
        this.work = w;
    }

    public List<BookItem> getWork(){
        return this.work;
    }
}
