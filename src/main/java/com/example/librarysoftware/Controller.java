package com.example.librarysoftware;

import Hibernate.Control.Main.HibernateMain;
import Hibernate.Control.Main.Repository.LibraryRepository;
import Library.Dto.java.DTOLibraryItems.Author;
import Library.Dto.java.DTOLibraryItems.BookAuthor;
import Library.Dto.java.DTOLibraryItems.BookItem;
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
        String username = user.getText();
        String password = pass.getText();
    }

    public void Clear(ActionEvent e) {
    }
}
