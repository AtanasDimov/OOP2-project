package com.example.librarysoftware;

import ExceptionHandling.AlreadyLoggedException;
import ExceptionHandling.LibraryException;
import ExceptionHandling.NotExistException;
import ExceptionHandling.SeverityCodes;
import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.DTOAccount.Admin;
import Logger.Logger;
import Sessions.ReservationSession;
import Sessions.UserSession;
import SqlFilter.SqlFilter;
import Utils.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;



public class LoginController {
    @FXML
    private TextField Login_Username;
    @FXML
    private PasswordField Login_Password;

    public void Submit(ActionEvent e) {
        String username = Login_Username.getText();
        String password = Login_Password.getText();

        if(!SqlFilter.Validate(username) || !SqlFilter.Validate(password)){
            GUIUtils.SetupAlert("Засечени нелегални символи");
            return;
        }

        try{
            AccountHelper.LogInUser(username, password);
            if(UserSession.isAdmin() || UserSession.isOperator()){
                ItemHelper.AlertForArchive();
                ReservationSession.Configure();
            }
            GUIUtils.changeScene(e,"/Index.fxml","Добре дошли");
        }
        catch(Exception ex){
            GUIUtils.SetupAlert(ex.getMessage());
            Logger log = new Logger();
            if(ex instanceof NotExistException){
                log.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Light));
            }
            if(ex instanceof AlreadyLoggedException){
                log.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
            }
        }

    }

    public void Clear() {
        Login_Password.clear();
        Login_Username.clear();

        String pass = "";
        try{
            pass = PasswordHasher.HashPassword("admin");
        }
        catch (Exception ex){

        }

        Admin admin = new Admin("admin", pass);
        LibraryRepository repo = RepositoryFactory.CreateLibraryRepository();
        repo.AddObject(admin);
        repo.CloseSession();
    }

    public void Register(ActionEvent e){
        GUIUtils.changeScene(e, "/Register.fxml","Регистрация на читател:");
    }

}
