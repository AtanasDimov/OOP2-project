package Utils;

import Hibernate.Control.Main.Repository.AuthorRepository;
import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.Alert.Alert;
import Library.Dto.java.Alert.AlertFactory;
import Library.Dto.java.Alert.AlertSeverity;
import Library.Dto.java.DTOLibraryItems.ArchiveItem;
import Library.Dto.java.DTOLibraryItems.Author;
import Library.Dto.java.DTOLibraryItems.BaseLibraryItem;
import com.sun.jmx.mbeanserver.Repository;

import java.util.ArrayList;
import java.util.List;

public class ItemHelper {
    public static void ArchiveItem(int id){
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        BaseLibraryItem item = (BaseLibraryItem) lr.GetObject(QueryGenerator.GetItemById(id));
        ArchiveItem archived = (ArchiveItem) item;
        lr.DeleteObject(item);
        lr.AddObject(archived);
        lr.CloseSession();
    }

    public static List<BaseLibraryItem> GetItemsForArchive(){
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        List<BaseLibraryItem> itemsForArchive = (List<BaseLibraryItem>)(Object)lr.GetListOfObject(QueryGenerator.GetItemsForArchive());
        lr.CloseSession();
        return itemsForArchive;
    }

    public static void AlertForArchive(){
        List<BaseLibraryItem> itemsForArchive = GetItemsForArchive();
        LibraryRepository repository = RepositoryFactory.CreateLibraryRepository();

        for(BaseLibraryItem item : itemsForArchive){
            String message = "Item with id:" + item.getId() + " and name:" + item.getTitle() + " needs to be added for archive!";
            Alert alert = AlertFactory.CreateAlert(message, AlertSeverity.ArchiveNeed);
            repository.AddObject(alert);
        }

        repository.CloseSession();
    }

    public static void ReturnItem(int id){
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        BaseLibraryItem item = (BaseLibraryItem) lr.GetObject(QueryGenerator.GetItemById(id));

        item.returnItem();

        lr.UpdateObject(item);
        lr.CloseSession();
    }

    public static void GiveItem(int id){
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        BaseLibraryItem item = (BaseLibraryItem) lr.GetObject(QueryGenerator.GetItemById(id));

        item.giveItem();

        lr.UpdateObject(item);
        lr.CloseSession();
    }

    public static void RemoveItem(int id){
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        BaseLibraryItem item = (BaseLibraryItem) lr.GetObject(QueryGenerator.GetItemById(id));

        lr.DeleteObject(item);
        lr.CloseSession();
    }

    public static void CreateItem(BaseLibraryItem item, List<Integer> authorId){
        List<Author> authors = new ArrayList<>();
        LibraryRepository repository;
        for(int id : authorId){
            repository = RepositoryFactory.CreateLibraryRepository();
            Author authorFromDb = (Author)repository.GetObject(QueryGenerator.AuthorGetById(id));
            authors.add(authorFromDb);
        }
        repository = RepositoryFactory.CreateLibraryRepository();
        item.addAuthorRange(authors);
        repository.AddObject(item);
        repository.CloseSession();
    }
}
