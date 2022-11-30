package Library.Dto.java.DTOLibraryItems;



import Library.Dto.java.Contracts.BorrowItemInterface;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="media_type",
        discriminatorType = DiscriminatorType.INTEGER)
public class AudioMediaItem extends BaseLibraryItem implements BorrowItemInterface {
    private int runtime;

    public AudioMediaItem(String title, String description, LocalDate publishDate, int runtime) {
        super(title, description, publishDate);
        this.runtime = runtime;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }


}
