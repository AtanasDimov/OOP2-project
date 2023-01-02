package com.example.librarysoftware;

import ExceptionHandling.AlreadyLoggedException;
import ExceptionHandling.LibraryException;
import ExceptionHandling.NotExistException;
import ExceptionHandling.SeverityCodes;

import Logger.Logger;
import Sessions.ReservationSession;
import Sessions.UserSession;
import SqlFilter.SqlFilter;
import Utils.AccountHelper;

import Utils.GUIUtils;
import Utils.ItemHelper;
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
    @FXML
    private Label Login_ErrorLabel;

    public void Submit(ActionEvent e) {
        String username = Login_Username.getText();
        String password = Login_Password.getText();

        if(!SqlFilter.Validate(username) || !SqlFilter.Validate(password)){
            //sql injection detected
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
        Login_ErrorLabel.setText("");
        Login_Password.clear();
        Login_Username.clear();



    }

    public void Register(ActionEvent e){
        GUIUtils.changeScene(e, "/Register.fxml","Регистрация на читател:");
    }

}
