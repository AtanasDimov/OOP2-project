package com.example.librarysoftware;

import Hibernate.Control.Main.HibernateMain;
import Hibernate.Control.Main.LibraryRepository;
import Library.Dto.java.Contracts.AccountsInterface;
import Library.Dto.java.Contracts.LibraryItemInterface;
import Library.Dto.java.DTOAccount.Admin;
import Library.Dto.java.DTOLibraryItems.Author;
import Library.Dto.java.DTOLibraryItems.BookAuthor;
import Library.Dto.java.DTOLibraryItems.BookItem;
import Utils.QueryGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.management.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private TextField user;
    @FXML
    private PasswordField pass;
    public void Submit(ActionEvent e) {
        String username = user.getText();
        String password = pass.getText();
        LibraryRepository lr = new LibraryRepository(new HibernateMain());
        //String query = QueryGenerator.GetLoginQuery(username, password);
        //AccountsInterface account = (AccountsInterface) lr.GetObject(query);
        BookItem book = new BookItem("Moby dick", "desc", LocalDate.now(), 50);
        String name = "Grigor Grigorov";
        String[] names = name.split(" ");
        BookAuthor grigor = new BookAuthor(names);
        List<BookAuthor> authors = new ArrayList<>();
        authors.add(grigor);
        List<BookItem> work = new ArrayList<>();
        work.add(book);
        grigor.addWork(work);
        book.setАuthor(grigor);
        lr.AddObject(book);
        //lr = new LibraryRepository(new HibernateMain());
        //lr.AddObject(grigor);

        //lr.AddObject(grigor);
        /*String query = "FROM Author WHERE id = 0";
        Author a = (Author) lr.GetObject(query);
        lr = new LibraryRepository(new HibernateMain());
        lr.GetLazyDataAuthor(a);
        System.out.println(a.getWork());*/

    }

    public void Clear(ActionEvent e) {
    }
}
