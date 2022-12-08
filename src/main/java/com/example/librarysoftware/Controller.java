package com.example.librarysoftware;

import Hibernate.Control.Main.HibernateMain;
import Hibernate.Control.Main.Repository.AuthorRepository;
import Hibernate.Control.Main.Repository.LibraryRepository;
import Library.Dto.java.DTOLibraryItems.Author;
import Utils.AccountHelper;
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
    private TextField Login_Username;
    @FXML
    private PasswordField Login_Password;
    public void Submit(ActionEvent e) {
        String username = Login_Username.getText();
        String password = Login_Password.getText();

        if(AccountHelper.CheckIfExists(username, password)){
            //logic
        }
        else{
            //logic
        }

    }

    public void Clear(ActionEvent e) {
    }
}
