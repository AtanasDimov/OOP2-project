package DTOLibraryItems;

import Contracts.LibraryItemInterface;

public class VisualMediaItem extends BaseLibraryItem implements LibraryItemInterface {
    //list of actors and corresponding roles

    public String Tags;
    public VisualMediaItem(String title, String description) {
        super(title, description);
    }
}
