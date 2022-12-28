package Utils;

import Hibernate.Control.Main.Repository.AuthorRepository;
import Hibernate.Control.Main.Repository.ItemRepository;
import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.Alert.Alert;
import Library.Dto.java.Alert.AlertFactory;
import Library.Dto.java.Alert.AlertSeverity;
import Library.Dto.java.DTOLibraryItems.ArchiveItem;
import Library.Dto.java.DTOLibraryItems.Author;
import Library.Dto.java.DTOLibraryItems.BaseLibraryItem;
import Library.Dto.java.DTOLibraryItems.ScrappedItem;
import com.sun.jmx.mbeanserver.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemHelper {
    public static List<BaseLibraryItem> GetItems(){
        List<BaseLibraryItem> items = new ArrayList<>();
        LibraryRepository repository = RepositoryFactory.CreateLibraryRepository();
        items = (List<BaseLibraryItem>) (Object)repository.GetListOfObject(QueryGenerator.GetLoadLazyDataItemsQuery());
        return items.stream().filter(i -> !(i instanceof ScrappedItem)).collect(Collectors.toList());
    }
    public static void ArchiveItem(int id){
        ItemRepository lr = RepositoryFactory.CreateItemRepository();
        BaseLibraryItem item = (BaseLibraryItem) lr.GetEagerItem(id);
        ArchiveItem archived = new ArchiveItem(item.getTitle(), item.getDescription(),item.getPublishDate(),item.getAuthor(), item.getQuantity());
        lr.DeleteObject(item);
        lr.AddObject(archived);
        lr.CloseSession();
    }
    public static void ScrapItem(int id){
        ItemRepository repository = RepositoryFactory.CreateItemRepository();
        BaseLibraryItem item = (BaseLibraryItem) repository.GetEagerItem(id);
        ScrappedItem scrappedItem;
        if((int)repository.GetObject(QueryGenerator.CheckScrappedItem(id)) == 0)
            scrappedItem = new ScrappedItem(item.getTitle(), item.getDescription(), item.getPublishDate(), item.getAuthor(),1, item.getId());
        else{
            scrappedItem = (ScrappedItem) repository.GetObject(QueryGenerator.CheckScrappedItem(id));
            repository.GetLazyDataItem(scrappedItem);
            scrappedItem.setQuantity(scrappedItem.getQuantity() + 1);
        }

        int quantity = item.getQuantity();
        item.setQuantity(--quantity);
        repository.UpdateObject(item);
        repository.AddObject(scrappedItem);
        repository.CloseSession();
    }
    public static List<BaseLibraryItem> GetItemsForArchive(){
        LibraryRepository repository = RepositoryFactory.CreateLibraryRepository();
        List<BaseLibraryItem> itemsForArchive = (List<BaseLibraryItem>)(Object)repository.GetListOfObject(QueryGenerator.GetItems());

        LocalDate currentDate = LocalDate.now();

        LocalDate thresholdDate = currentDate.minusYears(15);

        List<BaseLibraryItem> i = itemsForArchive.stream()
                .filter(o -> o.getPublishDate().isBefore(thresholdDate))
                .filter(o -> !(o instanceof ArchiveItem))
                .collect(Collectors.toList());

        return i;
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

        //increments the quantity
        item.returnItem();

        lr.CloseSession();
        lr = RepositoryFactory.CreateLibraryRepository();

        lr.UpdateObject(item);
        lr.CloseSession();
    }

    public static void GiveItem(int id){
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        BaseLibraryItem item = (BaseLibraryItem) lr.GetObject(QueryGenerator.GetItemById(id));
        lr.CloseSession();
        item.giveItem();
        lr = RepositoryFactory.CreateLibraryRepository();
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
            authorFromDb.addWork(item);
            authors.add(authorFromDb);
            repository.CloseSession();
        }
        repository = RepositoryFactory.CreateLibraryRepository();
        item.addAuthorRange(authors);
        repository.AddObject(item);
        repository.CloseSession();
    }
}
