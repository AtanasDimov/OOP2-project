package Utils;

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
}
