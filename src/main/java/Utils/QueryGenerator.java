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

    public static String GetLoadLazyDataItemQuery(int id) {
        String query = "FROM BaseLibraryItem b left join fetch b.author WHERE b.id=" + id;
        return query;
    }

    public static String GetLoadLazyDataItemsQuery() {
        String query = "FROM BaseLibraryItem b LEFT OUTER JOIN FETCH b.author";
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

    public static String GetReservationByReaderId(int id){
        String query = "From Reservation Where ReaderId = " + id;
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
        String query = "From BaseLibraryItem WHERE publishDate < DATE_SUB(CURRENT_DATE, INTERVAL 15 YEAR)";
        return query;
    }

    public static String GetReaderById(int id){
        String query = "From ReaderAccount Where accountId = " + id;
        return query;
    }
    public static String GetOperatorById(int id){
        String query = "From OperatorAccount Where accountId = " + id;
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

    public static String GetReaderFirstNameById(int id){
        String query = "Select firstName FROM Accounts WHERE accountId = " + id;
        return query;
    }

    public static String GetReaderLastNameById(int id){
        String query = "SELECT lastName FROM Accounts WHERE accountId = " + id;
        return query;
    }
    public static String GetBorrowForms(){
        String query = "From BorrowForm";
        return query;
    }

    public static String GetBorrowFormById(int id){
        String query = "From BorrowForm Where id = " + id;
        return query;
    }

    public static String GetUsernameById(int id) {
        String query = "Select username FROM Accounts WHERE id =" +id;
        return query;
    }
    public static String GetItemTitleById(int id){
        String query = "Select title From BaseLibraryItem Where Id ="+id;
        return query;
    }
    public static String GetReaderItems(int id){
        String query = "Select b From BaseLibraryItem b INNER JOIN Reservation r ON r.ItemId = b.id WHERE r.ReaderId = " + id;
        return query;
    }
    public static String GetAllForms(){
        String query = "From Form";
        return query;
    }
    public static String GetAllReaders(){
        String query = "From Accounts Where account_type = 3";
        return query;
    }
    public static String GetAllOperators(){
        String query = "From Accounts Where account_type = 2";
        return query;
    }
    public static String CheckScrappedItem(int id){
        String query = "Select Count(*) From ScrappedItem Where itemId = " + id;
        return query;
    }
    public static String GetScrappedItem(int id){
        String query = "From ScrappedItem Where itemId = " + id;
        return query;
    }

    public static String GetBorrowedDateForItem(int id, int readerId){
        String query = "SELECT BorrowDate FROM Reservation WHERE ItemId = " + id + "AND ReaderId = " + readerId;
        return query;
    }

    public static String GetDueDateForItem(int id, int readerId){
        String query = "SELECT DueDate FROM Reservation WHERE ItemId = " + id + "AND ReaderId = " + readerId;
        return query;
    }
}
