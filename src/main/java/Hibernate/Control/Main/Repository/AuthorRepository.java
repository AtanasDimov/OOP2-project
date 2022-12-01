package Hibernate.Control.Main.Repository;

import Hibernate.Control.Main.HibernateMain;
import Library.Dto.java.DTOLibraryItems.Author;
import Library.Dto.java.DTOLibraryItems.BookItem;
import Utils.QueryGenerator;

import java.util.List;

public class AuthorRepository extends LibraryRepository{
    HibernateMain hibernateManager;

    public AuthorRepository(HibernateMain hibernateManager) {
        super(hibernateManager);
        this.hibernateManager = hibernateManager;
    }

    public Object GetEagerAuthor(String query){
        Author author = (Author)super.GetObject(query);
        author.getWork();
        return author;
    }

    public void GetLazyDataAuthor(Author a){
        List<BookItem> b = (List<BookItem>)(List<?>)hibernateManager.GetListOfObject(QueryGenerator.GetLoadLazyDataAuthorQuery(a));
        a.lazyLoad(b);
        hibernateManager.CloseSession();
    }
}
