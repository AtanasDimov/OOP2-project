package Library.Dto.java.DTOLibraryItems;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("1")
public class Movies extends VisualMediaItem{
    private MoviesAgeRating rating;

    public Movies(String title, String description, LocalDate publishDate, List<Author> authors, int runtime, String videoQuality, MoviesAgeRating rating) {
        super(title, description, publishDate, authors, runtime, videoQuality);
        this.rating = rating;
    }

    public MoviesAgeRating getRating() {
        return rating;
    }

    public void setRating(MoviesAgeRating rating) {
        this.rating = rating;
    }
}
