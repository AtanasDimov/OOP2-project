package com.example.librarysoftware;

import Hibernate.Control.Main.HibernateMain;
import Hibernate.Control.Main.Repository.AuthorRepository;
import Hibernate.Control.Main.Repository.LibraryRepository;
import Library.Dto.java.DTOLibraryItems.Author;
import Utils.QueryGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private TextField user;
    @FXML
    private PasswordField pass;
    public void Submit(ActionEvent e) {
        //String username = user.getText();
        //String password = pass.getText();

        /*String[] name = {"Adolf", "Hitler"};
        Author author = new BookAuthor(name);

        BookItem book = new BookItem();
        book.setTitle("Neshto");
        book.setPageCount(50);
        book.setDescription("Hitler");
        book.setPublishDate(LocalDate.now());
        book.setAuthor(author);

        List<BaseLibraryItem> items = new ArrayList<>();
        items.add(book);

        author.addWork(book);

        LibraryRepository lr = new LibraryRepository(new HibernateMain());
        lr.AddObject(book);*/

        AuthorRepository ar = new AuthorRepository(new HibernateMain());
        Author grigor = ar.GetEagerAuthor(1);
        //ar = new AuthorRepository(new HibernateMain());
        //ar.GetLazyDataAuthor(grigor);


    }

    public void Clear(ActionEvent e) {
    }
}
