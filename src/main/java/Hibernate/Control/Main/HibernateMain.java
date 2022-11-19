package Hibernate.Control.Main;

import Library.Dto.java.DTOAccount.AccountBase;
import Library.Dto.java.DTOAccount.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateMain {
    private StandardServiceRegistry registry;
    public  HibernateMain() {
        this.registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
    }


    public Object GetObject(String query){

        Session session;
        List<Object> objects;
        SessionFactory factory;
        try {
        factory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
         objects = session.createQuery(query).getResultList();
        transaction.commit();
        }
        catch(Exception ex){}
        finally {
            session.close();
            factory.close();
            return objects.get(0);
        }

    }
    public void AddObject(Object object){
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        Session session;
        SessionFactory factory;
        try {
            factory = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(object);
        }catch(Exception ex){
        }finally{
            session.close();
            factory.close();
        }



    }
}
