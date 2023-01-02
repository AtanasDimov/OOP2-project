package Hibernate.Control.Main.Repository;

import ExceptionHandling.LibraryException;
import ExceptionHandling.SeverityCodes;
import Hibernate.Control.Main.HibernateMain;
import Library.Dto.java.DTOLibraryItems.*;
import Logger.Logger;
import Utils.QueryGenerator;

import java.util.ArrayList;
import java.util.List;

public class AuthorRepository extends LibraryRepository{
    private Logger _logger;

    public AuthorRepository(HibernateMain hibernateManager) {
        super(hibernateManager);
        _logger = new Logger();
    }

    public Author GetEagerAuthor(int id){
        try{
            Author result = (Author) hibernateManager.GetObject(QueryGenerator.GetLoadLazyDataAuthorQuery(id));
            return result;
        }
        catch(Exception ex){
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
        }
        finally {
            hibernateManager.CloseSession();
        }
        return null;
    }

    public Author GetLazyDataAuthor(Author a){
        try{
            Author result = (Author) hibernateManager.GetObject(QueryGenerator.GetLoadLazyDataAuthorQuery(a.getId()));
            a = result;
            return result;
        }
        catch (Exception ex){
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
        }
        finally {
            hibernateManager.CloseSession();
        }
        return null;
    }

    public List<BookAuthor> GetBookAuthors(){
        try {
            List<BookAuthor> authors = new ArrayList<>();
            authors = (List<BookAuthor>) (Object) hibernateManager.GetListOfObject(QueryGenerator.GetBookAuthors());
            return authors;
        }
        catch(Exception ex){
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
        }
        return null;
    }

    public List<AudioBookNarrator> GetAudioBookNarrators(){
        try {
            List<AudioBookNarrator> authors = new ArrayList<>();
            authors = (List<AudioBookNarrator>) (Object) hibernateManager.GetListOfObject(QueryGenerator.GetNarrators());
            return authors;
        }
        catch(Exception ex){
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
        }
        return null;
    }

    public List<MovieDirector> GetMovieDirectors(){
        try {
            List<MovieDirector> authors = new ArrayList<>();
            authors = (List<MovieDirector>) (Object) hibernateManager.GetListOfObject(QueryGenerator.GetMovieDirectors());
            return authors;
        }
        catch(Exception ex){
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
        }
        return null;
    }

    public List<MusicArtist> GetMusicArtists(){
        try {
            List<MusicArtist> authors = new ArrayList<>();
            authors = (List<MusicArtist>) (Object) hibernateManager.GetListOfObject(QueryGenerator.GetMusicArtists());
            return authors;
        }
        catch (Exception ex){
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
        }
        return null;
    }
}
