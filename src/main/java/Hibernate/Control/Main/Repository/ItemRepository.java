package Hibernate.Control.Main.Repository;

import ExceptionHandling.LibraryException;
import ExceptionHandling.SeverityCodes;
import Hibernate.Control.Main.HibernateMain;
import Library.Dto.java.Contracts.LibraryItemInterface;
import Library.Dto.java.DTOLibraryItems.*;
import Logger.Logger;
import Utils.QueryGenerator;

import java.util.ArrayList;
import java.util.List;

public class ItemRepository extends LibraryRepository{
    private Logger _logger;

    public ItemRepository(HibernateMain hibernateManager){
        super(hibernateManager);
        _logger = new Logger();
    }

    public LibraryItemInterface GetEagerItem(int id){
        try{
            LibraryItemInterface result = (LibraryItemInterface) hibernateManager.GetObject(QueryGenerator.GetLoadLazyDataItemQuery(id));
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

    public void GetLazyDataItem(LibraryItemInterface b){
        try{
            LibraryItemInterface result = (LibraryItemInterface) hibernateManager.GetObject(QueryGenerator.GetLoadLazyDataItemQuery(b.getId()));
            b = result;
        }
        catch(Exception ex){
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
        }
        finally {
            hibernateManager.CloseSession();
        }
    }

    public List<BookItem> GetBookItems(){
        try{
            List<BookItem> bookItems = (List<BookItem>) (Object) hibernateManager.GetListOfObject(QueryGenerator.GetBookItems());
            return bookItems;
        }
        catch (Exception ex){
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
        }
        return null;
    }

    public List<ArchiveItem> GetArchiveItems(){
        try{
            List<ArchiveItem> archiveItems = (List<ArchiveItem>) (Object) hibernateManager.GetListOfObject(QueryGenerator.GetArchiveItems());
            return archiveItems;
        }
        catch(Exception ex){
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
        }
        return null;
    }

    public List<AudioBook> GetAudioBooks(){
        try{
            List<AudioBook> audioBooks = (List<AudioBook>) (Object) hibernateManager.GetListOfObject(QueryGenerator.GetAudioBooks());
            return audioBooks;
        }
        catch(Exception ex){
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
        }
        return null;
    }

    public List<Movies> GetMovies(){
        try{
            List<Movies> movies = (List<Movies>) (Object) hibernateManager.GetListOfObject(QueryGenerator.GetMovies());
            return movies;
        }
        catch (Exception ex){
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
        }
        return null;
    }

    public List<MusicItem> GetMusicItems(){
        try{
            List<MusicItem> musicItems = (List<MusicItem>) (Object) hibernateManager.GetListOfObject(QueryGenerator.GetMusicItems());
            return musicItems;
        }
        catch(Exception ex){
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
        }
        return null;
    }

    public void DeleteItem(int id){
        try{
            BaseLibraryItem item = (BaseLibraryItem) hibernateManager.GetObject(QueryGenerator.GetLoadLazyDataItemQuery(id));
            List<Author> authors = item.getAuthor();

            for(Author a : authors){
                Author auth = (Author) hibernateManager.GetObject(QueryGenerator.GetLoadLazyDataAuthorQuery(a.getId()));
                auth.getWork().remove(item);
            }

            List<Author> n = null;
            item.setAuthor(n);

            hibernateManager.DeleteObject(item);
        }
        catch(Exception ex){
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
        }
        finally {
            hibernateManager.CloseSession();
        }
    }
}
