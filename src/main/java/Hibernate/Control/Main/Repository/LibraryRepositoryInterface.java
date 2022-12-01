package Hibernate.Control.Main.Repository;

public interface LibraryRepositoryInterface {
    void AddObject(Object object);
    Object GetObject(String query);
}
