package Hibernate.Control.Main.Repository;

import Hibernate.Control.Main.HibernateMain;
import Library.Dto.java.DTOLibraryItems.*;
import Utils.QueryGenerator;

import java.util.ArrayList;
import java.util.List;

public class AuthorRepository extends LibraryRepository{

    public AuthorRepository(HibernateMain hibernateManager) {
        super(hibernateManager);
    }

    public Author GetEagerAuthor(int id){
        Author result = (Author) hibernateManager.GetObject(QueryGenerator.GetLoadLazyDataAuthorQuery(id));
        hibernateManager.CloseSession();
        return result;
    }

    public Author GetLazyDataAuthor(Author a){
        Author result = (Author) hibernateManager.GetObject(QueryGenerator.GetLoadLazyDataAuthorQuery(a.getId()));
        a = result;
        hibernateManager.CloseSession();
        return result;
    }

    public List<BookAuthor> GetBookAuthors(){
        List<BookAuthor> authors = new ArrayList<>();
        authors = (List<BookAuthor>) (Object) hibernateManager.GetListOfObject(QueryGenerator.GetBookAuthors());
        return authors;
    }

    public List<AudioBookNarrator> GetAudioBookNarrators(){
        List<AudioBookNarrator> authors = new ArrayList<>();
        authors = (List<AudioBookNarrator>) (Object) hibernateManager.GetListOfObject(QueryGenerator.GetNarrators());
        return authors;
    }

    public List<MovieDirector> GetMovieDirectors(){
        List<MovieDirector> authors = new ArrayList<>();
        authors = (List<MovieDirector>) (Object) hibernateManager.GetListOfObject(QueryGenerator.GetMovieDirectors());
        return authors;
    }

    public List<MusicArtist> GetMusicArtists(){
        List<MusicArtist> authors = new ArrayList<>();
        authors = (List<MusicArtist>) (Object) hibernateManager.GetListOfObject(QueryGenerator.GetMusicArtists());
        return authors;
    }
}
