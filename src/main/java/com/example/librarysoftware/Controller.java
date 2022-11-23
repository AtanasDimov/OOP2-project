package com.example.librarysoftware;

import Hibernate.Control.Main.HibernateMain;
import Hibernate.Control.Main.LibraryRepository;
import Library.Dto.java.Contracts.AccountsInterface;
import Library.Dto.java.DTOAccount.Admin;
import Utils.QueryGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField user;
    @FXML
    private PasswordField pass;
    public void Submit(ActionEvent e) {
        String username = user.getText();
        String password = pass.getText();
        LibraryRepository lr = new LibraryRepository(new HibernateMain());
        String query = QueryGenerator.GetLoginQuery(username, password);
        AccountsInterface account = (AccountsInterface) lr.GetObject(query);
    }

    public void Clear(ActionEvent e) {
    }
}
