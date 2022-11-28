package Hibernate.Control.Main;

public class LibraryRepository implements LibraryRepositoryInterface{
    private HibernateMain hibernateManager;

    public LibraryRepository(HibernateMain hibernateManager)
    {
        this.hibernateManager = hibernateManager;
    }
    @Override
    public void AddObject(Object object) {
        hibernateManager.AddObject(object);
    }

    @Override
    public Object GetObject(String query) {
        return hibernateManager.GetObject(query);
    }
}
