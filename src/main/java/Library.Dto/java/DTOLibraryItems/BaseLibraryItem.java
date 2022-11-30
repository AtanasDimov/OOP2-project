package Library.Dto.java.DTOLibraryItems;


import Library.Dto.java.Contracts.LibraryItemInterface;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
@MappedSuperclass
public abstract class BaseLibraryItem implements LibraryItemInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private UUID readerID = null;
    private String title;
    private String description;
    private String itemGenre;
    private LocalDate publishDate;

    public BaseLibraryItem(){}
    public BaseLibraryItem(String title, String description, LocalDate publishDate) {
        this.title = title;
        this.description = description;
        this.itemGenre = itemGenre;
        this.publishDate = this.publishDate;
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
}
