package Hibernate.Control.Main.Repository;

import Hibernate.Control.Main.HibernateMain;
import Library.Dto.java.DTOLibraryItems.Author;
import Library.Dto.java.DTOLibraryItems.BookItem;
import Utils.QueryGenerator;

import java.util.List;

public class LibraryRepository implements LibraryRepositoryInterface {
    protected HibernateMain hibernateManager;

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

    public List<Object> GetListOfObject(String query){
        List<Object> result = hibernateManager.GetListOfObject(query);
        hibernateManager.CloseSession();
        return result;
    }
}
