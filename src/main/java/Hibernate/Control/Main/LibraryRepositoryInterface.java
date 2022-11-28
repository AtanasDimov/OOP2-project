package Hibernate.Control.Main;

public interface LibraryRepositoryInterface {
    void AddObject(Object object);
    Object GetObject(String query);
}
