package Utils;

import Library.Dto.java.DTOLibraryItems.*;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;

public class ItemFactory {
    public static BookItem CreateBookItem(String title, String description, LocalDate publishDate, int quantity, int pageCount){
        return new BookItem(title, description, publishDate, quantity, pageCount);
    }

    public static Movies CreateMovie(String title, String description, LocalDate publishDate, int quantity, int runtime, String videoQuality, String rating){
            MoviesAgeRating movieRating=MoviesAgeRating.PG;
        switch (rating){
            case "PG": movieRating = MoviesAgeRating.PG;break;
            case "R": movieRating = MoviesAgeRating.R;break;
            case "G": movieRating = MoviesAgeRating.G;break;
            case "PG13": movieRating = MoviesAgeRating.PG13;break;
            case "NC17": movieRating = MoviesAgeRating.NC17;break;
            default:break;
        }
        return new Movies(title, description, publishDate, quantity, runtime, videoQuality, movieRating);
    }

    public static AudioBook CreateAudioBook(String title, String description, LocalDate publishDate, int quantity, int runtime){
        return new AudioBook(title, description, publishDate, quantity, runtime);
    }

    public static MusicItem CreateMusicItem(String title, String description, LocalDate publishDate, int quantity, int runtime, String album){
        return new MusicItem(title, description, publishDate, quantity, runtime, album);
    }
}