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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        LibraryRepository repository = RepositoryFactory.CreateLibraryRepository();
        List<BaseLibraryItem> itemsForArchive = (List<BaseLibraryItem>)(Object)repository.GetListOfObject(QueryGenerator.GetItems());

        LocalDate currentDate = LocalDate.now();

        LocalDate thresholdDate = currentDate.minusYears(15);

        itemsForArchive.stream()
                .filter(o -> o.getPublishDate().isBefore(thresholdDate))
                .filter(o -> !(o instanceof ArchiveItem))
                .collect(Collectors.toList());

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
        LibraryRepository repository = RepositoryFactory.CreateLibraryRepository();
        for(int id : authorId){
            Author authorFromDb = (Author)repository.GetObject(QueryGenerator.AuthorGetById(id));
            authors.add(authorFromDb);
        }
        repository = RepositoryFactory.CreateLibraryRepository();
        item.addAuthorRange(authors);
        repository.AddObject(item);
        repository.CloseSession();
    }
}
