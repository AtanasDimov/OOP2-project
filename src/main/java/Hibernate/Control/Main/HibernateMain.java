package Hibernate.Control.Main;

import ExceptionHandling.LibraryException;
import ExceptionHandling.SeverityCodes;
import Logger.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HibernateMain {
    private StandardServiceRegistry registry;
    SessionFactory factory;
    Session session;
    private Logger _logger;
    public  HibernateMain() {
        this.registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        _logger = new Logger();
    }


    public Object GetObject(String query){
        factory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        session = factory.openSession();
        List<Object> objects;
        Transaction transaction = session.beginTransaction();
        try{
            objects = session.createQuery(query).getResultList();
            transaction.commit();

            return objects.get(0);
        }
        catch (Exception ex){
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
            transaction.rollback();
            return null;
        }
    }


    public List<Object> GetListOfObject(String query)
    {   factory = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();
        session = factory.openSession();
        List<Object> objects;
        Transaction transaction = session.beginTransaction();
        try{
            objects = session.createQuery(query).getResultList();
            transaction.commit();
            return objects;
        }
        catch(Exception ex){
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
            transaction.rollback();
            return null;
        }
    }

    public void AddObject(Object object){
        factory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(object);
            transaction.commit();
        }
        catch (Exception ex){
            transaction.rollback();
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
        }
    }

    public void DeleteObject(Object object){
        factory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.delete(object);
            transaction.commit();
        }
        catch (Exception ex){
            transaction.rollback();
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
        }
    }

    public void UpdateObject(Object object){
        factory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.update(object);
            transaction.commit();
        }
        catch (Exception ex){
            transaction.rollback();
            _logger.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
        }
    }

    public void CloseSession()
    {
        if(session != null && factory != null )
        {
            session.close();
            factory.close();
        }

    }
}

