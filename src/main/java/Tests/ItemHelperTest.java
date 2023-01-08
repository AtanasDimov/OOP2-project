package Tests;

import Hibernate.Control.Main.Repository.LibraryRepository;
import Library.Dto.java.DTOLibraryItems.Author;
import Library.Dto.java.DTOLibraryItems.BaseLibraryItem;
import Library.Dto.java.DTOLibraryItems.BookItem;
import Utils.ItemHelper;
import Utils.QueryGenerator;
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

class ItemHelperTest {

    @Test
    void getItems() {
        ItemHelper helper = mock(ItemHelper.class);
        List<Object> objects = (List<Object>) (Object)helper.GetItems();
        assertInstanceOf(BaseLibraryItem.class, objects.get(0));
    }

}