import Library.Dto.java.DTOLibraryItems.Author;
import Library.Dto.java.DTOLibraryItems.BaseLibraryItem;
import Library.Dto.java.DTOLibraryItems.BookItem;
import Utils.ItemHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.*;
import org.testng.Assert;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemHelperTest {
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
    void getItems() {
        BaseLibraryItem item = new BookItem("Title", "Description", LocalDate.now(), 10, 5);
        session.persist(item);
        List<BaseLibraryItem> items = ItemHelper.GetItems();
        int itemsSize = items.size();
        Assert.assertEquals(1, itemsSize);
    }

    @Test
    void getItemsForArchive() {
    }
}