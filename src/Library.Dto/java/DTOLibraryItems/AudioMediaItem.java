package DTOLibraryItems;

import Contracts.BorrowItemInterface;

import java.time.LocalDate;

public class AudioMediaItem extends BaseLibraryItem implements BorrowItemInterface {
    public AudioMediaItem(String title, String description, LocalDate publishDate) {
        super(title, description, publishDate);
    }
}
