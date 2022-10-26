package DTOLibraryItems;

import Contracts.LibraryItemInterface;

import java.time.LocalDate;

public class VisualMediaItem extends BaseLibraryItem {
    //list of actors and corresponding roles

    public String Tags;
    public VisualMediaItem(String title, String description, LocalDate publishDate) {
        super(title, description, publishDate);
    }
}
