package Utils;

import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.DTOLibraryItems.ArchiveItem;
import Library.Dto.java.DTOLibraryItems.BaseLibraryItem;

import java.util.List;

public class ItemHelper {
    public static void ArchiveItem(int id){
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        BaseLibraryItem item = (BaseLibraryItem) lr.GetObject(QueryGenerator.GetItemById(id));
        ArchiveItem archived = (ArchiveItem) item;
        lr.DeleteObject(item);
        lr.AddObject(archived);
    }

    public static List<BaseLibraryItem> GetItemsForArchive(){
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        List<BaseLibraryItem> itemsForArchive = (List<BaseLibraryItem>)(Object)lr.GetListOfObject(QueryGenerator.GetItemsForArchive());
        return itemsForArchive;
    }

    public static void ReturnItem(int id){
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        BaseLibraryItem item = (BaseLibraryItem) lr.GetObject(QueryGenerator.GetItemById(id));

        item.returnItem();

        lr.UpdateObject(item);
    }

    public static void GiveItem(int id){
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        BaseLibraryItem item = (BaseLibraryItem) lr.GetObject(QueryGenerator.GetItemById(id));

        item.giveItem();

        lr.UpdateObject(item);
    }

}
