package DTOLibraryItems;

import Contracts.BorrowItemInterface;

public class AudioMediaItem extends BaseLibraryItem implements BorrowItemInterface {
    public AudioMediaItem(String title, String description) {
        super(title, description);
    }
}
