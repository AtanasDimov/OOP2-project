package Hibernate.Control.Main.Repository;

import ExceptionHandling.LibraryException;
import ExceptionHandling.SeverityCodes;
import Hibernate.Control.Main.HibernateMain;
import Logger.Logger;

import java.util.List;

public class LibraryRepository implements LibraryRepositoryInterface {
    protected HibernateMain hibernateManager;
    protected Logger _logger;

    public LibraryRepository(HibernateMain hibernateManager)
    {
        this.hibernateManager = hibernateManager;
        _logger = new Logger();
    }
    @Override
    public void AddObject(Object object) {
        try {
            hibernateManager.AddObject(object);
        }
        catch (Exception ex){
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
        }
    }

    @Override
    public Object GetObject(String query) {
        try{
            Object result = hibernateManager.GetObject(query);
            return result;
        }
        catch (Exception ex){
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
        }
        return null;
    }

    public List<Object> GetListOfObject(String query){
        try{
            List<Object> result = hibernateManager.GetListOfObject(query);
            return result;
        }
        catch(Exception ex){
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
        }
        return null;
    }

    public void DeleteObject(Object object){
        try {
            hibernateManager.DeleteObject(object);
        }
        catch(Exception ex){
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
        }
    }

    public void UpdateObject(Object object){
        try{
            hibernateManager.UpdateObject(object);
        }
        catch (Exception ex){
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
        }
    }

    public void CloseSession(){
        try{
            hibernateManager.CloseSession();
        }
        catch (Exception ex){
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
        }
    }
}
