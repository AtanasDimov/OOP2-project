package Hibernate.Control.Main.Repository;

import Hibernate.Control.Main.HibernateMain;

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
    }

    @Override
    public Object GetObject(String query) {
        Object result = hibernateManager.GetObject(query);
        return result;
    }

    public List<Object> GetListOfObject(String query){
        List<Object> result = hibernateManager.GetListOfObject(query);
        return result;
    }

    public void DeleteObject(Object object){
        hibernateManager.DeleteObject(object);
    }

    public void UpdateObject(Object object){
        hibernateManager.UpdateObject(object);
    }

    public void CloseSession(){
        hibernateManager.CloseSession();
    }
}
