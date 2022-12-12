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
    public  HibernateMain() {
        this.registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
    }


    public Object GetObject(String query){

        List<Object> objects;
        try{
            factory = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            objects = session.createQuery(query).getResultList();
            transaction.commit();

            return objects.get(0);
        }
        catch (Exception ex){
            LibraryException le = new LibraryException(ex.getMessage(), SeverityCodes.Medium);
            Logger log = new Logger();
            log.LogException(le);
            return null;
        }
    }


    public List<Object> GetListOfObject(String query)
    {
        List<Object> objects;
        try{
            factory = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            objects = session.createQuery(query).getResultList();
            transaction.commit();
            return objects;
        }
        catch(Exception ex){
            LibraryException le = new LibraryException(ex.getMessage(), SeverityCodes.Medium);
            Logger log = new Logger();
            log.LogException(le);
            return null;
        }
    }

    public void AddObject(Object object){
        try{
            factory = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
        }
        catch (Exception ex){
            LibraryException le = new LibraryException(ex.getMessage(), SeverityCodes.Medium);
            Logger log = new Logger();
            log.LogException(le);
        }
    }

    public void DeleteObject(Object object){
        try{
            factory = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        }
        catch (Exception ex){
            LibraryException le = new LibraryException(ex.getMessage(), SeverityCodes.Medium);
            Logger log = new Logger();
            log.LogException(le);
        }
    }

    public void UpdateObject(Object object){
        try{
            factory = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
        }
        catch (Exception ex){
            LibraryException le = new LibraryException(ex.getMessage(), SeverityCodes.Medium);
            Logger log = new Logger();
            log.LogException(le);
        }
    }

    public void CloseSession()
    {
        session.close();
        factory.close();
    }
}

