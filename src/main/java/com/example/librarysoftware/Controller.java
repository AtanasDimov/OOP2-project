package com.example.librarysoftware;

import Hibernate.Control.Main.HibernateMain;
import Hibernate.Control.Main.LibraryRepository;
import Library.Dto.java.Contracts.AccountsInterface;
import Utils.QueryGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField Login_Username;
    @FXML
    private PasswordField Login_Password;
    public void Submit(ActionEvent e) {
        String username = Login_Username.getText();
        String password = Login_Password.getText();
        LibraryRepository lr = new LibraryRepository(new HibernateMain());
        String query = QueryGenerator.GetLoginQuery(username, password);
        AccountsInterface account = (AccountsInterface) lr.GetObject(query);
    }

    public void Clear(ActionEvent e) {
        Login_Username.clear();
        Login_Password.clear();
    }
}
