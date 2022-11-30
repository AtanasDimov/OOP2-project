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
    SessionFactory factory;
    Session session;
    public  HibernateMain() {
        this.registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
    }


    public Object GetObject(String query){

        List<Object> objects;
        factory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
         objects = session.createQuery(query).getResultList();
        transaction.commit();

            return objects.get(0);
        }


    public List<Object> GetListOfObject(String query)
    {
        List<Object> objects;
        factory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        objects = session.createQuery(query).getResultList();
        transaction.commit();

        return objects;
    }

    public void AddObject(Object object){
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

            factory = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
        }

        public void CloseSession()
        {
            session.close();
            factory.close();
        }
    }

