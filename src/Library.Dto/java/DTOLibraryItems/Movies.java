package DTOLibraryItems;

import java.time.LocalDate;

public class Movies extends VisualMediaItem{
    private MovieDirector director;
    private MoviesAgeRating rating;

    public Movies(String title, String description, LocalDate publishDate, int runtime, String videoQuality, MovieDirector director, MoviesAgeRating rating) {
        super(title, description, publishDate, runtime, videoQuality);
        this.director = director;
        this.rating = rating;
    }

    public Author getDirector() {
        return director;
    }

    public void setDirector(MovieDirector director) {
        this.director = director;
    }

    public MoviesAgeRating getRating() {
        return rating;
    }

    public void setRating(MoviesAgeRating rating) {
        this.rating = rating;
    }
}
