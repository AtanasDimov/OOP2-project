package Utils;

import Hibernate.Control.Main.HibernateMain;
import Library.Dto.java.DTOLibraryItems.Author;

public class QueryGenerator {
    public static String GetLoginQuery(String username, String password){
        String query = "from Accounts where username='" + username + "' AND password='" + password + "'";
        return query;
    }

    public static String GetLoadLazyDataAuthorQuery(int id){
        String query = "FROM Author a left join fetch a.work WHERE a.id=" + id;
        return query;
    }

    public static String AuthorGetById(int id){
        String query = "FROM Author WHERE id = " + id;
        return query;
    }

    public static String GetLoadLazyDataBookItemQuery(int id) {
        String query = "FROM BookItem b left join fetch b.author WHERE b.id=" + id;
        return query;
    }

    public static String GetListOfReservations(){
        String query = "From Reservation";
        return query;
    }
}
