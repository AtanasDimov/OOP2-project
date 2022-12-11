package Hibernate.Control.Main.Repository;

import Hibernate.Control.Main.HibernateMain;
import Library.Dto.java.DTOLibraryItems.BookItem;
import Utils.QueryGenerator;

public class BookRepository extends LibraryRepository{

    public BookRepository(HibernateMain hibernateManager){
        super(hibernateManager);
    }

    public BookItem GetEagerBook(int id){
        BookItem result = (BookItem) hibernateManager.GetObject(QueryGenerator.GetLoadLazyDataBookItemQuery(id));
        hibernateManager.CloseSession();
        return result;
    }

    public BookItem GetLazyDataBook(BookItem b){
        BookItem result = (BookItem) hibernateManager.GetObject(QueryGenerator.GetLoadLazyDataBookItemQuery(b.getId()));
        b = result;
        hibernateManager.CloseSession();
        return result;
    }
}
