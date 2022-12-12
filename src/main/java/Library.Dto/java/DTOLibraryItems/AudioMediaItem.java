package Library.Dto.java.DTOLibraryItems;



import Library.Dto.java.Contracts.BorrowItemInterface;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class AudioMediaItem extends BaseLibraryItem implements BorrowItemInterface {
    private int runtime;

    public AudioMediaItem(String title, String description, LocalDate publishDate, List<Author> authors, int quantity, int runtime) {
        super(title, description, publishDate, authors, quantity);
        this.runtime = runtime;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }


}
