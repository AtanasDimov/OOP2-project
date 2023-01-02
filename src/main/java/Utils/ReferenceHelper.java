package Utils;

import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.DTOAccount.ReaderAccount;
import Library.Dto.java.DTOLibraryItems.ArchiveItem;
import Library.Dto.java.DTOLibraryItems.BaseLibraryItem;
import Library.Dto.java.DTOLibraryItems.ScrappedItem;
import Library.Dto.java.DTOLibraryItems.VisualMediaItem;
import Library.Dto.java.Form.Form;
import Library.Dto.java.VisualizeItems.ReaderVisualize;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReferenceHelper {
    public static List<Form> GetAllReferenceForms(){
        LibraryRepository repository = RepositoryFactory.CreateLibraryRepository();
        List<Form> allForms = (List<Form>) (Object)repository.GetListOfObject(QueryGenerator.GetAllForms());
        repository.CloseSession();
        return allForms;
    }

    public static List<BaseLibraryItem> GetAllReferenceItems(){
        LibraryRepository repository = RepositoryFactory.CreateLibraryRepository();
        List<BaseLibraryItem> items = (List<BaseLibraryItem>) (Object)repository.GetListOfObject(QueryGenerator.GetLoadLazyDataItemsQuery());
        repository.CloseSession();
        return items.stream().filter(i -> !(i instanceof ScrappedItem)).filter(i -> i.getQuantity() > 0).collect(Collectors.toList());
    }

    public static List<BaseLibraryItem> GetAllScrappedItems(){
        LibraryRepository repository = RepositoryFactory.CreateLibraryRepository();
        List<BaseLibraryItem> scrappedItems = (List<BaseLibraryItem>) (Object)repository.GetListOfObject(QueryGenerator.GetLoadLazyDataScrappedItemsQuery());
        repository.CloseSession();
        return scrappedItems;
    }

    public static List<ReaderVisualize> GetAllReferenceReader(){
        LibraryRepository repository = RepositoryFactory.CreateLibraryRepository();
        List<ReaderAccount> accounts = (List<ReaderAccount>) (Object)repository.GetListOfObject(QueryGenerator.GetAllReaders());
        List<ReaderVisualize> readerVisualizeList = new ArrayList<>();

        for(ReaderAccount r : accounts){
            List<BaseLibraryItem> readerItems = (List<BaseLibraryItem>) (Object)repository.GetListOfObject(QueryGenerator.GetReaderItems(r.getAccountId()));
            readerVisualizeList.add(new ReaderVisualize(r, readerItems));
        }

        repository.CloseSession();
        return readerVisualizeList;
    }

    public static List<ReaderAccount> GetAllReferenceReadersByRating(int rating){
        LibraryRepository repository = RepositoryFactory.CreateLibraryRepository();
        List<ReaderAccount> accounts = (List<ReaderAccount>) (Object)repository.GetListOfObject(QueryGenerator.GetAllReaders());
        List<ReaderAccount> loyalReaders = accounts.stream()
                .filter(a -> a.getReaderRating() == rating)
                .collect(Collectors.toList());

        repository.CloseSession();
        return loyalReaders;
    }
}
