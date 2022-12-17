package Library.Dto.java.DTOLibraryItems;


import Library.Dto.java.Contracts.LibraryItemInterface;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class BaseLibraryItem implements LibraryItemInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private UUID readerID = null;
    private String title;
    private String description;

    private LocalDate publishDate;

    private int quantity = 0;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Item_Author",
            joinColumns = { @JoinColumn(name = "item_id") },
            inverseJoinColumns = { @JoinColumn(name = "author_id") }
    )
    protected List<Author> author;
    public BaseLibraryItem(){}

    public BaseLibraryItem(String title, String description, LocalDate publishDate, int quantity) {
        this.title = title;
        this.description = description;
        this.publishDate = publishDate;
        this.quantity = quantity;
    }
    public BaseLibraryItem(String title, String description, LocalDate publishDate, List<Author> authors, int quantity) {
        this.title = title;
        this.description = description;
        this.publishDate = publishDate;
        author.addAll(authors);
        this.quantity = quantity;
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

    public void addAuthor(Author author){

        if (this.author == null){
            this.author = new ArrayList<>();
        }
        this.author.add(author);
    }

    public void addAuthorRange(List<Author> authors){
        if (this.author == null){
            this.author = new ArrayList<>();
        }
        this.author.addAll(authors);
    }

    public void setAuthor(Author author) {
        if(this.author == null)
            this.author = new ArrayList<>();
        this.author.add(author);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void returnItem(){
        this.quantity++;
    }

    public void giveItem(){
        this.quantity--;
    }
}
