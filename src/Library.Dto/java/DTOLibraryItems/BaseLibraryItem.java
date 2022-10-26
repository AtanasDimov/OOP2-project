package DTOLibraryItems;

import Contracts.LibraryItemInterface;

import java.time.LocalDate;
import java.util.UUID;

public class BaseLibraryItem implements LibraryItemInterface {
    private UUID id;
    private UUID readerID = null;
    private String title;
    private String description;
    private LocalDate publishDate;

    public BaseLibraryItem(String title, String description, LocalDate publishDate) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.publishDate = publishDate;
    }

    public UUID getId() {
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

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
}
