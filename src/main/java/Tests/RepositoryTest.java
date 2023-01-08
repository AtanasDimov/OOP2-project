package Tests;

import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.DTOAccount.Admin;
import Library.Dto.java.DTOLibraryItems.Author;
import Library.Dto.java.DTOLibraryItems.BaseLibraryItem;
import Library.Dto.java.DTOLibraryItems.BookItem;
import Library.Dto.java.Form.Form;
import Library.Dto.java.VisualizeItems.ReaderVisualize;
import Utils.ItemHelper;
import Utils.PasswordHasher;
import Utils.QueryGenerator;
import Utils.ReferenceHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.*;
import org.mockito.configuration.IMockitoConfiguration;
import org.testng.Assert;

import java.time.LocalDate;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RepositoryTest {

    private static SessionFactory sessionFactory = null;
    private Session session = null;

    @BeforeAll
    static void setup(){
        try {
            StandardServiceRegistry standardRegistry
                    = new StandardServiceRegistryBuilder()
                    .configure("h2.cfg.xml")
                    .build();

            Metadata metadata = new MetadataSources(standardRegistry)
                    .addAnnotatedClass(BaseLibraryItem.class)
                    .addAnnotatedClass(Author.class)
                    .addAnnotatedClass(BookItem.class)
                    .getMetadataBuilder()
                    .build();

            sessionFactory = metadata
                    .getSessionFactoryBuilder().build();

        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    @BeforeEach
    void setupThis(){
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @AfterEach
    void tearThis(){
        session.getTransaction().commit();
    }

    @AfterAll
    static void tear(){
        sessionFactory.close();
    }

    @Test
    void getBookObjects() {
        LibraryRepository repo = RepositoryFactory.CreateLibraryRepository();
        List<Object> objects = (List<Object>) (Object)repo.GetListOfObject(QueryGenerator.GetItems());
        assertInstanceOf(BaseLibraryItem.class, objects.get(0));
    }

    @Test
    void getAdminAccount(){
        LibraryRepository repo = RepositoryFactory.CreateLibraryRepository();
        String username = "admin";
        String pass = "";
        try{
            pass = PasswordHasher.HashPassword("admin");
        }
        catch(Exception ex){

        }
        Object account = repo.GetObject(QueryGenerator.GetLoginQuery(username, pass));
        assertInstanceOf(Admin.class, account);
    }

    @Test
    void addBookItem(){
        BaseLibraryItem item = new BookItem("Title", "Description", LocalDate.now(), 10, 5);
        session.persist(item);
        BaseLibraryItem itemFromDb = session.get(BaseLibraryItem.class, new Integer(1));
        assertEquals(item, itemFromDb);
    }
}