package Utils;

import Hibernate.Control.Main.HibernateMain;
import Library.Dto.java.DTOLibraryItems.Author;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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

    public static String GetLoadLazyDataBookItemsQuery() {
        String query = "FROM BaseLibraryItem b LEFT JOIN FETCH b.author";
        return query;
    }

    public static String GetListOfReservations(){
        String query = "From Reservation";
        return query;
    }

    public static String GetReservationById(int id){
        String query = "From Reservation Where Id = " + id;
        return query;
    }

    public static String GetActiveForms(){
        String query = "From Form Where IsActive = true";
        return query;
    }

    public static String GetNumberOfActiveForms(){
        String query = "Select Count(Id) From Form Where IsActive = true";
        return query;
    }

    public static String GetForm(int id){
        String query = "From Form Where Id = " + id;
        return query;
    }

    public static String GetItemById(int id){
        String query = "From BaseLibraryItem Where id = " + id;
        return query;
    }

    public static String GetItems(){
        String query = "From BaseLibraryItem";
        return query;
    }

    public static String GetAlerts(){
        String query = "From Alert";
        return query;
    }
    public static String GetItemsForArchive(){
        LocalDate date = LocalDate.now();
        String query = "From BaseLibraryItem Where PublishDate <= " + date.plus(-15, ChronoUnit.YEARS);
        return query;
    }

    public static String GetReaderById(int id){
        String query = "From ReaderAccount Where Id = " + id;
        return query;
    }
    public static String GetListOfAuthors(){
        String query = "From Author";
        return query;
    }

    public static String GetNarrators(){
        String query = "From Author Where autor_type = 1";
        return query;
    }
    public static String GetBookAuthors(){
        String query = "From Author Where autor_type = 2";
        return query;
    }

    public static String GetMovieDirectors(){
        String query = "From Author Where autor_type = 3";
        return query;
    }

    public static String GetMusicArtists(){
        String query = "From Author Where autor_type = 4";
        return query;
    }

    public static String GetBookItems(){
        String query = "From BookItem";
        return query;
    }

    public static String GetArchiveItems(){
        String query = "From ArchiveItem";
        return query;
    }

    public static String GetAudioBooks(){
        String query = "From AudioBook";
        return query;
    }

    public static String GetMovies(){
        String query = "From Movies";
        return query;
    }

    public static String GetMusicItems(){
        String query = "From MusicItem";
        return query;
    }
}
