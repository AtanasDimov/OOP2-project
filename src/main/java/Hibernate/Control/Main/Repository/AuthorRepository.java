package Hibernate.Control.Main.Repository;

import Hibernate.Control.Main.HibernateMain;
import Library.Dto.java.DTOLibraryItems.Author;
import Library.Dto.java.DTOLibraryItems.BaseLibraryItem;
import Library.Dto.java.DTOLibraryItems.BookItem;
import Utils.QueryGenerator;

import java.util.List;

public class AuthorRepository extends LibraryRepository{
    HibernateMain hibernateManager;

    public AuthorRepository(HibernateMain hibernateManager) {
        super(hibernateManager);
        this.hibernateManager = hibernateManager;
    }

    public Author GetAuthor(String query){
        Author author = (Author)super.GetObject(query);
        return author;
    }

    public Author GetLazyDataAuthor(Author a){
        Author result = (Author) hibernateManager.GetObject(QueryGenerator.GetLoadLazyDataAuthorQuery(a.getId()));
        a = result;
        hibernateManager.CloseSession();
        return result;
    }
}
