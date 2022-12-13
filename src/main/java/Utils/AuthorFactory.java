package Utils;

import Library.Dto.java.DTOLibraryItems.AudioBookNarrator;
import Library.Dto.java.DTOLibraryItems.BookAuthor;
import Library.Dto.java.DTOLibraryItems.MovieDirector;

public class AuthorFactory {
    public static BookAuthor CreateBookAuthor(String name, String description){
        return new BookAuthor(name, description);
    }

    public static MovieDirector CreateMovieDirector(String name, String description){
        return new MovieDirector(name, description);
    }

    public static AudioBookNarrator CreateAudioBookNarrator(String name, String description){
        return new AudioBookNarrator(name, description);
    }
}
