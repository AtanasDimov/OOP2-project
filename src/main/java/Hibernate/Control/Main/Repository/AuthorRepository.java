package Hibernate.Control.Main.Repository;

import Hibernate.Control.Main.HibernateMain;
import Library.Dto.java.DTOLibraryItems.Author;
import Utils.QueryGenerator;

public class AuthorRepository extends LibraryRepository{

    public AuthorRepository(HibernateMain hibernateManager) {
        super(hibernateManager);
    }

    public Author GetEagerAuthor(int id){
        Author result = (Author) hibernateManager.GetObject(QueryGenerator.GetLoadLazyDataAuthorQuery(id));
        hibernateManager.CloseSession();
        return result;
    }

    public Author GetLazyDataAuthor(Author a){
        Author result = (Author) hibernateManager.GetObject(QueryGenerator.GetLoadLazyDataAuthorQuery(a.getId()));
        a = result;
        hibernateManager.CloseSession();
        return result;
    }
}
