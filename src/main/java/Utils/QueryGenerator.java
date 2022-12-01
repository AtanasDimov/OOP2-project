package Utils;

import Hibernate.Control.Main.HibernateMain;
import Library.Dto.java.DTOLibraryItems.Author;

public class QueryGenerator {
    public static String GetLoginQuery(String username, String password){
        String query = "from Accounts where username='" + username + "' AND password='" + password + "'";
        return query;
    }

    public static String GetLoadLazyDataAuthorQuery(Author a){
        String query = "SELECT a.work FROM Author a WHERE a.id = " + a.getId();
        return query;
    }

    public static String AuthorGetById(int id){
        String query = "FROM Author WHERE id = " + id;
        return query;
    }
}
