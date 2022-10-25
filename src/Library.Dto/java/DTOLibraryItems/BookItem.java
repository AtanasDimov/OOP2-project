package DTOLibraryItems;

import Contracts.LibraryItemInterface;

public class BookItem extends PaperMedia implements LibraryItemInterface {
    public BookItem(String title, String description) {
        super(title, description);
    }

}
