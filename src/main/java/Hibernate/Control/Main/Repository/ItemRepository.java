package Hibernate.Control.Main.Repository;

import Hibernate.Control.Main.HibernateMain;
import Library.Dto.java.DTOLibraryItems.*;
import Utils.QueryGenerator;

import java.util.List;

public class ItemRepository extends LibraryRepository{

    public ItemRepository(HibernateMain hibernateManager){
        super(hibernateManager);
    }

    public BaseLibraryItem GetEagerItem(int id){
        BookItem result = (BookItem) hibernateManager.GetObject(QueryGenerator.GetLoadLazyDataBookItemQuery(id));
        hibernateManager.CloseSession();
        return result;
    }

    public BaseLibraryItem GetLazyDataItem(BookItem b){
        BookItem result = (BookItem) hibernateManager.GetObject(QueryGenerator.GetLoadLazyDataBookItemQuery(b.getId()));
        b = result;
        hibernateManager.CloseSession();
        return result;
    }

    public List<BookItem> GetBookItems(){
        List<BookItem> bookItems = (List<BookItem>) (Object) hibernateManager.GetListOfObject(QueryGenerator.GetBookItems());
        return bookItems;
    }

    public List<ArchiveItem> GetArchiveItems(){
        List<ArchiveItem> archiveItems = (List<ArchiveItem>) (Object) hibernateManager.GetListOfObject(QueryGenerator.GetArchiveItems());
        return archiveItems;
    }

    public List<AudioBook> GetAudioBooks(){
        List<AudioBook> audioBooks = (List<AudioBook>) (Object) hibernateManager.GetListOfObject(QueryGenerator.GetAudioBooks());
        return audioBooks;
    }

    public List<Movies> GetMovies(){
        List<Movies> movies = (List<Movies>) (Object) hibernateManager.GetListOfObject(QueryGenerator.GetMovies());
        return movies;
    }

    public List<MusicItem> GetMusicItems(){
        List<MusicItem> musicItems = (List<MusicItem>) (Object) hibernateManager.GetListOfObject(QueryGenerator.GetMusicItems());
        return musicItems;
    }
}
