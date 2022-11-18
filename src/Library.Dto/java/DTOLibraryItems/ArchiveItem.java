package DTOLibraryItems;

import Contracts.LibraryItemInterface;

import java.time.LocalDate;

public class ArchiveItem extends BaseLibraryItem implements LibraryItemInterface {
    public ArchiveItem(String title, String description, String itemGenre, LocalDate publishDate) {
        super(title, description, publishDate);
    }
}
