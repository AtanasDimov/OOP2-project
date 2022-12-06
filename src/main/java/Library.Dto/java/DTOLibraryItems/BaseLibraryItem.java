package Library.Dto.java.DTOLibraryItems;


import Library.Dto.java.Contracts.LibraryItemInterface;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="item_type",
        discriminatorType = DiscriminatorType.INTEGER)
public class BaseLibraryItem implements LibraryItemInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private UUID readerID = null;
    private String title;
    private String description;
    private String itemGenre;
    private LocalDate publishDate;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Item_Author",
            joinColumns = { @JoinColumn(name = "item_id") },
            inverseJoinColumns = { @JoinColumn(name = "author_id") }
    )
    protected List<Author> author;
    public BaseLibraryItem(){}
    public BaseLibraryItem(String title, String description, LocalDate publishDate, List<Author> authors) {
        this.title = title;
        this.description = description;
        //this.itemGenre = itemGenre;
        this.publishDate = this.publishDate;
        author.addAll(authors);
    }

    public String getItemGenre() {
        return itemGenre;
    }

    public void setItemGenre(String itemGenre) {
        this.itemGenre = itemGenre;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        if(this.author == null)
            this.author = new ArrayList<>();
        this.author.add(author);
    }
}
