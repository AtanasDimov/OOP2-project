package Hibernate.Control.Main.Repository;

import Hibernate.Control.Main.HibernateMain;

public class RepositoryFactory {
    public static LibraryRepository CreateLibraryRepository(){
        return new LibraryRepository(new HibernateMain());
    }

    public static ItemRepository CreateBookRepository(){
        return new ItemRepository(new HibernateMain());
    }

    public static AuthorRepository CreateAuthorRepository(){
        return new AuthorRepository(new HibernateMain());
    }
}
