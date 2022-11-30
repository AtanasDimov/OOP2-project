package Hibernate.Control.Main;

import Library.Dto.java.DTOLibraryItems.Author;
import Library.Dto.java.DTOLibraryItems.BaseLibraryItem;
import Library.Dto.java.DTOLibraryItems.BookItem;
import Utils.QueryGenerator;

import java.util.List;

public class LibraryRepository implements LibraryRepositoryInterface{
    private HibernateMain hibernateManager;

    public LibraryRepository(HibernateMain hibernateManager)
    {
        this.hibernateManager = hibernateManager;
    }
    @Override
    public void AddObject(Object object) {
        hibernateManager.AddObject(object);
        hibernateManager.CloseSession();
    }

    @Override
    public Object GetObject(String query) {
        Object result = hibernateManager.GetObject(query);
        hibernateManager.CloseSession();
        return result;
    }

    public void GetLazyDataAuthor(Author a){
        List<BookItem> b = (List<BookItem>)(List<?>)hibernateManager.GetListOfObject(QueryGenerator.GetLoadLazyDataAuthorQuery(a));
        a.lazyLoad(b);
        hibernateManager.CloseSession();
    }
}
