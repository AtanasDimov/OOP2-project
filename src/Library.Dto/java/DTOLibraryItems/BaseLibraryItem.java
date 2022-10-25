package DTOLibraryItems;

import Contracts.LibraryItemInterface;

import java.util.UUID;

public class BaseLibraryItem implements LibraryItemInterface {
    private UUID id;
    private UUID readerID = null;
    private String title;
    private String description;

    public BaseLibraryItem(String title, String description) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
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
}
