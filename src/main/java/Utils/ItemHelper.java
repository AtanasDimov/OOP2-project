package Utils;

import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.DTOLibraryItems.ArchiveItem;
import Library.Dto.java.DTOLibraryItems.BaseLibraryItem;

public class ItemHelper {
    public static void ArchiveItem(int id){
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        BaseLibraryItem item = (BaseLibraryItem) lr.GetObject(QueryGenerator.GetItemById(id));
        ArchiveItem archived = (ArchiveItem) item;
        lr.DeleteObject(item);
        lr.AddObject(archived);
    }
}
