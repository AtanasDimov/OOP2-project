package Hibernate.Control.Main;

import Library.Dto.java.DTOAccount.AccountBase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateMain {
    /*private static final VarHandle MODIFIERS;

    static {
        try {
            var lookup = MethodHandles.privateLookupIn(Field.class, MethodHandles.lookup());
            MODIFIERS = lookup.findVarHandle(Field.class, "modifiers", int.class);
        } catch (IllegalAccessException | NoSuchFieldException ex) {
            throw new RuntimeException(ex);
        }
    }*/
    public static void main(String[] args){
     //   Configuration configuration = new Configuration().configure();
        //configuration.addAnnotatedClass(AccountBase.class);
       // StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

        /*var emptyElementDataField = ArrayList.class.getDeclaredField("EMPTY_ELEMENTDATA");
        MODIFIERS.set(emptyElementDataField, emptyElementDataField.getModifiers() & ~Modifier.FINAL);*/
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        SessionFactory factory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        AccountBase ac = new AccountBase("first", "second");
        session.save(ac);
        transaction.commit();
        session.close();
        factory.close();

    }

}
